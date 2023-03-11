public class AVLTree {

    static class AVLTreeNode{
        int element;
        int height;
        AVLTreeNode left;
        AVLTreeNode right;

        public AVLTreeNode(int element) //node constructor
        {
            this.left = null;
            this.right = null;
            this.height = 1;
            this.element = element;
        }
    }

    AVLTreeNode root;

    AVLTree(){ //avl tree constructor
        root = null;
    }

    public void insert(int element) {
        root = insert(root, element);
    }

    private AVLTreeNode insert(AVLTreeNode root, int element) {
        if (root == null) {
            return new AVLTreeNode(element);
        }
        if (element < root.element) {
            root.left = insert(root.left, element);
        } else if (element > root.element) {
            root.right = insert(root.right, element);
        } else { // Duplicate keys not allowed
            return root;
        }

        // Update height of this ancestor node
        root.height = 1 + Math.max(height(root.left), height(root.right));
        // Get the balance factor of this ancestor node to check whether
        // this node became unbalanced
        int balance = getBalance(root);

        return BalanceAVLTree(root, balance); //balance the tree
    }

    public void delete(int element) {
        root = delete(root, element);
    }

    private AVLTreeNode delete(AVLTreeNode root, int element) {
        if (root == null) {
            return root;
        }

        if (element < root.element) {
            root.left = delete(root.left, element);
        } else if (element > root.element) {
            root.right = delete(root.right, element);
        } else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                AVLTreeNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // no child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // one child case
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                AVLTreeNode temp = Min(root.right);

                // Copy the inorder successor's data to this node
                root.element = temp.element;

                // Delete the inorder successor
                root.right = delete(root.right, temp.element);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // Update height of the current node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get the balance factor of this node to check whether this node became unbalanced
        int balance = getBalance(root);
        // Balance the tree if necessary
        return BalanceAVLTree(root, balance);
    }



    private AVLTreeNode BalanceAVLTree(AVLTreeNode node, int balance) {
        // left left case
        if (balance > 1 && height(node.left.left) >= height(node.left.right)) {
            node = AVLTreeRightRotation(node);
        }
        // right right case
        else if (balance < -1 && height(node.right.right) >= height(node.right.left)) {
            node = AVLTreeLeftRotation(node);
        }
        // left right case
        else if (balance > 1 && height(node.left.left) < height(node.left.right)) {
            node.left = AVLTreeLeftRotation(node.left);
            node = AVLTreeRightRotation(node);
        }
        // right left case
        else if (balance < -1 && height(node.right.right) < height(node.right.left)) {
            node.right = AVLTreeRightRotation(node.right);
            node = AVLTreeLeftRotation(node);
        }
        return node;
    }

    private int height(AVLTreeNode node) { //helper function for node height so that we dont get nullptrexception when node is null
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    private int getBalance(AVLTreeNode node) { //helper function to find out whether there is an imbalance in the tree after inserting node
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLTreeNode AVLTreeRightRotation(AVLTreeNode node) {
        AVLTreeNode leftChild = node.left;
        AVLTreeNode rightGrandchild = leftChild.right;
        leftChild.right = node;
        node.left = rightGrandchild;
        // Update heights
        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));
        return leftChild;
    }

    private AVLTreeNode AVLTreeLeftRotation(AVLTreeNode node) {
        AVLTreeNode rightChild = node.right;
        AVLTreeNode leftGrandchild = rightChild.left;
        rightChild.left = node;
        node.right = leftGrandchild;
        // Update heights
        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightChild.height = 1 + Math.max(height(rightChild.left), height(rightChild.right));
        return rightChild;
    }


    AVLTreeNode Min(AVLTreeNode node) {
        AVLTreeNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    void search(int element){
        root = search(root,element);
    }

    AVLTreeNode search(AVLTreeNode root, int element){
        if (root == null){
            return root;
        }

        if (root.element == element){
            System.out.println(root.element);
            return root;
        }

        if (element < root.element){
            return search(root.left, element);
        } else return search(root.right, element);

    }


//---------------------------------------------------------------------------------------------------------------------//
    //just traversals there//

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

    void printPreorder() {
        printPreorder(root);
        System.out.println();
    }

    void printPreorder(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
}


