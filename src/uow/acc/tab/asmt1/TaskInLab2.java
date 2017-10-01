package uow.acc.tab.asmt1;

import uow.acc.tab.asmt1.exts.ExtFunc;
import uow.acc.tab.asmt1.hashTable.CuckooHashTable;
import uow.acc.tab.asmt1.hashTable.QuadraticProbingHashTable;
import uow.acc.tab.asmt1.hashTable.SeparateChainingHashTable;
import uow.acc.tab.asmt1.hashTable.StringHashFamily;

public class TaskInLab2 {

    public static String RandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer SB = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++){
            SB.append(KeyString.charAt((int) Math.random() * (len - 1)));
        }
        return SB.toString();
    }

    public static int RandomNumber(int max) {return (int)Math.round(Math.random() * max);}

    public static CuckooHashTable<String> qu1(int n) {
        CuckooHashTable<String> Hc = new CuckooHashTable<>(new StringHashFamily(3), 2000);
        long start, end;
        double aver1, aver2;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Hc.insert(RandomString(5));
        }
        end = System.currentTimeMillis();
        long ts = end - start;
        System.out.println("Insert " + n + " Times, Insertion Time is :" + ts + "ms.");
        return Hc;
    } //insert n random numbers

    public static int qu2(CuckooHashTable<String> hc, int n) {
        long start, end;
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (hc.remove(RandomString(5))) {
                count++;
            }
        }
        end = System.currentTimeMillis();
        long ts = end - start;
        System.out.println("Random Deletion time is: " + ts + "ms.");
        return count;
    } //delete the random number, which is found

    public static void qu3(int NUM) {
        System.out.println("Fill in the table.");

        long start, end;
        int n = 1;
        for (int tnum = 0; tnum < 20; tnum++) {
            n *= 2;
            System.out.println("n = " + n);

            CuckooHashTable<String> Hc = new CuckooHashTable<>(new StringHashFamily(3), 2000);
            QuadraticProbingHashTable<String> Hq = new QuadraticProbingHashTable<>();
            SeparateChainingHashTable<Integer> Hs = new SeparateChainingHashTable<>();



            //Cuckoo
            start = System.currentTimeMillis();
            for (int i = 0; i < NUM; i++) {
                Hc.insert(RandomString(5));
            }
            end = System.currentTimeMillis();
            System.out.println((end - start) + " CuckooHashTableInsertion");

            //QuadraticProbing
            start = System.currentTimeMillis();
            for (int i = 0; i < NUM; i++) {
                Hq.insert(RandomString(5));
            }
            end = System.currentTimeMillis();
            System.out.println((end - start) + " QuadraticProbingHashTableInsertion");

            //SeparateChaining

            start = System.currentTimeMillis();
            for (int i = 0; i < NUM; i++) {
                // SeparateChaining
                Hs.insert(ExtFunc.getRandomNumber(NUM));
            }
            end = System.currentTimeMillis();
            /*
            start = System.currentTimeMillis();
            for (int i = 0; i < NUM; i++) {
                Hs.insert(RandomNumber(Integer.MAX_VALUE));
            }
            end = System.currentTimeMillis();*/
            System.out.println((end - start) + " SeparateChainingHashTableInsertion");


            //
            start = System.currentTimeMillis();
            for (int i = 0; i < NUM; i++) {
                Hc.remove(RandomString(5));
            }
            System.out.println((end - start) + " CuckooHashTableInsertionRemoving");

            //
            start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                //Remove
                Hq.remove(RandomString(5));
            }
            end = System.currentTimeMillis();
            System.out.println((end - start) + " QuadraticProbingHashTableRemoving");

            start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                Hs.remove(RandomNumber(Integer.MAX_VALUE));
            }
            end = System.currentTimeMillis();
            System.out.println((end - start) + " SeparateChainingHashTableRemoving");
        }
        System.out.println("Done.");
    }

    public static void main(String[] args) {
        final int n = 10000000;
        final int NUM = 20000000;

        //CuckooHashTable<String> task1 = qu1(n);

        //int task2 = qu2(task1, n);

        qu3(NUM);
    }
}