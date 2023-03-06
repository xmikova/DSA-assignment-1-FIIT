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

        RedBlackTree.inorder();



    }



}
