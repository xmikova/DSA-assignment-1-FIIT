//The implementation of AVL Tree and its functionalities: insert, delete, and search.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

public class AVLTree {

    static class AVLTreeNode{
        int element;
        int height;
        AVLTreeNode left;
        AVLTreeNode right;

        public AVLTreeNode(int element) //Constructor for a node in AVL tree.
        {
            this.left = null;
            this.right = null;
            this.height = 1; //Default height.
            this.element = element;
        }
    }

    AVLTreeNode root;

    AVLTree(){ //AVL tree constructor
        root = null;
    }

    public void insert(int element) {
        root = insert(root, element);
    }

    private AVLTreeNode insert(AVLTreeNode root, int element) {
        if (root == null) { //If tree does not exist, return a newly created element.
            return new AVLTreeNode(element);
        }
        if (element < root.element) { //Recursive approach for insert, this is just standard BST insert without balancing.
            root.left = insert(root.left, element);
        } else if (element > root.element) {
            root.right = insert(root.right, element);
        } else { //Duplicate keys are not allowed, this is resolved by simply returning root.
            return root;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right)); //Updating the height of this node as new node could be added as its child.
        int balance = getBalance(root); //Get the balance factor of this node.
        return BalanceAVLTree(root, balance); //Eventually, balance the tree.
    }

    public void delete(int element) {
        root = delete(root, element);
    }

    private AVLTreeNode delete(AVLTreeNode root, int element) {
        if (root == null) { //If the tree does not exist, returt root (null).
            return root;
        }

        if (element < root.element) { //Traverse the tree recursively until parent of node we want to delete is found.
            root.left = delete(root.left, element);
        } else if (element > root.element) {
            root.right = delete(root.right, element);
            //Code below now only happens if we found the element to be deleted.
        } else if ((root.left == null) && (root.right != null)){ //Case where node has only right child.
            root = root.right;
        } else if ((root.right == null) && (root.left != null)){ //Case where node has only left child.
            root = root.left;
        } else if ((root.left == null) && (root.right == null)){ //Case where node is childless.
            root = null;
        } else { //Case where node has two children.
            AVLTreeNode node = Min(root.right); //Get the smallest inorder successor in the right subtree.
            root.element = node.element; //Copy its element to the root that was deleted.
            root.right = delete(root.right, node.element); //Delete the found successor.
        }

        if (root == null) { //Check for when the node we deleted was the only one in the tree (avoiding errors).
            return root;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right)); //Updating the height of this node as the node deleted could be its child.
        int balance = getBalance(root); //Get the balance factor of this node.
        return BalanceAVLTree(root, balance); //Balance the tree.
    }


    private AVLTreeNode BalanceAVLTree(AVLTreeNode node, int balance) {
        //1. Left Left case
        if (balance > 1 && (getHeight(node.left.left) >= getHeight(node.left.right))) {
            node = AVLTreeRightRotation(node);
        }
        //2. Right Right case
        else if (balance < -1 && (getHeight(node.right.right) >= getHeight(node.right.left))) {
            node = AVLTreeLeftRotation(node);
        }
        //3. Left Right case
        else if (balance > 1 && (getHeight(node.left.left) < getHeight(node.left.right))) {
            node.left = AVLTreeLeftRotation(node.left);
            node = AVLTreeRightRotation(node);
        }
        //4. Right Left case
        else if (balance < -1 && (getHeight(node.right.right) < getHeight(node.right.left))) {
            node.right = AVLTreeRightRotation(node.right);
            node = AVLTreeLeftRotation(node);
        }
        return node; //If no balancing is needed, simply return current node.
    }

    private int getHeight(AVLTreeNode node) { //Helper function to get height of a node, especially for cases when node is null so that we avoid getting errors.
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLTreeNode node) { //Helper function to find out whether there is an imbalance after inserting node.
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private AVLTreeNode AVLTreeRightRotation(AVLTreeNode node) { //Method for right rotation.
        AVLTreeNode leftChild = node.left;
        AVLTreeNode rightGrandchild = leftChild.right;
        leftChild.right = node;
        node.left = rightGrandchild;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        leftChild.height = 1 + Math.max(getHeight(leftChild.left), getHeight(leftChild.right));
        return leftChild;
    }

    private AVLTreeNode AVLTreeLeftRotation(AVLTreeNode node) { //Method for left rotation.
        AVLTreeNode rightChild = node.right;
        AVLTreeNode leftGrandchild = rightChild.left;
        rightChild.left = node;
        node.right = leftGrandchild;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        rightChild.height = 1 + Math.max(getHeight(rightChild.left), getHeight(rightChild.right));
        return rightChild;
    }


    private AVLTreeNode Min(AVLTreeNode node) { //Helper function to find node with the minimum value in a subtree rooted at node given (used in delete function).
        AVLTreeNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    public void search(int element){
        AVLTreeNode found = search(root, element);
    }

    private AVLTreeNode search(AVLTreeNode root, int element){
        if (root == null) {
            return null;
        }
        if (element < root.element) { //Recursively traverse the tree and return found root (returns null if node does not exist in the tree).
            return search(root.left, element);
        } else if (element > root.element) {
            return search(root.right, element);
        } else {
            return root;
        }
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


