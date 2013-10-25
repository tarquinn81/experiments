#!/bin/bash

# Load resources

xrdb -merge .Xresources

# Set up an icon tray

trayer --edge top --align right --SetDockType true --SetPartialStrut true --expand true --width 10 --transparent true --tint 0x191970 --height 12 &

# Set the background color<

xsetroot -solid #dd4814

# Fire up apps

exec xmonad
