package uow.acc.tab.asmt2.advancedanalysis;

import java.util.ArrayList;

public class ExtendableArray {

   public static void main( String [ ] args )
   {
	   int NITEMS = 100000;
	   int N = 10; // capacity of array list
	   int n = 0; // number of elements in extendable array
	   int c = 10;
	   double d = 1.2;
	   
	   long startTime = System.currentTimeMillis();

	   int[] exa = new int[N];
	   for( int i = 0; i < NITEMS; i++ )
	   {
		   exa[i] = i;
		   n++;
		   //System.out.println("size = " + exa.length);
		   if (n == exa.length)
		   {
			   //N = N*2; //n = n +c incremental strategy // n = n * d
			   N = (int)( N * d);
			   
			   int[] b = new int[N];
			   for (int j=0; j<n; j++)
			   {
				   b[j] = exa[j];
			   }
			   exa = b;
		   }
	   }
	   
	   long totalTime = System.currentTimeMillis() - startTime; 
	   long avg = 10000000*totalTime / NITEMS;
	   System.out.println("totalTime = " + totalTime);
	   System.out.println("avgTime = " + avg);
   }
}