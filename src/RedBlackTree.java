//The implementation of Red-Black Tree and its functionalities: insert, delete, and search.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

public class RedBlackTree {

    static class RedBlackTreeNode{
        int element;
        boolean Red;
        RedBlackTreeNode left;
        RedBlackTreeNode right;
        RedBlackTreeNode parent;

        public RedBlackTreeNode(int element){ //Constructor for node in Red Black tree.
            this.element = element;
            this.Red = true; //Every new node is colored red.
            this.left = this.right = this.parent = null;
        }
    }

    RedBlackTreeNode root;

    RedBlackTree(){
        root = null;
    }


    public void insert(int element) { //A public function insert that iteratively traverses the tree and inserts a node, then balances the tree.
        RedBlackTreeNode newRedBlackTreeNode = new RedBlackTreeNode(element); //Create a new node colored red.

        RedBlackTreeNode parent = null; // An iterative approach to standard BST insertion, no balancing yet.
        RedBlackTreeNode current = root;
        while (current != null) {
            parent = current;
            if (element < current.element) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newRedBlackTreeNode.parent = parent; //Set the parent of the node.

        //Set the node as the left or right child of its parent.
        if (parent == null) {
            root = newRedBlackTreeNode; //Happens only if the tree is empty.
        } else if (element < parent.element) {
            parent.left = newRedBlackTreeNode;
        } else {
            parent.right = newRedBlackTreeNode;
        }

        InsertBalanceRedBlackTree(newRedBlackTreeNode); //Balance the tree.
    }

    private void InsertBalanceRedBlackTree(RedBlackTreeNode node) {
        if (node == root) { //Case where node is the root, so all that has to be done is color it black.
            node.Red = false;
            return;
        }

        if (node.parent.Red == false) { //Case where parent of the node is black, in this case no balancing needed.
            return;
        }

        //Case where parent and the uncle of the node are both red, color them black and move the tree up recursively..
        RedBlackTreeNode parent = node.parent;
        RedBlackTreeNode uncle = getUncle(node);

        if (uncle != null && uncle.Red) {
            parent.Red = false;
            uncle.Red = false;
            parent.parent.Red = true; //parent.parent = grandparent
            InsertBalanceRedBlackTree(parent.parent); //Here the recursion happens.
            return;
        }

        //Case where the node is a right child and its parent is a left child - left rotation required.
        if (node == parent.right && parent == parent.parent.left) {
            RedBlackTreeLeftRotation(parent);
            node = node.left;
        } else if (node == parent.left && parent == parent.parent.right) { //Case where the node is a left child and its parent is a right child - right rotation required.
            RedBlackTreeRightRotation(parent);
            node = node.right;
        }

        //Case where the node's parent is red, but the uncle is black - recoloring and rotation required.
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

    private RedBlackTreeNode getUncle(RedBlackTreeNode node) { //Helper function that returns the uncle of the node.
        RedBlackTreeNode grandparent = node.parent.parent;
        if (grandparent == null) {
            return null; //No grandparent, so uncle does not exist.
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

    public void delete(int element) { //A public function delete that iteratively traverses the tree and if node to be deleted is found, it calls the private delete method.
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

        if (nodeToDelete.left != null && nodeToDelete.right != null) { //Case where the node has two children.
            RedBlackTreeNode successor = nodeToDelete.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            nodeColor = successor.Red;
            nodeToDelete.element = successor.element;
            nodeToDelete = successor;
        }

        //Case where node has one child or no children.
        child = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;

        if (child != null) {
            child.parent = nodeToDelete.parent;
        }

        if (nodeToDelete.parent == null) { //If node to be deleted is the root, so has no parent, then the root is child itself.
            root = child;
        } else if (nodeToDelete == nodeToDelete.parent.left) {
            nodeToDelete.parent.left = child;
        } else {
            nodeToDelete.parent.right = child;
        }
        if (nodeColor == false) { //Eventually, balance the tree.
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
            if (node == parent.left) { //Case 1 where the deleted node is the left child.
                sibling = parent.right;
                if (sibling.Red == true) { //1.1 When the sibling of the deleted node is red.
                    sibling.Red = false;
                    parent.Red = true;
                    RedBlackTreeLeftRotation(parent);
                    sibling = parent.right;
                }
                if ((sibling.left == null || sibling.left.Red == false) && (sibling.right == null || sibling.right.Red == false)) { //2.1 Both children of the sibling of the deleted node are black.
                    sibling.Red = true;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling.right == null || sibling.right.Red == false) { //3.1 The sibling of the deleted node has a black right child and red left child.
                        if (sibling.left != null) {
                            sibling.left.Red = false;
                        }
                        sibling.Red = true;
                        RedBlackTreeRightRotation(sibling);
                        sibling = parent.right;
                    }
                    sibling.Red = parent.Red; //4.1 Sibling is black, and its right child is red.
                    parent.Red = false;
                    if (sibling.right != null) {
                        sibling.right.Red = false;
                    }
                    RedBlackTreeLeftRotation(parent);
                    node = root;
                }
            } else { //Case 2 where deleted node is the right child.
                sibling = parent.left;
                if (sibling.Red == true) { //1.2 When the sibling of the deleted node is red.
                    sibling.Red = false;
                    parent.Red = true;
                    RedBlackTreeRightRotation(parent);
                    sibling = parent.left;
                }
                if ((sibling.left == null || sibling.left.Red == false) && (sibling.right == null || sibling.right.Red == false)) { //2.2 Both children of the sibling of the deleted node are black.
                    sibling.Red = true;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling.left == null || sibling.left.Red == false) { //3.2 The sibling of the deleted node has a black left child and red right child.
                        if (sibling.right != null) {
                            sibling.right.Red = false;
                        }
                        sibling.Red = true;
                        RedBlackTreeLeftRotation(sibling);
                        sibling = parent.left;
                    }
                    sibling.Red = parent.Red; //4.2 Sibling is black, and its left child is red.
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
        if (element < root.element) { //Recursively traverse the tree and return found root (returns null if node does not exist in the tree).
            return search(root.left, element);
        } else if (element > root.element) {
            return search(root.right, element);
        } else {
            return root;
        }
    }
}