public class AVLTree {

    static class AVLTreeNode{
        int element;
        int height;
        AVLTreeNode left;
        AVLTreeNode right;

        public AVLTreeNode(int element)
        {
            this.left = null;
            this.right = null;
            this.height = 1;
            this.element = element;
        }
    }

    AVLTreeNode root;

    AVLTree(){
        root = null;
    }


    public void insert(int element) {
        root = insert(root, element);
    }

    private AVLTreeNode insert(AVLTreeNode node, int element) {
        // Perform normal BST insertion
        if (node == null) {
            return new AVLTreeNode(element);
        }
        if (element < node.element) {
            node.left = insert(node.left, element);
        } else if (element > node.element) {
            node.right = insert(node.right, element);
        } else { // Duplicate keys not allowed
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether
        // this node became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && element < node.left.element) {
            return rotateRight(node);
        }
        // Right Right Case
        if (balance < -1 && element > node.right.element) {
            return rotateLeft(node);
        }
        // Left Right Case
        if (balance > 1 && element > node.left.element) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // Right Left Case
        if (balance < -1 && element < node.right.element) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        // return the (unchanged) node pointer
        return node;
    }

    private int height(AVLTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    private int getBalance(AVLTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return height(node.left) - height(node.right);
        }
    }

    private AVLTreeNode rotateRight(AVLTreeNode node) {
        AVLTreeNode leftChild = node.left;
        AVLTreeNode rightGrandChild = leftChild.right;
        leftChild.right = node;
        node.left = rightGrandChild;
        // Update heights
        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));
        return leftChild;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode node) {
        AVLTreeNode rightChild = node.right;
        AVLTreeNode leftGrandChild = rightChild.left;
        rightChild.left = node;
        node.right = leftGrandChild;
        // Update heights
        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightChild.height = 1 + Math.max(height(rightChild.left), height(rightChild.right));
        return rightChild;
    }


    int MinimalValue(AVLTreeNode root){
        int MinVal = root.element;
        while (root.left != null){
            MinVal = root.left.element;
            root = root.left;
        }
        return MinVal;
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


