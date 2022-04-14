import BinarySearchTreeArray.BinarySearchTreeArrayImpl;
import NHeap.NodeHeap;
import QuadTree.Quadtree;
import QuadTree.Quadtree.Boundary;
public class main {
    public static void main(String args[]) {
        NodeHeap heap = new NodeHeap();
        System.out.println("Is the tree empty? :" + heap.isEmpty());
        heap.add(5);
        heap.add(9);
        heap.add(4);
        heap.add(1);
        heap.add(2);
        heap.add(10);
        heap.preOrderTraverse();
        System.out.println("Is the tree empty? :" + heap.isEmpty());
        heap.add(12);
        heap.preOrderTraverse();
        heap.remove();
        heap.preOrderTraverse();
        heap.remove();
        heap.preOrderTraverse();
        heap.add(90);
        heap.preOrderTraverse();
        heap.add(15);
        heap.preOrderTraverse();
        heap.add(4);
        heap.preOrderTraverse();
        heap.remove();
        heap.preOrderTraverse();

        System.out.println("Quadtree:");

        Quadtree.QuadTree anySpace = new Quadtree.QuadTree(1, new Boundary(0, 0, 100, 100));
        anySpace.insert(30, 30, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(20, 15, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(50, 40, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(10, 12, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(40, 20, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(25, 60, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(15, 25, 1);
        System.out.println();

        Quadtree.QuadTree.dfs(anySpace);
        System.out.println();



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
