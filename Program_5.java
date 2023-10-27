class Node {
  int key, height;
  Node left, right;

  Node(int item) {
    key = item;
    height = 1;
  }
}

public class Program_5 {
  Node root;

  int height(Node node) {
    if (node == null)
      return 0;
    return node.height;
  }

  int getBalance(Node node) {
    if (node == null)
      return 0;
    return height(node.left) - height(node.right);
  }

  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    return x;
  }

  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    return y;
  }

  Node insert(Node node, int key) {
    if (node == null)
      return new Node(key);

    if (key < node.key)
      node.left = insert(node.left, key);
    else if (key > node.key)
      node.right = insert(node.right, key);
    else
      return node;

    node.height = 1 + Math.max(height(node.left), height(node.right));

    int balance = getBalance(node);

    if (balance > 1) {
      if (key < node.left.key)
        return rightRotate(node);
      else if (key > node.left.key) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
      }
    }

    if (balance < -1) {
      if (key > node.right.key)
        return leftRotate(node);
      else if (key < node.right.key) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
      }
    }

    return node;
  }

  Node minValueNode(Node node) {
    Node current = node;
    while (current.left != null)
      current = current.left;
    return current;
  }

  Node delete(Node root, int key) {
    if (root == null)
      return root;

    if (key < root.key)
      root.left = delete(root.left, key);
    else if (key > root.key)
      root.right = delete(root.right, key);
    else {
      if (root.left == null || root.right == null) {
        Node temp = null;
        if (root.left != null)
          temp = root.left;
        else
          temp = root.right;

        if (temp == null) {
          temp = root;
          root = null;
        } else
          root = temp;
      } else {
        Node temp = minValueNode(root.right);
        root.key = temp.key;
        root.right = delete(root.right, temp.key);
      }
    }

    if (root == null)
      return root;

    root.height = 1 + Math.max(height(root.left), height(root.right));
    int balance = getBalance(root);

    if (balance > 1) {
      if (getBalance(root.left) >= 0)
        return rightRotate(root);
      else {
        root.left = leftRotate(root.left);
        return rightRotate(root);
      }
    }

    if (balance < -1) {
      if (getBalance(root.right) <= 0)
        return leftRotate(root);
      else {
        root.right = rightRotate(root.right);
        return leftRotate(root);
      }
    }

    return root;
  }

  void preOrder(Node node) {
    if (node != null) {
      System.out.print(node.key + " ");
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  void search(int key) {
    if (searchNode(root, key))
      System.out.println("Element " + key + " found in the AVL tree.");
    else
      System.out.println("Element " + key + " not found in the AVL tree.");
  }

  boolean searchNode(Node node, int key) {
    if (node == null)
      return false;
    if (node.key == key)
      return true;
    if (node.key > key)
      return searchNode(node.left, key);
    return searchNode(node.right, key);
  }

  public static void main(String[] args) {
    Program_5 tree = new Program_5();

    tree.root = tree.insert(tree.root, 10);
    tree.root = tree.insert(tree.root, 20);
    tree.root = tree.insert(tree.root, 30);
    tree.root = tree.insert(tree.root, 40);
    tree.root = tree.insert(tree.root, 50);

    System.out.println("Preorder traversal of AVL tree:");
    tree.preOrder(tree.root);
    System.out.println();

    tree.search(30);

    System.out.println("AVL tree after deleting 20:");
    tree.root = tree.delete(tree.root, 20);
    tree.preOrder(tree.root);
  }
}
