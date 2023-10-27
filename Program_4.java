class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // a) Search a given item
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null)
            return false;

        if (root.data == key)
            return true;

        if (key < root.data)
            return searchRec(root.left, key);
        else
            return searchRec(root.right, key);
    }

    // b) Insertion of a new node
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.data)
            root.left = insertRec(root.left, key);
        else if (key > root.data)
            root.right = insertRec(root.right, key);

        return root;
    }

    // c) Maximum element of the BST
    public int findMax() {
        Node current = root;
        while (current.right != null)
            current = current.right;
        return current.data;
    }

    // d) Minimum element of the BST
    public int findMin() {
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.data;
    }

    // e) Successor of the BST
    public int successor(int key) {
        return successorRec(root, key);
    }

    private int successorRec(Node root, int key) {
        Node current = searchNode(root, key);

        if (current == null)
            return -1;

        if (current.right != null) {
            current = current.right;
            while (current.left != null)
                current = current.left;
            return current.data;
        } else {
            Node successor = null;
            Node ancestor = root;

            while (ancestor != current) {
                if (current.data < ancestor.data) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else
                    ancestor = ancestor.right;
            }
            return successor != null ? successor.data : -1;
        }
    }

    private Node searchNode(Node root, int key) {
        if (root == null || root.data == key)
            return root;

        if (key < root.data)
            return searchNode(root.left, key);

        return searchNode(root.right, key);
    }

    // f) Delete a given node from the BST
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // In-order traversal to print the tree
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }
}

public class Program_4 {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("a) In-order Traversal:");
        tree.inOrder();

        int searchKey = 40;
        if (tree.search(searchKey))
            System.out.println("\nb) Item " + searchKey + " found in the tree.");
        else
            System.out.println("\nb) Item " + searchKey + " not found in the tree.");

        System.out.println("c) Maximum element in the tree: " + tree.findMax());
        System.out.println("d) Minimum element in the tree: " + tree.findMin());

        int successorKey = 30;
        int successor = tree.successor(successorKey);
        if (successor != -1)
            System.out.println("e) Successor of " + successorKey + " is: " + successor);
        else
            System.out.println("e) No successor found for " + successorKey);

        int deleteKey = 40;
        System.out.println("f) Deleting item " + deleteKey + " from the tree.");
        tree.delete(deleteKey);
        System.out.println("\nIn-order Traversal after deletion:");
        tree.inOrder();
    }
}
