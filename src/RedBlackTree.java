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
            root = new RedBlackTreeNode(element); //create new tree if empty
            root.Red = false;
            return root;
        }

        //traverse the tree recursively
        if (element < root.element) {   //insert in the left subtree
            root.left = insert(root.left, element);
            root.left.parent = root; // set the parent of the newly created node
        } else if (element > root.element) {  //insert in the right subtree
            root.right = insert(root.right, element);
            root.right.parent = root; // set the parent of the newly created node
        }

        // Balance the current node
        root = BalanceRedBlackTree(root);

        // Make sure the root node is black
        if (root.parent == null) {
            root.Red = false;
        }

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
                        root = RedBlackTreeLeftRotation(root);
                    }
                    root.parent.Red = false;
                    root.parent.parent.Red = true;
                    root = RedBlackTreeRightRotation(root.parent.parent);
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
                        root = RedBlackTreeRightRotation(root);
                    }
                    root.parent.Red = false;
                    root.parent.parent.Red = true;
                    root = RedBlackTreeLeftRotation(root.parent.parent);
                }
            }
            System.out.println("Root: " + root.element + ", Parent: " + root.parent.element + ", Grandparent: " + root.parent.parent.element); // Debugging line
        }
        root.Red = false;
        return root;
    }

    private RedBlackTreeNode RedBlackTreeLeftRotation(RedBlackTreeNode root) {
        RedBlackTreeNode x = root.right;
        root.right = x.left;
        if (root.right != null) {
            root.right.parent = root;
        }
        x.left = root;
        x.Red = root.Red;
        root.Red = true;
        x.parent = root.parent;
        root.parent = x;
        if (x.parent != null) {
            if (x.parent.left == root) {
                x.parent.left = x;
            } else {
                x.parent.right = x;
            }
        }
        return x;
    }

    private RedBlackTreeNode RedBlackTreeRightRotation(RedBlackTreeNode root) {
        System.out.println("Before right rotation: " + root.element);
        RedBlackTreeNode x = root.left;
        root.left = x.right;
        if (x.right != null) {
            x.right.parent = root;
        }
        x.parent = root.parent;
        if (root.parent == null) {
            this.root = x;
        } else if (root == root.parent.right) {
            root.parent.right = x;
        } else {
            root.parent.left = x;
        }
        x.right = root;
        root.parent = x;
        x.Red = root.Red;
        root.Red = true;
        System.out.println("After right rotation: " + x.element);
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
    void printPostorder() { printPostorder(root); }

    void printPostorder(RedBlackTreeNode root) {
        if (root == null)
            return;

        // first recur on left subtree
        printPostorder(root.left);

        // then recur on right subtree
        printPostorder(root.right);

        // now deal with the node
        System.out.print(root.element + " ");
    }

    // Wrappers over above recursive functions









}
