package NHeap;

import java.util.*;
import java.lang.Math;

/**
 * NodeHeap class which extends BinaryTree class
 */
public class NodeHeap extends BinaryTree{
    private int follow;

    /**
     * Node class
     */
    public class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        /**
         *
         * @param val value of the node
         */
        public Node(int val) {
            value = val;
            left = null;
            right = null;
            parent = null;
        }

        /**
         *
         * @param val value of the node
         * @param l left node
         * @param r right node
         * @param p parent node
         */
        public Node(int val,Node l, Node r, Node p) {
            value = val;
            left = l;
            right = r;
            parent = p;
        }

        /**
         *
         * @return parent node
         */
        public Node getParent() {
            return parent;
        }

        /**
         *
         * @param parent parent node
         */
        public void setParent(Node parent) {
            this.parent = parent;
        }

        /**
         *
         * @return value of the node
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value of the node
         */
        public void setValue(int value) {
            this.value = value;
        }

        /**
         *
         * @return left child of the node
         */
        public Node getLeft() {
            return left;
        }

        /**
         *
         * @param left left child of the node
         */
        public void setLeft(Node left) {
            this.left = left;
        }

        /**
         *
         * @return right child of the node
         */
        public Node getRight() {
            return right;
        }

        /**
         *
         * @param right child of the node
         */
        public void setRight(Node right) {
            this.right = right;
        }

        /**
         *
         * @return tostring equivalence
         */
        @Override
        public String toString() {
            return "Node [value=" + value + "]";
        }

    }

    private int depth;
    private int size;
    private Node root;

    /**
     * Nodeheap constructor
     */
    public NodeHeap() {
        root = null;
        size = 0;
        depth = 0;
        follow = 0;
    }

    /**
     *
     * @return the size of the heap
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     *
     * @return the top node of the heap
     * @throws NoSuchElementException
     */
    public int top() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException ("attempt to access top of empty heap");
        }
        else
            return root.getValue();
    }

    /**
     *  removes and element from heap using preorder traversel
     * @throws NoSuchElementException
     */
    public void remove() throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException ("We cannot remove an element from empty heap");
        if(size==1){
            size=0;
            root = null;
            return;
        }
        follow = 0;
        Node last = findLastNode(root, size, depth);
        root.setValue(last.getValue());
        Node parent = last.getParent();
        if(size%2 == 0){
            parent.setLeft(null);
        }
        else{
            parent.setRight(null);
        }
        if((size&(--size)) == 0){
            depth --;
        }
        arrangePositionInDownerDirection(root);
        System.out.println("\nRemove operation takes "+follow+" swaps.");
        follow = 0;
    }

    /**
     * add function which finds a position to add a new node into the heap using preorder traversel
     * @param item value to be added
     */
    public void add(int item){
        if(size==0){
            root = new Node(item);
            size++;
            return;
        }
        follow = 0;
        Node nextParent = findLastParent(root, size, depth);
        Node add = new Node(item, null, null, nextParent);

        if(nextParent.getLeft() == null){
            nextParent.setLeft(add);
        }
        else{
            nextParent.setRight(add);
        }
        if((size&(++size)) == 0){
            depth ++;
        }
        arrangePositionInUpperDirection(add);
        System.out.println("\nAdd operation takes  "+follow+" swaps.");
        follow = 0;
    }
    /**
     * preorder traverse function which uses linked list to keeps the elements
     */
    public void preOrderTraverse(){
        System.out.println("Heap with size "+size+" and depth "+depth);
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node current = queue.remove();
            System.out.print(current.getValue() + " ");
            if(current.getLeft() != null){
                queue.add(current.getLeft());
            }
            if(current.getRight() != null){
                queue.add(current.getRight());
            }
        }
        System.out.println();
    }

    /**
     * finds the parent of the current node and fixes the position in terms of the value
     * if the value of the current position is bigger than parent then swaps the position
     * @param current current node
     */
    private void arrangePositionInUpperDirection(Node current){
        if(current==null || current.getParent() == null)
            return;
        if(current.getValue()>current.getParent().getValue()){
            int temp = current.getParent().getValue();
            current.getParent().setValue(current.getValue());
            current.setValue(temp);
            arrangePositionInUpperDirection(current.getParent());
            follow++;
        }
    }
    /**
     * finds the child of the current node and fixes the position in terms of the value
     * if the value of the current position is smaller than left child then swaps the position
     * @param current current node
     */
    private void arrangePositionInDownerDirection(Node current){
        if(current==null || (current.getLeft()==null && current.getRight() == null))
            return;
        follow++;
        if(current.getRight()==null)
        {
            if(current.getValue() < current.getLeft().getValue()){
                int temp = current.getLeft().getValue();
                current.getLeft().setValue(current.getValue());
                current.setValue(temp);
                arrangePositionInDownerDirection(current);
                arrangePositionInDownerDirection(current.getLeft());
            }
        }
        else if(current.getLeft()==null)
        {
            if(current.getValue() < current.getRight().getValue()){
                int temp = current.getRight().getValue();
                current.getRight().setValue(current.getValue());
                current.setValue(temp);
                arrangePositionInDownerDirection(current);
                arrangePositionInDownerDirection(current.getRight());
            }
        }
        else if(current.getLeft().getValue() > current.getRight().getValue() && current.getValue() < current.getLeft().getValue()){
            int temp = current.getLeft().getValue();
            current.getLeft().setValue(current.getValue());
            current.setValue(temp);
            arrangePositionInDownerDirection(current);
            arrangePositionInDownerDirection(current.getLeft());
        }
        else if(current.getLeft().getValue() < current.getRight().getValue() && current.getValue() < current.getRight().getValue()){
            int temp = current.getRight().getValue();
            current.getRight().setValue(current.getValue());
            current.setValue(temp);
            arrangePositionInDownerDirection(current);
            arrangePositionInDownerDirection(current.getRight());
        }
        else{
            follow--;
        }

    }

    /**
     * It finds the last parent of the heap using preorder traversal recursively
     * @param current current node
     * @param size size of the tree
     * @param depth depth of the tree
     * @return
     */
    private Node findLastParent(Node current, int size, int depth){
        if(size<=2)
            return current;
        int twoPow = (int)(Math.pow(2, depth));
        if((size+1)%twoPow < twoPow/2){
            if((size&(size+1)) == 0)
                return findLastParent(current.getLeft(), size-twoPow, depth-1);
            return findLastParent(current.getLeft(), size-(int)(Math.pow(2, depth-1)), depth-1);
        }
        return findLastParent(current.getRight(), size-twoPow, depth-1);
    }

    /**
     * it finds the lastest node on the heap using binary operations and indexes
     * index and depth are related to each other
     * it uses index and depth recursively to traverse through the lastest node on the heap
     * @param current current node
     * @param size size of the tree
     * @param depth depth of the tree
     */
    private Node findLastNode(Node current, int size, int depth){
        if(size<=1)
            return current;
        int twoPow = (int)(Math.pow(2, depth));
        if(size%twoPow < twoPow/2){
            if((size&(size+1)) == 0)
                return findLastNode(current.getLeft(), size-twoPow, size-1);
            return findLastNode(current.getLeft(), size-(int)(Math.pow(2, depth-1)), depth-1);
        }
        return findLastNode(current.getRight(), size-twoPow, depth-1);

    }



    /**
     *
     * @return the size of the heap
     */
    public int getSize(){
        return size;
    }

    /**
     *
     * @return the depth of the heap
     */
    public int getDepth(){
        return depth;
    }

}