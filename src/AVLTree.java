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

    public int height(AVLTreeNode root) {
        if (root == null) {
            return -1;  // null node has height -1
        } else {
            return root.height;
        }
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

        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Balance the current node
        root = BalanceAVLTree(root);

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

    public AVLTreeNode BalanceAVLTree(AVLTreeNode root) {

        if ((height(root.left) - height(root.right) > 1) && (height(root.left.left) > height(root.left.right))) {
            root = AVLTreeRightRotation(root);
        } else if ((height(root.left) - height(root.right) > 1) &&(height(root.left.left) < height(root.left.right))) {
            root = AVLTreeLeftRightRotation(root);
        } else if ((height(root.left) - height(root.right) < -1) && (height(root.right.left) < height(root.right.right))){
            root = AVLTreeLeftRotation(root);
        } else if ((height(root.left) - height(root.right) < -1) &&  (height(root.right.left) > height(root.right.right))) {
            root = AVLTreeRightLeftRotation(root);
        }

        return root;
    }

    public AVLTreeNode AVLTreeRightRotation(AVLTreeNode root){
        AVLTreeNode temp = root.left;
        root.left = temp.right;
        temp.right = root;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        temp.height = 1 + Math.max(height(temp.left), height(temp.right));

        return temp;
    }

    public AVLTreeNode AVLTreeLeftRotation(AVLTreeNode root){
        AVLTreeNode temp = root.right;
        root.right = temp.left;
        temp.left = root;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        temp.height = 1 + Math.max(height(temp.left), height(temp.right));

        return temp;
    }

    public AVLTreeNode AVLTreeLeftRightRotation(AVLTreeNode root) {
        root.left = AVLTreeLeftRotation(root.left);
        return AVLTreeRightRotation(root);
    }

    public AVLTreeNode AVLTreeRightLeftRotation(AVLTreeNode root) {
        root.right = AVLTreeRightRotation(root.right);
        return AVLTreeLeftRotation(root);
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
}

