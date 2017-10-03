package uow.acc.tab.asmt1;

import uow.acc.tab.asmt1.exts.ExtFunc;
import uow.acc.tab.asmt1.hashTable.CuckooHashTable;
import uow.acc.tab.asmt1.hashTable.QuadraticProbingHashTable;
import uow.acc.tab.asmt1.hashTable.SeparateChainingHashTable;
import uow.acc.tab.asmt1.hashTable.StringHashFamily;

/**
 * Answer Sheet for Assignment 1 Task2 1 - 3
 * Written by Tab Tu
 * Updated Sep.25 2017
 */
public class A1_3 {

    public static CuckooHashTable<String> q1(int n) {
        CuckooHashTable<String> Hc = new CuckooHashTable<>(new StringHashFamily(3), 2000);
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Hc.insert(ExtFunc.getRandomString(10));
        }
        end = System.currentTimeMillis();
        long tsum = end - start;
        System.out.println("The total insert time for " + n + " insertion in CuckooHashTable is : " + tsum + "ms.");
        return Hc;
    }

    public static int q2(CuckooHashTable<String> hc, int n) {
        long start, end;
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (hc.remove(ExtFunc.getRandomString(10))) {
                count++;
            }
        }
        end = System.currentTimeMillis();
        long tsum = end - start;
        System.out.println("The total random delete time is : " + tsum + "ms.");
        return count;
    }

    public static void q3(int NUMS) {
        for (int k = 1; k < 20; k++) {
            System.out.println("Fill in the table...");

            long start, end;
            int n = 1;
            for (int tn = 0; tn < 20; tn++) {
                n *= 2;
                System.out.print("n = " + n + " | ");
                String tmus = "";

                CuckooHashTable<String> Hc = new CuckooHashTable<>(new StringHashFamily(3), 2000);
                QuadraticProbingHashTable<String> Hq = new QuadraticProbingHashTable<>();
                SeparateChainingHashTable<Integer> Hs = new SeparateChainingHashTable<>();

                // Insert example
                start = System.currentTimeMillis();
                for (int i = 0; i < NUMS; i++) {
                    // Cuckoo
                    Hc.insert(ExtFunc.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hc_i;";
                start = System.currentTimeMillis();
                for (int i = 0; i < NUMS; i++) {
                    // QuadraticProbing
                    Hq.insert(ExtFunc.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hq_i;";
                start = System.currentTimeMillis();
                for (int i = 0; i < NUMS; i++) {
                    // SeparateChaining
                    Hs.insert(ExtFunc.getRandomNumber(Integer.MAX_VALUE));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hs_i;";

                // Remove example
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hc.remove(ExtFunc.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hc_r;";
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hq.remove(ExtFunc.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hq_r;";
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hs.remove(ExtFunc.getRandomNumber(Integer.MAX_VALUE));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hs_r;";

                System.out.println(tmus);
            }
            System.out.println("Finished.");
        }
    }

    public static void main( String [ ] args ) {
        final int n = 10000000;
        final int NUMS = 2000000;

        System.out.println(q2(q1(n), n));
        q3(NUMS);
    }
}
