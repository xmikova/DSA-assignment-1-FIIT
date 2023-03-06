import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        AVLTree AVLTree = new AVLTree();
        AVLTree.insert(45);
        AVLTree.insert(10);
        AVLTree.insert(7);
        AVLTree.insert(12);

//        for (int i = 0; i < 100000; i++){
//            AVLTree.insert((int)Math.floor(Math.random() * (100000 - 2 + 1) + 2));
//        }
//
//        AVLTree.inorder();
//        System.out.println("\n");
//
//        for (int i = 0; i < 100000; i++){
//            AVLTree.delete((int)Math.floor(Math.random() * (100000 - 2 + 1) + 2));
//        }        //AVLTree.inorder();
//        AVLTree.search(9);
        RedBlackTree RedBlackTree = new RedBlackTree();
        RedBlackTree.insert(5);
        RedBlackTree.insert(45);
        RedBlackTree.insert(10);
        RedBlackTree.insert(7);
        RedBlackTree.insert(12);
        RedBlackTree.insert(6);
        RedBlackTree.insert(95);
        RedBlackTree.insert(19);
        RedBlackTree.insert(37);

        //RedBlackTree.inorder();
//        long startTime = System.currentTimeMillis();
//
//
//        for (int i = 0; i < 10000000; i++){
//            RedBlackTree.insert((int)Math.floor(Math.random() * (10000000 - 2 + 1) + 2));
//        }
//
//        long endTime = System.currentTimeMillis();
//        long duration = (endTime - startTime);
//        System.out.format("Milli = %s, ( S_Start : %s, S_End : %s ) \n", duration, startTime, endTime );

       RedBlackTree.printPostorder();

    }



}
