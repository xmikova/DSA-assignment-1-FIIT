public class Main {
    public static void main(String[] args) {
        AVLTree AVLTree = new AVLTree();
        AVLTree.insert(45);
        AVLTree.insert(10);
        AVLTree.insert(7);
        AVLTree.insert(12);

        for (int i = 0; i < 1000000; i++){
            AVLTree.insert((int)Math.floor(Math.random() * (1000000 - 2 + 1) + 2));
        }

        AVLTree.inorder();
        System.out.println("\n");
        AVLTree.delete(8999);
        AVLTree.inorder();

    }
}