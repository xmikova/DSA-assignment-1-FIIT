public class RedBlackTree {

    static class RedBlackTreeNode{
        int element;
        boolean Red; // 1 = red, 0 = black
        RedBlackTreeNode left;
        RedBlackTreeNode right;
        RedBlackTreeNode parent;

        public RedBlackTreeNode(int element){
            this.element = element;
            this.Red = true;
            this.left = this.right = this.parent = null;
        }
    }

    RedBlackTreeNode root;

    RedBlackTree(){
        root = null;
    }

    void insert(int element)  {
        root = insert(root, element);
    }


    RedBlackTree.RedBlackTreeNode insert(RedBlackTree.RedBlackTreeNode root, int element) {
        //tree is empty
        if (root == null) {
            root = new RedBlackTree.RedBlackTreeNode(element); //create new tree if empty
            root.Red = false;
            return root;
        }else
            //traverse the tree recursively
            if (element < root.element)     //insert in the left subtree
                root.left = insert(root.left, element);
            else if (element > root.element)    //insert in the right subtree
                root.right = insert(root.right, element);
        // return pointer

        // Balance the current node


        root = BalanceRedBlackTree(root);

        return root;

    }

    private RedBlackTreeNode BalanceRedBlackTree(RedBlackTreeNode root) {
        while (root.parent != null && root.parent.Red == true) {
            if (root.parent == root.parent.parent.left) {
                RedBlackTreeNode uncle = root.parent.parent.right;
                if (uncle != null && uncle.Red == true) {
                    root.parent.Red = false;
                    uncle.Red = false;
                    root.parent.parent.Red = true;
                    root = root.parent.parent;
                } else {
                    if (root == root.parent.right) {
                        root = root.parent;
                        RedBlackTreeLeftRotation(root);
                    }
                    root.parent.Red = false;
                    root.parent.parent.Red = true;
                    RedBlackTreeRightRotation(root.parent.parent);
                }
            } else {
                RedBlackTreeNode uncle = root.parent.parent.left;
                if (uncle != null && uncle.Red == true) {
                    root.parent.Red = false;
                    uncle.Red = false;
                    root.parent.parent.Red = true;
                    root = root.parent.parent;
                } else {
                    if (root == root.parent.left) {
                        root = root.parent;
                        RedBlackTreeRightRotation(root);
                    }
                    root.parent.Red = false;
                    root.parent.parent.Red = true;
                    RedBlackTreeLeftRotation(root.parent.parent);
                }
            }
        }
        root.Red = false;
        return root;
    }

    private RedBlackTreeNode RedBlackTreeLeftRotation(RedBlackTreeNode root) {
        RedBlackTreeNode x = root.right;
        root.right = x.left;
        x.left = root;
        x.Red = root.Red;
        root.Red = true;
        return x;
    }

    private RedBlackTreeNode RedBlackTreeRightRotation(RedBlackTreeNode root) {
        RedBlackTreeNode x = root.left;
        root.left = x.right;
        x.right = root;
        x.Red = root.Red;
        root.Red = true;
        return x;
    }

    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(RedBlackTreeNode root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.element + " ");
            inorder_Recursive(root.right);
        }
    }







}
