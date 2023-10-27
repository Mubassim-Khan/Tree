import java.util.Stack;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    // Recursive Pre-order Traversal
    void recursivePreorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        recursivePreorder(node.left);
        recursivePreorder(node.right);
    }

    // Non-Recursive Pre-order Traversal
    void nonRecursivePreorder() {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null)
                stack.push(current.right);

            if (current.left != null)
                stack.push(current.left);
        }
    }

    // Recursive In-order Traversal
    void recursiveInorder(Node node) {
        if (node == null)
            return;

        recursiveInorder(node.left);
        System.out.print(node.data + " ");
        recursiveInorder(node.right);
    }

    // Non-Recursive In-order Traversal
    void nonRecursiveInorder() {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    // Recursive Post-order Traversal
    void recursivePostorder(Node node) {
        if (node == null)
            return;

        recursivePostorder(node.left);
        recursivePostorder(node.right);
        System.out.print(node.data + " ");
    }

    // Non-Recursive Post-order Traversal
    void nonRecursivePostorder() {
        if (root == null)
            return;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null)
                stack1.push(current.left);

            if (current.right != null)
                stack1.push(current.right);
        }

        while (!stack2.isEmpty()) {
            Node current = stack2.pop();
            System.out.print(current.data + " ");
        }
    }
}

public class Program_3 {
public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Recursive Pre-order:");
        tree.recursivePreorder(tree.root);
        System.out.println("\nNon-Recursive Pre-order:");
        tree.nonRecursivePreorder();

        System.out.println("\n\nRecursive In-order:");
        tree.recursiveInorder(tree.root);
        System.out.println("\nNon-Recursive In-order:");
        tree.nonRecursiveInorder();

        System.out.println("\n\nRecursive Post-order:");
        tree.recursivePostorder(tree.root);
        System.out.println("\nNon-Recursive Post-order:");
        tree.nonRecursivePostorder();
        System.out.println("\n");
    }
}
