public class RedBlackTree {

    static class RedBlackTreeNode{
        int element;
        boolean Red;
        RedBlackTreeNode left;
        RedBlackTreeNode right;
        RedBlackTreeNode parent;

        public RedBlackTreeNode(int element){ // every new node is colored red
            this.element = element;
            this.Red = true;
            this.left = this.right = this.parent = null;
        }
    }

    RedBlackTreeNode root;

    RedBlackTree(){
        root = null;
    }


    public void insert(int element) {

        // Step 1: Create a new red node.
        RedBlackTreeNode newRedBlackTreeNode = new RedBlackTreeNode(element);

        // Step 2: Insert the node using the classic BST insertion algorithm
        RedBlackTreeNode parent = null;
        RedBlackTreeNode current = root;
        while (current != null) {
            parent = current;
            if (element < current.element) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // Set the parent of the new node.
        newRedBlackTreeNode.parent = parent;

        // Set the new node as the left or right child of its parent.
        if (parent == null) {
            // Tree is empty. Set new node as root.
            root = newRedBlackTreeNode;
        } else if (element < parent.element) {
            parent.left = newRedBlackTreeNode;
        } else {
            parent.right = newRedBlackTreeNode;
        }

        // Step 3: Fix the Red-Black Tree properties that may have been violated by the insertion.
        BalanceRedBlackTree(newRedBlackTreeNode);
    }

    private void BalanceRedBlackTree(RedBlackTreeNode node) { //TODO: comment and understand the code there
        // Case 1: The node is the root. Color it black and we're done.
        if (node == root) {
            node.Red = false;
            return;
        }

        // Case 2: The parent of the node is black. No violations, so we're done.
        if (node.parent.Red == false) {
            return;
        }

        // Case 3: The parent and the uncle of the node are both red. Recolor them and move up the tree.
        RedBlackTreeNode parent = node.parent;
        RedBlackTreeNode uncle = getUncle(node);

        if (uncle != null && uncle.Red) {
            parent.Red = false;
            uncle.Red = false;
            parent.parent.Red = true; //grandparent
            BalanceRedBlackTree(parent.parent);
            return;
        }

        // Case 4: The node is a right child and its parent is a left child. Rotate left.
        if (node == parent.right && parent == parent.parent.left) {
            rotateLeft(parent);
            node = node.left;
        } else if (node == parent.left && parent == parent.parent.right) {
            // Case 5: The node is a left child and its parent is a right child. Rotate right.
            rotateRight(parent);
            node = node.right;
        }

        // Case 6: The node's parent is red, but the uncle is black. Recolor and rotate.
        parent = node.parent;
        parent.parent = parent.parent;

        parent.Red = false;
        parent.parent.Red = true;

        if (node == parent.left && parent == parent.parent.left) {
            rotateRight(parent.parent);
        } else {
            rotateLeft(parent.parent);
        }
    }

    private RedBlackTreeNode getUncle(RedBlackTreeNode node) { //uncle getter
        //RedBlackTreeNode grandparent = node.parent.parent;
        if (node.parent.parent == null) {
            return null; // No grandparent, so no uncle.
        }
        if (node.parent == node.parent.parent.left) {
            return node.parent.parent.right;
        } else {
            return node.parent.parent.left;
        }
    }

    private void rotateLeft(RedBlackTreeNode node) {
        RedBlackTreeNode xl = node.right;
        node.right = xl.left;
        if (xl.left != null) {
            xl.left.parent = node;
        }
        xl.parent = node.parent;
        if (node.parent == null) {
            root = xl;
        } else if (node == node.parent.left) {
            node.parent.left = xl;
        } else {
            node.parent.right = xl;
        }
        xl.left = node;
        node.parent = xl;
    }

    private void rotateRight(RedBlackTreeNode node) {
        RedBlackTreeNode xr = node.left;
        node.left = xr.right;
        if (xr.right != null) {
            xr.right.parent = node;
        }
        xr.parent = node.parent;
        if (node.parent == null) {
            root = xr;
        } else if (node == node.parent.right) {
            node.parent.right = xr;
        } else {
            node.parent.left = xr;
        }
        xr.right = node;
        node.parent = xr;
    }

//---------------------------just traversals down there--------------------------------------------------------------//


    void printPreorder(RedBlackTreeNode node) {
        if (node != null) {
            System.out.print(node.element + " ");
            printPreorder(node.left);
            printPreorder(node.right);
        }
    }

    // Wrapper method for starting the pre-order traversal from the root node
    void printPreorder() {
        printPreorder(root);
    }


}