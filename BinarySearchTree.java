import java.util.*;

class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
    }
}

class BST {
    Node root;

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null)
            return new Node(key);

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) return null;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        return root.left == null ? root.key : minValue(root.left);
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    void toListWithPlaceholders() {
        if (root == null) return;

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
            if (choice == 1 || choice == 2) {
                System.out.print("Enter the value to " + (choice == 1 ? "insert" : "delete") + ": ");
                int item = scanner.nextInt();
                if (choice == 1) bst.insert(item);
                else bst.deleteKey(item);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
        bst.toListWithPlaceholders();
        System.out.println("\nInorder traversal: ");
        bst.inorder();
        System.out.println();
    }
}