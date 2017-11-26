package uow.acc.tab.asmt4;

import uow.acc.tab.asmt4.textprocessing.*;
import uow.acc.tab.asmt4.utils.Func;

public class tasks {

    private final static String DATA_PATH = "data/";

    public static void main(String[] args) {
        task1();
//        task2();
//        task3();
//        task4();
//        task5();
    }

    /**
     * Task1 in Assignment4, Create by Tab Tu, On Nov.14 2017
     */
    public static void task1() {
        String txt = new In(DATA_PATH + "Hard disk.txt").readAll();
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

        StdOut.println("BruteForceMatch: " + bf + "ms for " + N + " rounds and average CPU time for each searching is " + bf / N + "ms");
        StdOut.println("BoyerMoore: " + bm + "ms for " + N + " rounds and average CPU time for each searching is " + bm / N + "ms");
        StdOut.println("KMP: " + kmp + "ms for " + N + " rounds and average CPU time for each searching is " + kmp / N + "ms");
    }

    /**
     * Task2 in Assignment4, Create by Tab Tu, On Nov.14 2017
     */
    public static void task2() {

        String[] key = {"protein", "complex", "PPI", "prediction", "physicochemical", "interface", "interactions", "complex", "individual", "predictions"};
        String[] content = Func.getwords(new In(DATA_PATH + "Protein.txt").readAll());
        TST<Integer> st = new TST<Integer>();

        // print keys
        StdOut.print("keys : ");
        for (String each : key) {
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
        StdOut.println("So, average time for each round is " + time / key.length + "ms");
    }

    /**
     * Task3 in Assignment4, Create by Tab Tu, On Nov.21 2017
     */
    public static void task3() {
        String sourcepath = DATA_PATH + "W3C Web Pages/";
        String targetpath = DATA_PATH + "asmt4text/";
        String[] list = Func.getFilesFromPath(sourcepath);
        for (String each : list) {
            String tmp = Func.readFile2String(sourcepath + each);
            String tmot = each.substring(0, each.length() - 4) + ".txt";
            Func.writeString2File(targetpath + tmot, tmp);
        }
        StdOut.println("Completed transfer " + list.length + " files into " + targetpath);
    }

    /**
     * Task4 in Assignment4, Create by Tab Tu, On Nov.21 2017
     */
    public static void task4() {
        String path = DATA_PATH + "asmt4text/";
        String[] list = Func.getFilesFromPath(path);

        String ppn = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
        String reppn = "";
        String pea = "[\\w]+@[\\w]+\\.[com|net|ca|cn|cc|org]";
        String repea = "";

        for (String each : list) {
            String tmp = Func.readFile2String(path + each);
            String phonenumber = Func.findByPattern(ppn, tmp);
            String emailaddress = Func.findByPattern(pea, tmp);
            if (phonenumber.length() > 0) {
                reppn += "\n\nFile name: " + each + ": ";
                reppn += phonenumber;
            }
            if (emailaddress.length() > 0) {
                repea += "\n\nFile name: " + each + ": ";
                repea += emailaddress;
            }
        }

        StdOut.print("\n------------Find Phone Number: ------------");
        StdOut.println(reppn);
        StdOut.print("\n\n------------Find Email Address: ------------");
        StdOut.println(repea);
    }

    /**
     * Task5 in Assignment4, Create by Tab Tu, On Nov.21 2017
     */
    public static void task5() {
        String path = DATA_PATH + "W3C Web Pages/";
        String[] list = Func.getFilesFromPath(path);

        //String urlReg = "<a href\\s?=\\s?\"(http://|https://)?(www.)?([a-zA-Z0-9]+)\\.[a-zA-Z0-9]*\\.?[a-zA-Z]{2,3}.?([a-z]+)?\"\\s?>";
        String w3Rega = "<a href\\s?=\\s?\"(http://|https://)?(www.)?(w3.org)\"\\s?>";
        String rea = "";
        String w3Regb = "<a href\\s?=\\s?\"(http://|https://)?(www.)?([a-zA-Z0-9]+)\\.[a-zA-Z0-9]*\\.?[a-zA-Z]{2,3}.?([a-z]+)?(/[a-zA-Z0-9]+)+\"\\s?>";
        String reb = "";
        String w3Regc = "<a href\\s?=\\s?\"(http://|https://)?(www.)?([a-zA-Z0-9]+)\\.[a-zA-Z0-9]*\\.?[a-zA-Z]{2,3}.?([a-z]+)?(/[a-zA-Z0-9]+)*(/#[a-zA-Z0-9]+)+\"\\s?>";
        String rec = "";
        String w3Regd = "<a href\\s?=\\s?\"(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.(.net|.com|.org)\"\\s?>";
        String red = "";

        for (String each : list) {
            String tmp = Func.readFile2String(path + each);
            String tmpa = Func.findByPattern(w3Rega, tmp);
            String tmpb = Func.findByPattern(w3Regb, tmp);
            String tmpc = Func.findByPattern(w3Regc, tmp);
            String tmpd = Func.findByPattern(w3Regd, tmp);
            if (tmpa.length() > 0) {
                rea += "\n\nFile name: " + each + ": ";
                rea += tmpa;
            }
            if (tmpb.length() > 0) {
                reb += "\n\nFile name: " + each + ": ";
                reb += tmpb;
            }
            if (tmpc.length() > 0) {
                rec += "\n\nFile name: " + each + ": ";
                rec += tmpc;
            }
            if (tmpd.length() > 0) {
                red += "\n\nFile name: " + each + ": ";
                red += tmpd;
            }
        }

        StdOut.print("------------Find links with domain w3.org: ------------");
        StdOut.println(rea);
        StdOut.print("------------Find links that contain folders: ------------");
        StdOut.println(reb);
        StdOut.print("------------Find links that contain references: ------------");
        StdOut.println(rec);
        StdOut.print("------------Find links with domain .net, .com, .org: ------------");
        StdOut.println(red);
    }
}

