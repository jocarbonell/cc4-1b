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
    Set<Integer> keys;

    BST() {
        root = null;
        keys = new HashSet<>();
    }

    void insert(int key) {
        if (key == 0) {
            System.out.println("Sorry, invalid input. Please enter a non-zero integer.");
            return;
        }
        if (keys.contains(key)) {
            System.out.println("Key already exists. Please enter a different key.");
            return;
        }
        root = insertRec(root, key);
        keys.add(key);
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
        if (key == 0) {
            System.out.println("Sorry, invalid input. Please enter a non-zero integer.");
            return;
        }
        root = deleteRec(root, key, deleteFromLeft);
        keys.remove(key);
    }

    Node deleteRec(Node root, int key, boolean deleteFromLeft) {
        if (root == null) return null;
        if (key < root.key)
            root.left = deleteRec(root.left, key, deleteFromLeft);
        else if (key > root.key)
            root.right = deleteRec(root.right, key, deleteFromLeft);
        else {
            if (deleteFromLeft)
                root.left = deleteRec(root.left, key, deleteFromLeft);
            else
                root.right = deleteRec(root.right, key, deleteFromLeft);
        }
        return root;
    }

    void inorder(Node root) {
        Set<Integer> visited = new HashSet<>();
        inorder(root, visited);
    }

    void inorder(Node root, Set<Integer> visited) {
        if (root != null) {
            inorder(root.left, visited);
            if (!visited.contains(root.key) && root.key != 0) {
                System.out.print(root.key + " ");
                visited.add(root.key);
            }
            inorder(root.right, visited);
        }
    }

    void preorder(Node root) {
        Set<Integer> visited = new HashSet<>();
        preorder(root, visited);
    }

    void preorder(Node root, Set<Integer> visited) {
        if (root != null) {
            if (!visited.contains(root.key) && root.key != 0) {
                System.out.print(root.key + " ");
                visited.add(root.key);
            }
            preorder(root.left, visited);
            preorder(root.right, visited);
        }
    }

    void postorder(Node root) {
        Set<Integer> visited = new HashSet<>();
        postorder(root, visited);
    }

    void postorder(Node root, Set<Integer> visited) {
        if (root != null) {
            postorder(root.left, visited);
            postorder(root.right, visited);
            if (!visited.contains(root.key) && root.key != 0) {
                System.out.print(root.key + " ");
                visited.add(root.key);
            }
        }
    }

    void toListWithPlaceholders() {
        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null && !visited.contains(node.key) && node.key != 0) {
                list.add(node.key);
                visited.add(node.key);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                list.add(null); // Placeholder instead of 0
            }
        }
        System.out.println("Array representation: " + list);
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        BST bst = new BST();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("1. Insert\n2. Delete\n3. End\nEnter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 3) break;
            if (choice == 1 || choice == 2) {
                if (choice == 1) {
                    System.out.print("Enter the value to insert: ");
                    int value = scanner.nextInt();
                    bst.insert(value);
                } else {
                    System.out.print("Enter the value to delete: ");
                    int value = scanner.nextInt();
                    System.out.print("Delete from:\n1. Left\n2. Right\nEnter your choice: ");
                    boolean deleteFromLeft = scanner.nextInt() == 1;
                    bst.deleteKey(value, deleteFromLeft);
                }
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
    }
}