;;Almost all of it is "https://github.com/jeapostrophe/exp/blob/master/.emacs.el"

;; buffer names
(require 'uniquify)
(setq uniquify-min-dir-content 90
      uniquify-buffer-name-style 'forward)

;; spelling
(require 'ispell)
(setq ispell-process-directory (expand-file-name "~/"))
(setq ispell-program-name "aspell")
(setq ispell-list-command "list")
(setq ispell-extra-args '("--sug-mode=ultra"))

(require 'flyspell)
(setq flyspell-issue-message-flag nil)
(dolist (hook '(text-mode-hook latex-mode-hook org-mode-hook markdown-mode-hook))
  (add-hook hook (lambda () (flyspell-mode 1))))
(dolist (hook '(c++-mode-hook elisp-mode-hook))
  (add-hook hook (lambda () (flyspell-prog-mode 1))))
(dolist (hook '(change-log-mode-hook log-edit-mode-hook))
  (add-hook hook (lambda () (flyspell-mode -1))))

;; Auto saving
(defun je/save-all ()
  "Save all buffers"
  (interactive)
  (desktop-save-in-desktop-dir)
  (save-some-buffers t))

(run-with-idle-timer 60 t 'je/save-all)
(global-set-key (kbd "s-S") 'je/save-all)

;;;; User info
(setq user-full-name "Quinn K.")
(setq user-mail-address "qkarpowtiz@gmail.com")

;; syntax highlight everywhere
(global-font-lock-mode t)

;; Display the line and column number in the modeline
(setq line-number-mode t)
(setq column-number-mode t)
(line-number-mode t)
(column-number-mode t)

;; Don't get weird properties when pasting
(setq yank-excluded-properties t)

;; Don't open new annoying windows under X, use the echo area
(when t
  (tooltip-mode -1))
(setq initial-buffer-choice "~/.emacs.el")

;Copy, paste and cut
(global-set-key (kbd "C-c") 'kill-ring-save)
(global-set-key (kbd "C-S-C") 'kill-region)
(global-set-key (kbd "C-v") 'yank)

;Eval
(global-set-key (kbd "C-t") 'eval-buffer)

;i-search TODO
;;(global-set-key (kbd "C-f") 'i-search)

;Undo shortcut
(global-set-key (kbd "C-z") 'undo-only)

;menu bar mode
(global-set-key (kbd "C-S-M") 'menu-bar-mode)

;buffer rotation
(progn
(global-set-key (kbd "C-x C-b") 'ibuffer))
(define-key global-map (kbd "C-`") 'ibuffer)

;text size shortcuts
(define-key global-map (kbd "C-=") 'text-scale-increase)
(define-key global-map (kbd "C--") 'text-scale-decrease)

;Dired
(define-key global-map (kbd "C-d") 'dired)

;Deleting
(setq shift-select-mode 1)
(delete-selection-mode 1)

;;Encrypting

;.gpg
(defvar pgg-gpg-user-id "user")
(autoload 'pgg-make-temp-file "pgg" "PGG")
(autoload 'pgg-gpg-decrypt-region "pgg-gpg" "PGG GnuPG")
(define-generic-mode 'gpg-file-mode
  (list ?#) 
  nil nil
  '(".gpg\\'" ".gpg-encrypted\\'")
  (list (lambda ()
	    (add-hook 'before-save-hook
                      (lambda () 
                        (let ((pgg-output-buffer (current-buffer)))
                          (pgg-gpg-encrypt-region (point-min) (point-max)
                                                  (list pgg-gpg-user-id))))
                      nil t)
	    (add-hook 'after-save-hook 
		      (lambda ()
                        (let ((pgg-output-buffer (current-buffer)))
                          (pgg-gpg-decrypt-region (point-min) (point-max)))
			(set-buffer-modified-p nil)
			(auto-save-mode nil))
		      nil t)
            (let ((pgg-output-buffer (current-buffer)))
              (pgg-gpg-decrypt-region (point-min) (point-max)))
	    (auto-save-mode nil)
	    (set-buffer-modified-p nil)))
  "Mode for gpg encrypted files")

;no more scratch startup stuff
(setq initial-scratch-message nil)

;yes or no prompt
(fset 'yes-or-no-p 'y-or-n-p)

;;show matching parens
(show-paren-mode t)
(setq show-paren-style 'expression)
(setq show-paren-delay 0.0)

;; Show matching paren, even if off-screen
(defadvice show-paren-function
  (after show-matching-paren-offscreen activate)
  "If the matching paren is offscreen, show the matching line in the
        echo area. Has no effect if the character before point is not of
        the syntax class ')'."
  (interactive)
  (if (not (minibuffer-prompt))
      (let ((matching-text nil))
        ;; Only call `blink-matching-open' if the character before point
        ;; is a close parentheses type character. Otherwise, there's not
        ;; really any point, and `blink-matching-open' would just echo
        ;; "Mismatched parentheses", which gets really annoying.
        (if (char-equal (char-syntax (char-before (point))) ?\))
            (setq matching-text (blink-matching-open)))
        (if (not (null matching-text))
            (message matching-text)))))

(set-face-background 'show-paren-match-face "turquoise")

;; Highlight selection
(transient-mark-mode t)

;Compiler
(defun run-current-file (writep)
  "Execute or compile the current file."
  (interactive)
  (let (suffixMap fname suffix progName cmdStr)
    ;; a keyed list of file suffix to comand-line program path/name
    (setq suffixMap
          '(("rkt" . "racket")
	    ("java" . "javai")
	    ("py" . "python")))

    (save-buffer)

    (setq fname (buffer-file-name))
    (setq suffix (file-name-extension fname))
    (setq progName (cdr (assoc suffix suffixMap)))
    (setq cmdStr (concat "-i -c \'" progName " \""   fname "\"\'"))

    (if (string-equal suffix "el") ; special case for emacs lisp
        (load-file fname)
      (if progName
          (progn
            (message "Running...")
            (if (not writep)
                (compile (concat "sh " cmdStr))
              (let ((multi-term-program-switches 
                     (list "-i" "-c" (concat progName " \"" fname "\""))))
                (multi-term-dedicated-open))))
        (progn
          (message "No recognized program file suffix for this file."))))))
(defun run-current-file-ro () 
  "Execute or compile the current file."
  (interactive)
  (run-current-file nil))
(defun run-current-file-wr () 
  "Execute or compile the current file."
  (interactive)
  (run-current-file t))

(global-set-key (kbd "C-t") 'run-current-file-ro)
(global-set-key (kbd "C-M-t") 'run-current-file-wr)

;;Game scores

;snake scores file
(setq snake-score-file
    "~/.emacs.d/snake-scores")

;tetris score files
(setq tetris-score-file
  "~/.emacs.d/tetris-scores")

;bubbles score files
(setq bubbles-score-file
  "~/.emacs.d/bubbles-scores")
