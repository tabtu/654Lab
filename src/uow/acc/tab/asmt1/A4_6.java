package uow.acc.tab.asmt1;

import uow.acc.tab.asmt1.exts.ExtFunc;
import uow.acc.tab.asmt1.searchtrees.BinarySearchTree;
import uow.acc.tab.asmt1.searchtrees.AVLTree;
//import uow.acc.tab.asmt1.searchtrees.RedBlackTree;
import uow.acc.tab.asmt1.searchtrees.SplayTree;
import uow.acc.tab.asmt1.searchtrees.RedBlackBST;

/**
 * Written by Tab Tu
 * Updated Sep.25 2017
 */
public class A4_6 {
    public static void main(String[] args) {
        int numItems = 100000;
        long start, end;
        String tmus = "";

        System.out.println("Start to Fill...");

        for (int j=0; j<10; j++) {
            // BinarySearchTree
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            // Insert with Order
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                bst.insert(i + 1);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_i;";
            // Search with Random in Order Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                bst.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_s;";
            // Remove with Order
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                bst.remove(i);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_r;";

            // AVLTree
            AVLTree<Integer> avlt = new AVLTree<>();
            // Insert in Order
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                avlt.insert(i + 1);
                avlt.checkBalance();
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_i;";
            // Search with Random in Order Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                avlt.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_s;";
            // Remove with Order
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                avlt.remove(i);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_r;";

            // RedBlackBST
            RedBlackBST<Integer, Integer> rbst = new RedBlackBST<Integer, Integer>();
            // Insert in Order
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                rbst.put(i + 1, i + 1);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_i;";
            // Search with Random in Order Tree
            start = System.currentTimeMillis();
            for (int i = 1; i < numItems; i++) {
                rbst.get(i);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_s;";
            // Remove with Order
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                rbst.delete(i);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_r;";

        /*
        // RedBlackTree
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        // Insert in Order
        start = System.currentTimeMillis();
        for (int i = 0; i < numItems; i++) {
            rbt.add(i + 1);
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_i;";
        // Search with Random in Order Tree
        start = System.currentTimeMillis();
        for (int i = 0; i < numItems; i++) {
            rbt.equals(ExtFunc.getRandomNumber(numItems));
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_s;";
        // Remove with Order
        start = System.currentTimeMillis();
        for (int i = numItems; i > 0; i--) {
            rbt.remove(i);
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_r;";
        */


            // SplayTree
            SplayTree<Integer> st = new SplayTree<>();
            // Insert in Order
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                st.insert(i + 1);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_i;";
            // Search with Random in Order Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                st.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_s;";
            // Remove with Order
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                st.remove(i);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_r;";

            // Step 5
            // BinarySearchTree
            // Insert with Random Numbers
            bst.makeEmpty();
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                bst.insert(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_iR;";
            // Search with Random in Random Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                bst.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_sR;";
            // Remove in Random
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                bst.remove(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",bst_rR;";
            bst.makeEmpty();

            // AVLTree
            // Insert with Random Numbers
            avlt.makeEmpty();
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                avlt.insert(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_iR;";
            // Search with Random in Random Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                avlt.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_sR;";
            // Remove in Random
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                avlt.remove(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",avlt_rR;";
            avlt.makeEmpty();

            // RedBlackBST
            // Insert in Order
            rbst = new RedBlackBST<Integer, Integer>();
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                int x = ExtFunc.getRandomNumber(numItems);
                rbst.put(x, x);
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_iR;";
            // Search with Random in Order Tree
            start = System.currentTimeMillis();
            for (int i = 1; i < numItems; i++) {
                rbst.get(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_sR;";
            // Remove with Order
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                rbst.delete(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",rbst_rR;";

        /*
        // RedBlackTree
        // Insert with Random Numbers
        rbt.clear();
        start = System.currentTimeMillis();
        for (int i = 0; i > numItems; i++) {
            rbt.add(ExtFunc.getRandomNumber(numItems));
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_iR;";
        // Search with Random in Random Tree
        start = System.currentTimeMillis();
        for (int i = 0; i < numItems; i++) {
            rbt.contains(ExtFunc.getRandomNumber(numItems));
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_sR;";
        // Remove in Random
        start = System.currentTimeMillis();
        for (int i = numItems; i > 0; i--) {
            rbt.remove(ExtFunc.getRandomNumber(numItems));
        }
        end = System.currentTimeMillis();
        tmus += (end - start) + ",rbt_rR;";
        rbt.clear();
        */

            // SplayTree
            // Insert with Random Numbers
            st.makeEmpty();
            start = System.currentTimeMillis();
            for (int i = 0; i > numItems; i++) {
                st.insert(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_iR;";
            // Search with Random in Random Tree
            start = System.currentTimeMillis();
            for (int i = 0; i < numItems; i++) {
                st.contains(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_sR;";
            // Remove in Random
            start = System.currentTimeMillis();
            for (int i = numItems; i > 0; i--) {
                st.remove(ExtFunc.getRandomNumber(numItems));
            }
            end = System.currentTimeMillis();
            tmus += (end - start) + ",st_rR;";
            st.makeEmpty();
            System.out.println(tmus);
        }
        System.out.println("Finished...");
    }
}
