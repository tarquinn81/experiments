package Sorting_Algorithm_Efficiency;
import java.util.*;
public class Sortingnumbers 
{
  	public static int[] randomgenerator()
	{
		int num=0, row=0;
		int[] data = null;
		for ( int a=0; a<99; a++)
		{
			Random rand = new Random( );
			num = rand.nextInt(99);
			data[row] = num;
			row++;
		}
		return data;
	}
	public static int[] Bubblesort( int[] data )
	{
		   int tmp,i,j, n = 100;
		   long start_time, end_time, time;
		   start_time = System.currentTimeMillis();
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
		   end_time = System.currentTimeMillis();
		   time = start_time - end_time;
		   System.out.println("Bubblesort:");
		   System.out.println("Time:" + time );
		   System.out.println("Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c]);
		   }
		   return data;
	}
	public static int[] Quicksort( int[] data, int left, int right )
	{
		   int mid,tmp,i,j;
		   long start_time, end_time, time;
		   start_time = System.currentTimeMillis();
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
		   end_time = System.currentTimeMillis();
		   time = start_time - end_time;
		   System.out.println("Bubblesort:");
		   System.out.println("Time:" + time );
		   System.out.println("Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c]);
		   }
		   return data;
	}
	public static int[] Selectsort (int data[],int n) 
	{
		   int min,tmp,i,j,min_id = 0;
		   long start_time, end_time, time;
		   start_time = System.currentTimeMillis();
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
		   end_time = System.currentTimeMillis();
		   time = start_time - end_time;
		   System.out.println("Bubblesort:");
		   System.out.println("Time:" + time );
		   System.out.println("Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c]);
		   }
		   return data;
		}
	public static int[] Insertionsort (int data[],int n) 
	{
		   int tmp,i,j;
		   long start_time, end_time, time;
		   start_time = System.currentTimeMillis();
		   for (j=1; j<n; j++) {
		       i =j - 1;
		       tmp = data[j];
		       while ( (i>=0) && (tmp < data[i]) ) {
		           data[i+1] = data[i];
		           i--;
		       }
		       data[i+1] = tmp;
		   }
		   end_time = System.currentTimeMillis();
		   time = start_time - end_time;
		   System.out.println("Bubblesort:");
		   System.out.println("Time:" + time );
		   System.out.println("Data:");
		   for (int c=0; c<99; c++)
		   {
			   System.out.print(data[c]);
		   }
		   return data;
		}
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String numcheck;
		System.out.println("Press 1 for Bubble");
		System.out.println("Press 2 for Selection");
		System.out.println("Press 3 for Insertion");
		System.out.println("Press 4 for Quicksort");
		System.out.print("Enter a number: ");
		numcheck = scan.nextLine();
		int[] rand = randomgenerator();
		switch(numcheck)
		{
			case "1" : 
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c]);
				}
				Bubblesort(rand); 
				break;
			case "2" :
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c]);
				}
				Quicksort(rand, 0, 99); 
				break;
			case "3" :
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c]);
				}
				Selectsort(rand, 99); 
				break;
			case "4" : 
				System.out.println("Original data:");
				for (int c=0; c<99; c++)
				{
					System.out.print(rand[c]);
				}
				Insertionsort(rand, 99); 
				break;
		}
	}
}
