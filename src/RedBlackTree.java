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


    public void insert(int element) { //

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

    private void rotateLeft(RedBlackTreeNode x) {
        RedBlackTreeNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(RedBlackTreeNode y) {
        RedBlackTreeNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }


    public void delete(int element) {
        RedBlackTreeNode nodeToDelete = root;
        while (nodeToDelete != null) {
            if (element == nodeToDelete.element) {
                deleteNode(nodeToDelete);
                return;
            } else if (element < nodeToDelete.element) {
                nodeToDelete = nodeToDelete.left;
            } else {
                nodeToDelete = nodeToDelete.right;
            }
        }
    }

    private void deleteNode(RedBlackTreeNode nodeToDelete) {
        RedBlackTreeNode child, sibling;
        boolean originalColor = nodeToDelete.Red;

        if (nodeToDelete.left != null && nodeToDelete.right != null) {
            // node has two children, find successor and replace nodeToDelete with it
            RedBlackTreeNode successor = nodeToDelete.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            originalColor = successor.Red;
            nodeToDelete.element = successor.element;
            nodeToDelete = successor;
        }

        // node has one child or no children
        child = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;

        if (child != null) {
            // update child's parent reference
            child.parent = nodeToDelete.parent;
        }

        if (nodeToDelete.parent == null) {
            // nodeToDelete is the root
            root = child;
        } else if (nodeToDelete == nodeToDelete.parent.left) {
            nodeToDelete.parent.left = child;
        } else {
            nodeToDelete.parent.right = child;
        }

        // fixup the tree to maintain red-black properties
        if (!originalColor) {
            if (child != null && child.Red) {
                child.Red = false;
            } else {
                deleteFixup(child, nodeToDelete.parent);
            }
        }
    }


    private void deleteFixup(RedBlackTreeNode x, RedBlackTreeNode parent) {
        RedBlackTreeNode sibling;
        while (x != root && (x == null || !x.Red)) {
            if (x == parent.left) {
                sibling = parent.right;
                if (sibling.Red) {
                    sibling.Red = false;
                    parent.Red = true;
                    rotateLeft(parent);
                    sibling = parent.right;
                }
                if ((sibling.left == null || !sibling.left.Red) && (sibling.right == null || !sibling.right.Red)) {
                    sibling.Red = true;
                    x = parent;
                    parent = x.parent;
                } else {
                    if (sibling.right == null || !sibling.right.Red) {
                        if (sibling.left != null) {
                            sibling.left.Red = false;
                        }
                        sibling.Red = true;
                        rotateRight(sibling);
                        sibling = parent.right;
                    }
                    sibling.Red = parent.Red;
                    parent.Red = false;
                    if (sibling.right != null) {
                        sibling.right.Red = false;
                    }
                    rotateLeft(parent);
                    x = root;
                }
            } else {
                sibling = parent.left;
                if (sibling.Red) {
                    sibling.Red = false;
                    parent.Red = true;
                    rotateRight(parent);
                    sibling = parent.left;
                }
                if ((sibling.left == null || !sibling.left.Red) && (sibling.right == null || !sibling.right.Red)) {
                    sibling.Red = true;
                    x = parent;
                    parent = x.parent;
                } else {
                    if (sibling.left == null || !sibling.left.Red) {
                        if (sibling.right != null) {
                            sibling.right.Red = false;
                        }
                        sibling.Red = true;
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }
                    sibling.Red = parent.Red;
                    parent.Red = false;
                    if (sibling.left != null) {
                        sibling.left.Red = false;
                    }
                    rotateRight(parent);
                    x = root;
                }
            }
        }
        if (x != null) {
            x.Red = false;
        }
    }


    RedBlackTreeNode search(RedBlackTreeNode root, int element){
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