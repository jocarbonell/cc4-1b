import java.util.*;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null)
            return new Node(key);
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else
            root.right = insertRec(root.right, key);
        return root;
    }

    void deleteKey(int key, boolean deleteFromLeft) {
        root = deleteRec(root, key, deleteFromLeft);
    }

    Node deleteRec(Node root, int key, boolean deleteFromLeft) {
        if (root == null) return null;
        if (key < root.key)
            root.left = deleteRec(root.left, key, deleteFromLeft);
        else if (key > root.key)
            root.right = deleteRec(root.right, key, deleteFromLeft);
        else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            root.key = deleteFromLeft ? maxValue(root.left) : minValue(root.right);
            if (deleteFromLeft)
                root.left = deleteRec(root.left, root.key, deleteFromLeft);
            else
                root.right = deleteRec(root.right, root.key, deleteFromLeft);
        }
        return root;
    }

    int maxValue(Node root) {
        return root.right == null ? root.key : maxValue(root.right);
    }

    int minValue(Node root) {
        return root.left == null ? root.key : minValue(root.left);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.key + " ");
        }
    }

    void toListWithPlaceholders() {
        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            list.add(node != null ? node.key : 0);
            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        System.out.println("Array representation: " + list);
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        BST bst = new BST();
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("1. Insert\n2. Delete\n3. End\nEnter your choice: ");
            choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter the value to insert: ");
                bst.insert(scanner.nextInt());
            } else if (choice == 2) {
                System.out.print("Enter the value to delete: ");
                int value = scanner.nextInt();
                System.out.print("Delete from:\n1. Left\n2. Right\nEnter your choice: ");
                bst.deleteKey(value, scanner.nextInt() == 1);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
        bst.toListWithPlaceholders();
        System.out.println("\nInorder traversal: ");
        bst.inorder(bst.root);
        System.out.println("\n\nPreorder traversal: ");
        bst.preorder(bst.root);
        System.out.println("\n\nPostorder traversal: ");
        bst.postorder(bst.root);
        System.out.println();
    }
}