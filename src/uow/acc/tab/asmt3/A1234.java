package uow.acc.tab.asmt3;

import uow.acc.tab.asmt3.graphs.*;

public class A1234 {

    private final static String path = "document/Assignment3/data/";

    /**
     * Task1 in Assignment3, Create by Tab Tu, On Oct.31 2017
     *
     */
    public static void task1() {

        In in = new In(path + "largeDG.txt");
        Digraph G = new Digraph(in);
        long start = System.currentTimeMillis();
        DepthFirstOrder dfs = new DepthFirstOrder(G);
        long dsf = System.currentTimeMillis() - start;
        /*
        StdOut.println("   v  pre post");
        StdOut.println("--------------");
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }
        */
        start = System.currentTimeMillis();
        StdOut.print("Preorder: ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        long pre = System.currentTimeMillis() - start;
        StdOut.println();

        start = System.currentTimeMillis();
        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        long pos = System.currentTimeMillis() - start;
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        start = System.currentTimeMillis();
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        long rev = System.currentTimeMillis() - start;

        System.out.println("DFS: " + dsf);
        System.out.println("Preorder: " + pre);
        System.out.println("Postorder: " + pos);
        System.out.println("Reverse postorder: " + rev);
        StdOut.println();
    }

    /**
     * Task2 in Assignment3, Create by Tab Tu, On Oct.31 2017
     *
     */
    public static void task2() {

        // a
        In in0 = new In(path + "largeEWG.txt");
        long start = System.currentTimeMillis();
        EdgeWeightedDigraph G0 = new EdgeWeightedDigraph(in0);
        long ewd = System.currentTimeMillis() - start;
        //StdOut.println(G0);

        // b
        //In in = new In(args[0]);
        In in = new In(path + "largeEWG.txt");
        start = System.currentTimeMillis();
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        long kmst = System.currentTimeMillis() - start;
        for (Edge e : mst.edges()) {
            //StdOut.println(e);
        }
        //StdOut.printf("%.5f\n", mst.weight());

        System.out.println("Edge Weighted Digraph: " + ewd);
        System.out.println("KurskalMST: " + kmst);
    }

    /**
     * Task3 in Assignment3, Create by Tab Tu, On Oct.31 2017
     *
     */
    public static void task3() {
        long start = System.currentTimeMillis();
        SymbolGraph sg = new SymbolGraph(path + "movies.txt", "/");
        Graph G = sg.G();
        CC cc = new CC(G);
        System.out.println("SymbolGraph: " + (System.currentTimeMillis() - start));
        // number of connected components
        int M = cc.count();
        StdOut.println(M + " components");
        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }
        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

    public static void task4() {
        String[] names = {"DiCaprio, Leonardo", "Roberts, Julia (I)", "Grant, Hugh (I)"};
        SymbolGraph sg = new SymbolGraph(path + "movies.txt", "/");
        Graph G = sg.G();
        for (int i = 0; i <= names.length; i++) {
            if (i == names.length) {
                System.out.println(names[i - 1] + " and " + names[i - 2] + " both: ");
                if (sg.contains(names[i - 1]) && sg.contains(names[i - 2])) {
                    int s = sg.index(names[i - 1]);
                    int s2 = sg.index(names[i - 2]);
                    for (int v : G.adj(s)) {
                        for (int j : G.adj(s2)) {
                            if (sg.name(v) == sg.name(j))
                                StdOut.println("   " + sg.name(v));
                        }
                    }
                } else {
                    StdOut.println("input does not contain '" + names[0] + " and " + names[1] + "'\n");
                }
                break;
            }
            System.out.println(names[i] + ": ");
            if (sg.contains(names[i])) {
                int s = sg.index(names[i]);
                for (int v : G.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input does not contain '" + names[i] + "'\n");
            }
        }
    }

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
    }
}
