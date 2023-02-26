public class AVLTree {

    static class AVLTreeNode{
        int element;
        int height;
        AVLTreeNode left;
        AVLTreeNode right;

        public AVLTreeNode(int element)
        {
            left = null;
            right = null;
            this.height = 1;
            this.element = element;
        }
    }

    AVLTreeNode root;

    AVLTree(){
        root = null;
    }

    void insert(int element)  {
        root = insert(root, element);
    }

    //recursive insert function
    AVLTreeNode insert(AVLTreeNode root, int element) {
        //tree is empty
        if (root == null) {
            root = new AVLTreeNode(element); //create new tree if empty
            return root;
        }else
        //traverse the tree recursively
        if (element < root.element)     //insert in the left subtree
            root.left = insert(root.left, element);
        else if (element > root.element)    //insert in the right subtree
            root.right = insert(root.right, element);
        // return pointer
        return root;

    }

    void delete(int element){
        root = delete(root, element);
    }

    AVLTreeNode delete(AVLTreeNode root, int element){
        if (root == null){
            return root; //empty tree
        }

        if (element < root.element){  //traversing the tree recursively
            root.left = delete(root.left, element);
        }else if (element > root.element){
            root.right = delete(root.right, element);
        }

        else { //case where node has one or no child
            if (root.left == null){
                return root.right;
            } else if (root.right == null){
                return root.left;
            }

            //case where node has two children, find the smallest successor in the right subtree

            root.element = MinimalValue(root.right);
            root.right = delete(root.right, root.element);
        }

        return root;
    }

    int MinimalValue(AVLTreeNode root){
        int MinVal = root.element;
        while (root.left != null){
            MinVal = root.left.element;
            root = root.left;
        }
        return MinVal;
    }

    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(AVLTreeNode root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.element + " ");
            inorder_Recursive(root.right);
        }
    }
}

