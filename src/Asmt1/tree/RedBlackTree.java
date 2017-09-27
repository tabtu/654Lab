package Asmt1.tree;

/**
 * Red Black Tree Properties :
 *
 * 1. A node is either red or black. 2. All leaves (NIL) are black. (All leaves are same color as
 * the root.) 3. Every red node must have two black child nodes. 4. Every path from a given node to
 * any of its descendant leaves contains the same number of black nodes.
 */

import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RedBlackTree<E> implements Set<E> {

    private final Comparator<? super E> comparator;
    private int size = 0;
    private Node<E> root = null;
    private int modCount = 0;

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public RedBlackTree() {
        this.comparator = null;
    }

    @Override
    public boolean add(E ele) {
        boolean inserted = false;
        if (this.root == null) {
            this.modCount++;
            this.root = new Node<E>(ele, null, null, null, Node.Color.Black);
            inserted = true;
        } else {
            Node<E> newNode = this.binarySearchInsert(this.root, ele);
            /* rebalance the tree */
            if (newNode != null) {
                this.modCount++;
                inserted = true;
                rebalanceTree(newNode);
            }
        }

        if (inserted) {
            ++size;
        }

        return inserted;
    }

    @Override
    public boolean addAll(Collection<? extends E> col) {
        boolean inserted = true;
        Iterator<? extends E> it = col.iterator();
        while (it.hasNext()) {
            inserted = inserted && this.add(it.next());
        }
        return inserted;
    }

  /* node insert methods */

    /**
     * Adds a node into the tree using the comparator
     *
     * @param node    -- the starting node
     * @param element -- the element to be added
     * @return Node<E> if the element was successfully added or null if the element already exists
     */
    private Node<E> binarySearchInsert(Node<E> node, E element) {
    /* choose the proper compare to */
        int compare = 0;
        if (this.comparator != null) {
            compare = this.comparator.compare(element, node.getValue());
        } else {
            Comparable<? super E> key = (Comparable<? super E>) element;
            compare = key.compareTo(node.getValue());
        }
    /* check where the element goes */
        if (compare < 0) {
      /* element is less than node value */
            if (node.getLeft() != null) {
                return binarySearchInsert(node.getLeft(), element);
            } else {
                Node<E> newNode = new Node<E>(element, node, null, null);
                node.setLeft(newNode);
                return newNode;
            }
        } else if (compare > 0) {
      /* element is greater than node value */
            if (node.getRight() != null) {
                return binarySearchInsert(node.getRight(), element);
            } else {
                Node<E> newNode = new Node<E>(element, node, null, null);
                node.setRight(newNode);
                return newNode;
            }
        }
        return null;
    }

  /* node lookup methods */

    /**
     * Binary search method that looks for a particular node in the tree using comparator
     *
     * @param node
     * @param element
     * @return true if the node exists; false otherwise
     */
    private Node<E> binarySearch(Node<E> node, E element) {
    /* make the proper comparison */
        int compare = 0;
        if (this.comparator != null) {
            compare = this.comparator.compare(element, node.getValue());
        } else {
            Comparable<? super E> key = (Comparable<? super E>) element;
            compare = key.compareTo(node.getValue());
        }
        if (compare < 0) {
            if (node.getLeft() != null) {
                return binarySearch(node.getLeft(), element);
            }
            return null;
        } else if (compare > 0) {
            if (node.getRight() != null) {
                return binarySearch(node.getRight(), element);
            }
            return null;
        }
        return node;
    }

    /**
     * Rebalance the tree until all properties are fullfilled
     *
     * @param node -- the node to start the rebalance from
     */
    public void rebalanceTree(Node<E> node) {
    /* root */
        if (node.getParent() == null) {
            node.setColor(Node.Color.Black);
            return;
        }
    /* if the parent is black its okay to add a red child */
        if (node.getParent().getColor().equals(Node.Color.Black)) {
            return;
        } else if (node.getParent().getColor().equals(Node.Color.Red)) {
      /* check the uncle to see if he is red as well */
            Node<E> uncle = node.getUncle();
            if (uncle != null && uncle.getColor().equals(Node.Color.Red)) {
                Node<E> grandParent = node.getGrandParent();
                node.getParent().setColor(Node.Color.Black);
                uncle.setColor(Node.Color.Black);
                grandParent.setColor(Node.Color.Red);
                rebalanceTree(grandParent);
            } else if (uncle == null || uncle.getColor().equals(Node.Color.Black)) {
                Node<E> grandParent = node.getGrandParent();
                if (node.getParent().getRight() == node && grandParent.getLeft() == node.getParent()) {
                    rotateLeft(node.getParent());
                    node = node.getLeft();
                } else if (node.getParent().getLeft() == node && grandParent.getRight() == node.getParent()) {
                    rotateRight(node.getParent());
                    node = node.getRight();
                }

        /*
         * need to grab the grand parent again in case of right and left rotation
         */
                grandParent = node.getGrandParent();
                node.getParent().setColor(Node.Color.Black);
                grandParent.setColor(Node.Color.Red);
                if (node == node.getParent().getLeft()) {
                    rotateRight(grandParent);
                } else {
                    rotateLeft(grandParent);
                }
            }
        }
    }

    @Override
    public void clear() {
        this.modCount++;
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean contains(Object o) {
    /* can't contain null values */
        if (o == null) {
            return false;
        }
    /* if root is null then no searching necessary */
        if (this.root == null) {
            return false;
        }
    /* do some binary searching */
        E oe = null;
        try {
            oe = (E) o;
        } catch (ClassCastException ce) {
            return false;
        }
        return this.binarySearch(this.root, oe)!=null;
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        boolean found = true;
        Iterator<?> it = col.iterator();
        while (it.hasNext()) {
            found = found && this.contains(it.next());
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object o) {
        if(o==null) {
            return false;
        }
        /* try the cast */
        E oe = null;
        try {
            oe = (E) o;
        } catch (ClassCastException ce) {
            return false;
        }
        /* find the node */
        Node<E> foundNode = this.binarySearch(this.root,oe);
        if(foundNode!=null) {
            this.modCount++;
            /* only one node case */
            if(foundNode==this.root) {
                this.root=null;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        boolean removed = true;
        Iterator<?> it = col.iterator();
        while(it.hasNext()) {
            /* TODO - actually call remove */
            removed = removed && this.contains(it.next());
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        Iterator<E> it = this.iterator();
        boolean changed = false;
        while(it.hasNext()) {
            Object o = it.next();
            if(!col.contains(o)){
                changed = changed || this.remove(o);
            }
        }
        return changed;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

  /* transformation methods */

    private void rotateLeft(Node<E> node) {
        Node<E> grandParent = node.getParent();
        Node<E> rightChild = node.getRight();
        Node<E> rightChildLeft = rightChild.getLeft();
        if (grandParent != null) {
            if (grandParent.getLeft() == node)
                grandParent.setLeft(rightChild);
            else {
                grandParent.setRight(rightChild);
            }
        } else {
            this.root = rightChild;
            this.root.setParent(null);
        }
        rightChild.setLeft(node);
        node.setRight(rightChildLeft);
    }

    private void rotateRight(Node<E> node) {
        Node<E> grandParent = node.getParent();
        Node<E> leftChild = node.getLeft();
        Node<E> leftChildRight = leftChild.getRight();
        if (grandParent != null) {
            if (grandParent.getLeft() == node) {
                grandParent.setLeft(leftChild);
            } else {
                grandParent.setRight(leftChild);
            }
        } else {
            this.root = leftChild;
            this.root.setParent(null);
        }
        leftChild.setRight(node);
        node.setLeft(leftChildRight);
    }

    /**
     * Replaces a node in the tree with the given child
     * @param target - the node to be replaced
     * @param child - the child node to replace it with
     */
    private void replaceNode(Node<E> target, Node<E> child) {
        if(target.getLeft()==child || target.getRight()==child) {
            if (target == target.getParent().getLeft()) {
                target.getParent().setLeft(child);
            } else if (target == target.getParent().getRight()) {
                target.getParent().setRight(child);
            }
        }
        // do nothing if its not a child of the target
    }

    /*
    All leaves in an RBT are null and black
     */
    private boolean isLeaf(Node<E> node) {
        return node==null;
    }


    /* search methods */

    public void depthFirstSearch() {
        depthFirstSearch(this.root);
    }

    private void depthFirstSearch(Node<E> node) {
        if (node != null) {
            depthFirstSearch(node.getLeft());
            System.out.println(node);
            depthFirstSearch(node.getRight());
        }
    }

    public void breadthFirstSearch() {
        breadthFirstSearch(this.root);
    }

    private void breadthFirstSearch(Node<E> rootNode) {
        Queue<Node<E>> nodes = new LinkedList<Node<E>>();
        if(rootNode!=null) {
            nodes.add(rootNode);
            while(!nodes.isEmpty()) {
                Node<E> node = nodes.poll();
                System.out.println(node);
                if(node!=null) {
                    nodes.add(node.getLeft());
                    nodes.add(node.getRight());
                }
            }
        }


    }


    /**
     * private Iterator class that traverses the tree in order
     * TODO: need to fix Queue approach because it doesn't reflect the order of the tree
     * @author christian
     */
    private final class Itr implements Iterator<E> {

        /* Used to verify if the tree has been modified during iteration */
        private int expectedModCount = modCount;

        /* the queue to keep track of nodes as they are explored in order */
        private final Queue<E> nodes;

        private Itr() {
            this.nodes = new LinkedList<E>();
            if (root != null)
                this.loadNodes(root);
        }

        /**
         * Loads the nodes in order they would be explored using DFS
         *
         * @param node
         */
        private final void loadNodes(Node<E> node) {
            if (node != null) {
                loadNodes(node.getLeft());
                this.nodes.add(node.getValue());
                loadNodes(node.getRight());
            }

        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public E next() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            return this.nodes.poll();
        }

        @Override
        public void remove() {
            if(this.expectedModCount != modCount) {
                RedBlackTree.this.remove(this.nodes.poll());
            }
            /* expected mount count in tree will change so it needs to be changed here */
            this.expectedModCount = modCount;
        }

    }

  /* node inner class */

    private static class Node<E> {

        public static enum Color {
            Black, Red
        }

        ;

        private final E value;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        private Color color;

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right) {
            this(value, parent, left, right, Color.Red);
        }

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right, Color color) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        /**
         * Add a new left child Need to modify the parent of the new left child to be correct
         *
         * @param left
         */
        public void setLeft(Node<E> left) {
            this.left = left;
            if (left != null)
                this.left.setParent(this);
        }

        public Node<E> getRight() {
            return right;
        }

        /**
         * Add a new right child Need to modify the parent of the new right child to correct
         *
         * @param right
         */
        public void setRight(Node<E> right) {
            this.right = right;
            if (right != null)
                this.right.setParent(this);
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Node<E> getGrandParent() {
            Node<E> parent = this.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getParent();
        }

        public Node<E> getUncle() {
            Node<E> grandParent = this.getGrandParent();

            if (grandParent == null) {
                return null;
            }

            if (this.getParent() == grandParent.getLeft()) {
                return grandParent.getRight();
            } else {
                return grandParent.getLeft();
            }
        }

        public Node<E> getSibling() {
            Node<E> parent = this.getParent();
            /* check to see if this is the root node */
            if(parent!=null) {
                if(this==parent.getLeft()) {
                    return parent.getRight();
                } else {
                    return parent.getLeft();
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "Node [value=" + this.value + ", parent="
                    + ((this.parent != null) ? this.parent.value : null) + ", left="
                    + ((this.left != null) ? this.left.value : null) + ", right="
                    + ((this.right != null) ? this.right.value : null) + ", color=" + color + "]";
        }
    }

}