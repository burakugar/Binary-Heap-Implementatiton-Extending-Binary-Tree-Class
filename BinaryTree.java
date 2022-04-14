package NHeap;

import java.io.*;
import java.util.Scanner;

/**
 * Class for a binary tree that stores type E objects
 *
 * @author Jacob / Koffman & Wolfgang
 *
 * @param <E> The type of data to be stored
 */
public class BinaryTree<E> implements Serializable {

    // Data Fields
    /**
     * Class for a binary tree that stores type E objects
     */
    protected Node<E> root;

    // Constructors
    /**
     * No‐parameter constructor merely sets the data field root to null.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * The constructor that takes a Node as a parameter
     * This is because client classes do not know about the Node class.
     *
     * @param root The root of the tree
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root, leftTree as its left
     * subtree, and rightTree as its right subtree
     *
     * @param data      The data of the root node
     * @param leftTree  The left subtree
     * @param rightTree The right subtree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    // Methods
    /**
     * Return the left subtree
     *
     * @return The left subtree or null if either the root or the left subtree is
     *         null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return new BinaryTree<>(null);
        }
    }

    /**
     * Return the right subtree
     *
     * @return The right subtree or null if either the root or the right subtree is
     *         null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return new BinaryTree<>(null);
        }
    }

    /**
     * Determine whether this tree is a leaf
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return root == null || (root.left == null && root.right == null);
    }

    /**
     * It prints the tree in preorder traversal.
     *
     * @return A string representation of the tree.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     *
     * @param node  The local root
     * @param depth The depth
     * @param sb    The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Method to read a binary treee
     * pre: The input consists of a preorder traversal of the binary tree.
     * The line "null" indicates a null tree.
     *
     * @param scan the Scanner attached to the input file
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    /**
     * Class to encapsulate a tree node
     *
     * @author Jacob / Koffman & Wolfgang
     *
     * @param <E> The type of data stored
     */
    protected static class Node<E> implements Serializable
    {
        // Data Fields
        /**
         * The information stored in this node
         */
        protected E data;
        /**
         * Reference to the left child
         */
        protected Node<E> left;
        /**
         * Reference to the right child
         */
        protected Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children
         *
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Return a string representation of the node
         *
         * @return A string representation of the data fields
         */
        public String toString() {
            return data.toString();
        }
    }
}