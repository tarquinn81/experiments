import java.util.*;
public class Sortingnumbers
{
	public static int[] randomgenerator()
	{
		int[] data = new int[100];
		for ( int a=0; a<99; a++)
		{
			Random rand = new Random( );
			data[a] = rand.nextInt(99);
		}
		return data;
	}
	public static void Bubblesort( int[] data )
	{
		   int tmp,i,j, n = 100;
		   float start_time, end_time, time;
		   start_time = System.nanoTime();
		   for (i=0; i<n-1; i++)
		   {
		       for (j=0; j<n-i-1; j++)
		            if (data[j] > data[j+1]) 
		           {
		               tmp = data[j];
		               data[j] = data[j+1];
		               data[j+1] = tmp;
		           }
		   }
		   end_time = System.nanoTime();
		   time = end_time - start_time;
		   System.out.println("Time: " + (time / 1000000) );
		   System.out.println("New Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c] + " ");
		   }
	}
	public static int[] Quicksort( int[] data, int left, int right )
	{
		   int mid,tmp,i,j;
		   i = left;
		   j = right;
		   mid = data[(left + right)/2];
		   do {
		        while(data[i] < mid)
		           i++;
		       while(mid < data[j])
		           j--;
		       if (i <= j) 
		       {
		           tmp = data[i];
		           data[i] = data[j];
		           data[j] = tmp;
		           i++;
		           j--;
		       }
		   } while (i <= j);
		   if (left < j) Quicksort(data,left,j);
		   if (i < right) Quicksort(data,i,right);
		   return data;
	}
	public static void Selectsort (int data[],int n) 
	{
		   int min,tmp,i,j,min_id = 0;
		   float start_time, end_time, time;
		   start_time = System.nanoTime();
		   for (i=0; i<n-1; i++) {
		       min = data[i];
		       for (j=i+1; j<n; j++)
		           if (data[j] < min) {
		              min = data[j];
		              min_id = j;
		           }
		       tmp = data[i];
		       data[i] = data[min_id];
		       data[min_id] = tmp;
		   }
		   end_time = System.nanoTime();
		   time = end_time - start_time;
		   System.out.println("Time:" + (time/1000000) );
		   System.out.println("Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c] + " ");
		   }
		}
	public static void Insertionsort (int data[],int n) 
	{
		   int tmp,i,j;
		   float start_time, end_time, time;
		   start_time = System.nanoTime();
		   for (j=1; j<n; j++) {
		       i =j - 1;
		       tmp = data[j];
		       while ( (i>=0) && (tmp < data[i]) ) {
		           data[i+1] = data[i];
		           i--;
		       }
		       data[i+1] = tmp;
		   }
		   end_time = System.nanoTime();
		   time = end_time - start_time;
		   System.out.println("Time: " + (time / 1000000) );
		   System.out.println("New Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c] + " ");
		   }
		}
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String numcheck;
		System.out.println("Press 1 for Bubble");
		System.out.println("Press 2 for Quicksort");
		System.out.println("Press 3 for Selection");
		System.out.println("Press 4 for Insertion");
		System.out.print("Enter a number: ");
		numcheck = scan.nextLine();
		float endtime, starttime, time;
		int[] rand = randomgenerator();
		switch(numcheck)
		{
			case "1" : 
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c] + " ");
				}
				System.out.println("");
				System.out.println("Bubblesort:");
				Bubblesort(rand); 
				break;
			case "2" :
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c] + " ");
				}
				System.out.println("");
				starttime = System.nanoTime();
				System.out.println("Quicksort:");
				int[] data = Quicksort(rand, 0, 99);
				endtime = System.nanoTime();
				time = endtime - starttime;
				System.out.println("Time:" + (time / 1000000) );
				System.out.println("New Data");
				for (int c=0; c<99; c++)
				{
					System.out.print(data[c] + " ");
				}
				break;
			case "3" :
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c] + " ");
				}
				System.out.println("");
				System.out.println("Selectsort:");
				Selectsort(rand, 99); 
				break;
			case "4" : 
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c] + " ");
				}
				System.out.println("");
				System.out.println("Insertionsort:");
				Insertionsort(rand, 99); 
				break;
		}
	}
}
