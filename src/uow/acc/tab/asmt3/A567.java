package uow.acc.tab.asmt3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import uow.acc.tab.asmt3.memoryManagement.BTree;
import uow.acc.tab.asmt3.memoryManagement.IndexMinPQ;
import uow.acc.tab.asmt3.memoryManagement.StdOut;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class A567 {
    private final static String path = "data/";

    public static void main(String[] args) {
        //task5();
        //task6();
        task7();  // include t5 and t6
    }

    /**
     * Separte file into different pieces
     * @param f input file
     * @param fs output files
     */
    private static void separte(String f, String[] fs) throws Exception {
        In inf = new In(path + f);
        String[] reads = inf.readAllStrings();
        int N = fs.length;
        int ptmp = reads.length / N;
        for (int j = 0; j < N; j++) {
            Out o = new Out(path + fs[j]);
            for (int i = 0; i < ptmp; i++) {
                o.println(reads[j * ptmp + i]);
            }
        }
    }

    /**
     * Sorting file
     * @param fin input file (unsorted)
     * @param fout output file (sorted)
     */
    private static void sortfile(String fin, String fout) throws Exception {
        In us = new In(path + fin);
        String[] stmp = us.readAllStrings();
        Arrays.sort(stmp);
        Out so = new Out(path + fout);
        for (String tmp : stmp) {
            so.println(tmp);
        }
    }

    /**
     * merge files to a big file(in order)
     * @param fin file names
     * @param fout final file
     * @throws Exception
     */
    private static void merge(String[] fin, String fout) throws Exception {
        if (fin.length > 2) throw new Exception("input files are more than 2.");
        String[] a = new In(path + fin[0]).readAllStrings();
        String[] b = new In(path + fin[1]).readAllStrings();
        Out fo = new Out(path + fout);
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) < 0) {
                fo.println(a[i]);
                i++;
            } else {
                fo.println(b[j]);
                j++;
            }
        }
        while(i < a.length) {
            fo.println(a[i]);
            i++;
        }
        while(j < b.length) {
            fo.println(b[j]);
            j++;
        }
    }

    /**
     * Task5 in Assignment3, Create by Tab Tu, On Nov.7 2017
     */
    public static void task5() {
        String infile = "Chip-seq-reads-1M.dat";
        String[] files = { "A.dat", "B.dat", "C.dat", "D.dat" };  // , "E.dat", "F.dat", "G.dat", "H.dat"};
        String[] sfiles = { "AS.dat", "BS.dat", "CS.dat", "DS.dat" };  // , "ES.dat", "FS.dat", "GS.dat", "HS.dat"};
        String outfile = "Chip-seq-reads-1M.sorted.dat";

        try {
            // start to log
            System.out.println("start...");
            long start = System.currentTimeMillis();

            // separte files
            separte(infile, files);
            System.out.println("separte files: " + (System.currentTimeMillis() - start) + "ms");

            // sort strings
            for (int i = 0; i < files.length; i++) {
                sortfile(files[i], sfiles[i]);
            }
            System.out.println("sort files: " + (System.currentTimeMillis() - start) + "ms");

            // merge files
            for (int j = 1; j < sfiles.length; j++) {
                if (j == 1) {
                    String[] first = { sfiles[j], sfiles[j - 1] };
                    merge(first, outfile);
                } else {
                    String[] tmp = {outfile, sfiles[j]};
                    merge(tmp, outfile);
                }
            }

            // show result
            System.out.println("merge files: " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("end...");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Task6 in Assignment3, Create by Tab Tu, On Nov.7 2017
     */
    public static void task6() {
        //task5();
        String infile = "Chip-seq-reads-1M.sorted.dat";
        //String infile = "AS.dat";
        String[] ele = new In(path + infile).readAllStrings();
        BTree<String, String> st = new BTree<String, String>();

        long start = System.currentTimeMillis();
        String pri = ele[0];
        int tms = 1;
        for (int i = 1; i < ele.length; i++) {
            if (ele[i].compareTo(pri) == 0) {
                tms++;
            } else {
                st.put(pri, tms + "");
                pri = ele[i];
                tms = 1;
            }
        }

        System.out.println("build B-tree cost: " + (System.currentTimeMillis() - start) + "ms");
        //StdOut.println("test: " + st.get("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        StdOut.println("size: " + st.size());
        StdOut.println("height: " + st.height());

        String btfile = "B-tree.dat";
        st.traversal(btfile);
    }

    public static void task7() {
        long start = System.currentTimeMillis();
        task5();
        long task5 = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        task6();
        long task6 = System.currentTimeMillis() - start;
        System.out.println("Task5: " + task5);
        System.out.println("Task6: " + task6);
        System.out.println("Total For T5&T6: " + (task5 + task6));
    }
}
