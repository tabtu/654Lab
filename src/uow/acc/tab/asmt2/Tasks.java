package uow.acc.tab.asmt2;

import uow.acc.tab.asmt2.exts.ExtFunc;
import uow.acc.tab.asmt2.sorting.Sort;
import uow.acc.tab.asmt2.sorting.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tasks {

    /**
     * Task2 in Assignment2, Create by Tab Tu, On Oct.18 2017
     * @param nums The Maximum Queue Size
     * @param tms Loop Times
     */
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
        }

    }

    /**
     * Task3 in Assignment2, Create by Tab Tu, On Oct.21 2017
     * @param nums The Maximum Queue Size
     * @param tms Loop Times
     */
    public static void task3 (int nums, int tms) {
        long start, end;

        String tums = "MergeSort:   QuickSort:  HeapSort:   dual pivot sorting: ";
        System.out.println(tums);
        String [ ] b = new String[ nums];

        for (int j = 0; j < tms; j++) {
            tums = "";

            // MergeSort
            for (int i = 0; i < nums; i++) {
                b[i] = ExtFunc.getRandomString(5);
            }
            start = System.currentTimeMillis();
            Sort.mergeSort(b);
            end = System.currentTimeMillis();
            Sort.checkSort(b);
            tums += (end - start);

            // QuickSort
            for (int i = 0; i < nums; i++) {
                b[i] = ExtFunc.getRandomString(5);
            }
            start = System.currentTimeMillis();
            Sort.quicksort(b);
            end = System.currentTimeMillis();
            Sort.checkSort(b);
            tums += "   " + (end - start);

            // HeapSort
            for (int i = 0; i < nums; i++) {
                b[i] = ExtFunc.getRandomString(5);
            }
            start = System.currentTimeMillis();
            Sort.heapsort(b);
            end = System.currentTimeMillis();
            Sort.checkSort(b);
            tums += "   " + (end - start);

            // Sort dual pivot sorting
            for (int i = 0; i < nums; i++) {
                b[i] = ExtFunc.getRandomString(5);
            }
            start = System.currentTimeMillis();
            Arrays.sort(b);
            end = System.currentTimeMillis();
            Sort.checkSort(b);
            tums += "   " + (end - start);

            System.out.println(tums);
        }
    }

    public static void main(String[] args) {

        int NUM_ITEMS = 100000;
        int TMS = 1000;

        task2(NUM_ITEMS, TMS);
    }
}
