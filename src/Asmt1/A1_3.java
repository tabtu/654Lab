package Asmt1;

import Asmt1.hashTable.CuckooHashTable;
import Asmt1.hashTable.QuadraticProbingHashTable;
import Asmt1.hashTable.SeparateChainingHashTable;
import Asmt1.hashTable.StringHashFamily;

/**
 * Written by Tab Tu
 * Updated Sep.25 2017
 */
public class A1_3 {

    public static CuckooHashTable<String> q1(int n) {
        CuckooHashTable<String> Hc = new CuckooHashTable<>(new StringHashFamily(3), 2000);
        long start, end;
        double aver1, aver2;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Hc.insert(ExtFuns.getRandomString(10));
        }
        end = System.currentTimeMillis();
        long tsum = end - start;
        System.out.println("The total insert time for " + n + " insertion is : " + tsum + "ms.");
        return Hc;
    }

    public static int q2(CuckooHashTable<String> hc, int n) {
        long start, end;
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (hc.remove(ExtFuns.getRandomString(10))) {
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
                    Hc.insert(ExtFuns.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hc_i;";
                start = System.currentTimeMillis();
                for (int i = 0; i < NUMS; i++) {
                    // QuadraticProbing
                    Hq.insert(ExtFuns.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hq_i;";
                start = System.currentTimeMillis();
                for (int i = 0; i < NUMS; i++) {
                    // SeparateChaining
                    Hs.insert(ExtFuns.getRandomNumber(Integer.MAX_VALUE));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hs_i;";

                // Remove example
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hc.remove(ExtFuns.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hc_r;";
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hq.remove(ExtFuns.getRandomString(10));
                }
                end = System.currentTimeMillis();
                tmus += (end - start) + ",hq_r;";
                start = System.currentTimeMillis();
                for (int i = 0; i < n; i++) {
                    Hs.remove(ExtFuns.getRandomNumber(Integer.MAX_VALUE));
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

        int delcount = q2(q1(n), n);
        q3(NUMS);
    }
}
