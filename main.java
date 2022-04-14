import BinarySearchTreeArray.BinarySearchTreeArrayImpl;

public class main {
    public static void main(String args[]) {
       

        BinarySearchTreeArrayImpl.BST bst= new BinarySearchTreeArrayImpl.BST<Integer>(10);
        bst.add(14);
        bst.add(16);
        bst.add(19);
        bst.add(2);
        System.out.println("Current node:");
        bst.traverse(0);
        System.out.println();
        System.out.println("Finding 2:");
        System.out.println(bst.find(2));
        bst.remove(2);
        System.out.println("After Delete: ");
        bst.traverse(0);
        System.out.println("Is three contains 19:");
        System.out.println( bst.contains(19));
        bst.traverse(0);
    }
}
