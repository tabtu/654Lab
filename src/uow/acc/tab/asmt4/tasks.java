package uow.acc.tab.asmt4;

import uow.acc.tab.asmt4.textprocessing.*;
import uow.acc.tab.asmt4.utils.Func;

public class tasks {

    private final static String path = "data/";

    public static void main(String[] args) {
        //task1();
        task2();
        //task3();
        //task4();
    }

    /**
     * Task1 in Assignment4, Create by Tab Tu, On Nov.14 2017
     *
     */
    public static void task1() {
        String txt = new In(path + "Hard disk.txt").readAll();
        String[] pats = {"hard", "disk", "hard disk", "hard drive", "hard dist", "xltpru"};

        long start;
        int N = 10000;
        double bf, bm, kmp;
        bf = bm = kmp = 0;

        for (int i = 0; i < N; i++) {
            // BF
//            StdOut.println("BruteForceMatch:");
            start = System.currentTimeMillis();
            for (String each : pats) {
                int offset = BruteForceMatch.search1(each, txt);
                if (offset == txt.length()) {
//                    StdOut.println("Can not find " + each);
                    continue;
                }
//                StdOut.println(each + " at pos " + offset);
            }
            bf += (System.currentTimeMillis() - start);

            // BM
//            StdOut.println("BoyerMoore:");
            start = System.currentTimeMillis();
            for (String each : pats) {
                BoyerMoore boyermoore = new BoyerMoore(each);
                try {
//                    StdOut.println(each + " at pos " + boyermoore.search(txt));
                } catch (Exception e) {
//                    StdOut.println("Can not find " + each);
                }
            }
            bm += (System.currentTimeMillis() - start);

            // KMP
//            StdOut.println("KMP:");
            start = System.currentTimeMillis();
            for (String each : pats) {
                KMP kmp0 = new KMP(each);
                try {
//                    StdOut.println(each + " at pos " + kmp0.search(txt));
                } catch (Exception e) {
//                    StdOut.println("Can not find " + each);
                }
            }
            kmp += (System.currentTimeMillis() - start);
        }

        StdOut.println("BruteForceMatch: " + bf + "ms for " + N + " rounds and average CPU time for each searching is " + bf/N + "ms");
        StdOut.println("BoyerMoore: " + bm + "ms for " + N + " rounds and average CPU time for each searching is " + bm/N + "ms");
        StdOut.println("KMP: " + kmp + "ms for " + N + " rounds and average CPU time for each searching is " + kmp/N + "ms");
    }

    /**
     * Task2 in Assignment4, Create by Tab Tu, On Nov.14 2017
     *
     */
    public static void task2() {

        String[] key = { "protein", "complex", "PPI", "prediction", "physicochemical", "interface", "interactions", "complex", "individual", "predictions" };
        String[] content = Func.getwords(new In(path + "Protein.txt").readAll());
        TST<Integer> st = new TST<Integer>();

        // print keys
        StdOut.print("keys : ");
        for(String each : key) {
            StdOut.print(each + ", ");
        }
        StdOut.println();

        // find keys
        long s = System.currentTimeMillis();
        for (int i = 0; i < content.length; i++) {
            st.put(content[i], i);
        }
        double time = System.currentTimeMillis() - s;
        for (String each : key) {
            StdOut.println("key = shells, value = " + st.get(each));
        }
        StdOut.println("It cost " + time + "ms to find these " + key.length + " keys");
        StdOut.println("So, average time for each round is " + time/key.length + "ms");
    }

    /**
     * Task3 in Assignment3, Create by Tab Tu, On Nov.14 2017
     *
     */
    public static void task3() {

    }

    public static void task4() {

    }
}
