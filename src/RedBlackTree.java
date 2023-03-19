//The implementation of Red-Black Tree and its functionalities: insert, delete, and search
//Petra Miková, ID: 120852, summer term 22/23 - DSA

public class RedBlackTree {

    static class RedBlackTreeNode{
        int element;
        boolean Red;
        RedBlackTreeNode left;
        RedBlackTreeNode right;
        RedBlackTreeNode parent;

        public RedBlackTreeNode(int element){ //constructor for node in Red-Black tree
            this.element = element;
            this.Red = true; //every new node is colored red
            this.left = this.right = this.parent = null;
        }
    }

    RedBlackTreeNode root;

    RedBlackTree(){
        root = null;
    }


    public void insert(int element) { //
        RedBlackTreeNode newRedBlackTreeNode = new RedBlackTreeNode(element); //create a new node colored red

        RedBlackTreeNode parent = null; // iterative standard BST insertion
        RedBlackTreeNode current = root;
        while (current != null) {
            parent = current;
            if (element < current.element) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newRedBlackTreeNode.parent = parent; //set the parent of the node

        if (parent == null) { //now set the node as the left or right child
            root = newRedBlackTreeNode; // only if tree is empty
        } else if (element < parent.element) {
            parent.left = newRedBlackTreeNode;
        } else {
            parent.right = newRedBlackTreeNode;
        }

        InsertBalanceRedBlackTree(newRedBlackTreeNode); //balance the tree
    }

    private void InsertBalanceRedBlackTree(RedBlackTreeNode node) {
        if (node == root) { //case where node is the root, so we just need to color it black
            node.Red = false;
            return;
        }

        if (node.parent.Red == false) { //case where parent of the node is black, no balancing needed
            return;
        }

        //case where parent and the uncle of the node are both red, color them black and move tree up
        RedBlackTreeNode parent = node.parent;
        RedBlackTreeNode uncle = getUncle(node);

        if (uncle != null && uncle.Red) {
            parent.Red = false;
            uncle.Red = false;
            parent.parent.Red = true; //grandparent
            InsertBalanceRedBlackTree(parent.parent); //recursion
            return;
        }

        //case where the node is a right child and its parent is a left child - left rotation required
        if (node == parent.right && parent == parent.parent.left) {
            RedBlackTreeLeftRotation(parent);
            node = node.left;
        } else if (node == parent.left && parent == parent.parent.right) { //case where the node is a left child and its parent is a right child - right rotation required
            RedBlackTreeRightRotation(parent);
            node = node.right;
        }

        //case where the node's parent is red, but the uncle is black - recoloring and rotation required
        parent = node.parent;
        RedBlackTreeNode grandparent = parent.parent;
        parent.Red = false;
        grandparent.Red = true;

        if (node == parent.left && parent == grandparent.left) {
            RedBlackTreeRightRotation(grandparent);
        } else {
            RedBlackTreeLeftRotation(grandparent);
        }
    }

    private RedBlackTreeNode getUncle(RedBlackTreeNode node) { //uncle getter
        RedBlackTreeNode grandparent = node.parent.parent;
        if (grandparent == null) {
            return null; //no grandparent, so uncle does not exist
        }
        if (node.parent == grandparent.left) {
            return grandparent.right;
        } else {
            return grandparent.left;
        }
    }

    private void RedBlackTreeRightRotation(RedBlackTreeNode node) {
        RedBlackTreeNode leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }
    private void RedBlackTreeLeftRotation(RedBlackTreeNode node) {
        RedBlackTreeNode rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    public void delete(int element) {
        RedBlackTreeNode nodeToDelete = root;
        while (nodeToDelete != null) {
            if (element == nodeToDelete.element) {
                delete(nodeToDelete);
                return;
            } else if (element < nodeToDelete.element) {
                nodeToDelete = nodeToDelete.left;
            } else {
                nodeToDelete = nodeToDelete.right;
            }
        }
    }

    private void delete(RedBlackTreeNode nodeToDelete) {
        RedBlackTreeNode child;
        boolean nodeColor = nodeToDelete.Red;

        if (nodeToDelete.left != null && nodeToDelete.right != null) { //case where node has two children
            RedBlackTreeNode successor = nodeToDelete.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            nodeColor = successor.Red;
            nodeToDelete.element = successor.element;
            nodeToDelete = successor;
        }

        // case where node has one child or no children
        child = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;

        if (child != null) {
            child.parent = nodeToDelete.parent;
        }

        if (nodeToDelete.parent == null) { //if node to be deleted is the root, so has no parent
            root = child;
        } else if (nodeToDelete == nodeToDelete.parent.left) {
            nodeToDelete.parent.left = child;
        } else {
            nodeToDelete.parent.right = child;
        }
        if (nodeColor == false) { //balance the tree
            if (child != null && child.Red) {
                child.Red = false;
            } else {
                DeleteBalanceRedBlackTree(child, nodeToDelete.parent);
            }
        }
    }


    private void DeleteBalanceRedBlackTree(RedBlackTreeNode node, RedBlackTreeNode parent) {
        RedBlackTreeNode sibling;
        while (node != root && (node == null || node.Red == false)) {
            if (node == parent.left) { //case where deleted node is the left child
                sibling = parent.right;
                if (sibling.Red == true) { //1.1 when the sibling of the deleted node is red
                    sibling.Red = false;
                    parent.Red = true;
                    RedBlackTreeLeftRotation(parent);
                    sibling = parent.right;
                }
                if ((sibling.left == null || sibling.left.Red == false) && (sibling.right == null || sibling.right.Red == false)) { //2.1 both children of the sibling of the deleted node are black
                    sibling.Red = true;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling.right == null || sibling.right.Red == false) { //3.1 the sibling of the deleted node has a black right child and red left child
                        if (sibling.left != null) {
                            sibling.left.Red = false;
                        }
                        sibling.Red = true;
                        RedBlackTreeRightRotation(sibling);
                        sibling = parent.right;
                    }
                    sibling.Red = parent.Red; //4.1 sibling is black, and its right child is red
                    parent.Red = false;
                    if (sibling.right != null) {
                        sibling.right.Red = false;
                    }
                    RedBlackTreeLeftRotation(parent);
                    node = root;
                }
            } else { //case where deleted node is the right child
                sibling = parent.left;
                if (sibling.Red == true) { //1.2 when the sibling of the deleted node is red
                    sibling.Red = false;
                    parent.Red = true;
                    RedBlackTreeRightRotation(parent);
                    sibling = parent.left;
                }
                if ((sibling.left == null || sibling.left.Red == false) && (sibling.right == null || sibling.right.Red == false)) { //2.2 both children of the sibling of the deleted node are black
                    sibling.Red = true;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling.left == null || sibling.left.Red == false) { //3.2 the sibling of the deleted node has a black left child and red right child
                        if (sibling.right != null) {
                            sibling.right.Red = false;
                        }
                        sibling.Red = true;
                        RedBlackTreeLeftRotation(sibling);
                        sibling = parent.left;
                    }
                    sibling.Red = parent.Red; //4.2 sibling is black, and its left child is red
                    parent.Red = false;
                    if (sibling.left != null) {
                        sibling.left.Red = false;
                    }
                    RedBlackTreeRightRotation(parent);
                    node = root;
                }
            }
        }
        if (node != null) {
            node.Red = false;
        }
    }


    public void search(int element){
        RedBlackTreeNode found = search(root, element);
    }

    private RedBlackTreeNode search(RedBlackTreeNode root, int element){
        if (root == null) {
            return null;
        }
        if (element < root.element) {
            return search(root.left, element);
        } else if (element > root.element) {
            return search(root.right, element);
        } else {
            return root;
        }
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