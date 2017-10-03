package uow.acc.tab.asmt2;

import uow.acc.tab.asmt2.exts.ExtFunc;
import uow.acc.tab.asmt2.sorting.Sort;
import uow.acc.tab.asmt2.sorting.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Task2 {

    public static void task2 (int nums, int tms) {
        long start, end;
        String tums = "MergeSort:   QuickSort:  HeapSort:   dual pivot sorting: ";
        System.out.println(tums);
        for (int j = 0; j < tms; j++) {
            tums = "";
            Long [ ] a = new Long[ nums ];
            //Random rand = new Random(System.currentTimeMillis());

            // Fill array a with random numbers
            for( int i = 0; i < a.length; i++ ) {
                //a[i] = rand.nextLong();
                a[i] = ExtFunc.getRandomNumber(Long.MAX_VALUE);
            }
            // MergeSort
            start = System.currentTimeMillis();
            Sort.mergeSort(a);
            end = System.currentTimeMillis();
            Sort.checkSort(a);
            tums += (end - start);

            // Fill array a with random numbers
            for( int i = 0; i < a.length; i++ ) {
                //a[i] = rand.nextLong();
                a[i] = ExtFunc.getRandomNumber(Long.MAX_VALUE);
            }
            // QuickSort
            start = System.currentTimeMillis();
            Sort.quicksort(a);
            end = System.currentTimeMillis();
            Sort.checkSort( a );
            tums += "   " + (end - start);

            // Fill array a with random numbers
            for( int i = 0; i < a.length; i++ ) {
                //a[i] = rand.nextLong();
                a[i] = ExtFunc.getRandomNumber(Long.MAX_VALUE);
            }
            // HeapSort
            start = System.currentTimeMillis();
            Sort.heapsort(a);
            end = System.currentTimeMillis();
            Sort.checkSort( a );
            tums += "   " + (end - start);

            // Fill array a with random numbers
            for( int i = 0; i < a.length; i++ ) {
                //a[i] = rand.nextLong();
                a[i] = ExtFunc.getRandomNumber(Long.MAX_VALUE);
            }
            // Sort dual pivot sorting
            start = System.currentTimeMillis();
            Arrays.sort(a);
            end = System.currentTimeMillis();
            //Sort.print(a);
            Sort.checkSort( a );
            tums += "   " + (end - start);
            System.out.println(tums);
            /*
            // Sorting String objects
            // Fill array a with random numbers
            String [ ] b = new String[ NUM_ITEMS];

            for( int i = 0; i < a.length; i++ )
                b[i] = ExtFunc.getRandomString(10);
            // MergeSort
            start = System.currentTimeMillis();
            Sort.mergeSort(b);
            end = System.currentTimeMillis();
            Sort.checkSort(b);

            // Fill array a with random numbers
            for( int i = 0; i < a.length; i++ ) a[i] = rand.nextLong();
            // Quick select
            start = System.currentTimeMillis();
            int k = 10; // k must be greater than 0 and less than n
            Sort.quickSelect( a, k); //NUM_ITEMS / 2 );
            end = System.currentTimeMillis();
            System.out.println("Quickselect:");
            //Sort.print(a);


            System.out.println("kth smallest = " + a[k-1]);
            */
        }

    }

    public static void task3 (int nums, int tms) {
        List<String> lst = new ArrayList<>( );
        Random r = new Random( );

        final int LEN = 3;

        for( int i = 0; i < 1000000; i++ )
        {
            String str = "";
            int len = LEN; //3 + r.nextInt( 7 ); // between 3 and 9 characters

            for( int j = 0; j < len; j++ )
                str += (char) ( 'a' + r.nextInt( 26 ) );

            lst.add( str );
        }

        String [ ] arr1 = new String[ lst.size( ) ];
        String [ ] arr2 = new String[ lst.size( ) ];

        lst.toArray( arr1 );
        lst.toArray( arr2 );

        // Print unsorted array
        //print(arr1);

        long start, end;

        start = System.currentTimeMillis( );
        Arrays.sort( arr1 );
        end = System.currentTimeMillis( );
        System.out.println( "Quicksort: " + ( end - start ) );

        start = System.currentTimeMillis( );
        RadixSort.radixSortA( arr2, LEN );
        end = System.currentTimeMillis( );
        System.out.println( "Radix sort: " + ( end - start ) );

        for( int i = 0; i < arr1.length; i++ )
            if( !arr1[ i ].equals( arr2[ i ]  ) )
                System.out.println( "OOPS!!" );

        // Print sorted array
        //print(arr2);
    }

    public static void main(String[] args) {

        int NUM_ITEMS = 100000;
        int TMS = 1000;

        task2(NUM_ITEMS, TMS);
    }
}
