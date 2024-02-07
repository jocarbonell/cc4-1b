import java.util.*;

public class StackOrQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1. Stack\n2. Queue\nEnter your preference: "); // user choose if stack or queue
        int preference = scanner.nextInt(); // stores the choice of the user's input
        if (preference == 1) { // user chose stack
            System.out.print("Enter the upper bound: "); // user's input
            int upperBound = scanner.nextInt();
            stackProgram(upperBound, scanner);
        } else if (preference == 2) { // user chose queue
            System.out.print("Enter the upper bound: ");
            int upperBound = scanner.nextInt();
            queueProgram(upperBound, scanner);
        } else {
            System.out.println("Invalid choice. Please enter '1' for Stack or '2' for Queue.");
        }
        scanner.close();
    }

    public static void stackProgram(int upperBound, Scanner scanner) {
        Stack<Integer> stack = new Stack<>();
        int action = 0;
        while (action != 3) {
            System.out.print("1. Push\n2. Pop\n3. Finish\nEnter your choice: ");
            action = scanner.nextInt();
            if (action == 1) { // Push operation
                if (stack.size() < upperBound) {   
                    System.out.print("Enter item to push into the stack: ");
                    int item = scanner.nextInt();
                    stack.push(item);
                    System.out.println("Pushed " + item + " into the stack.");
                } else {
                    System.out.println("The stack is full.");
                }
            } else if (action == 2) { // Pop operation
                if (!stack.isEmpty()) {
                    System.out.println("Popped " + stack.pop() + " from the stack.");
                } else {
                    System.out.println("The stack is empty.");
                }
            } else if (action != 3) {
                System.out.println("Invalid choice.");
            }
        }
        System.out.println("The final stack is: " + stack);
    }

    public static void queueProgram(int upperBound, Scanner scanner) {
        Queue<Integer> queue = new LinkedList<>();
        int action = 0;
        while (action != 3) {
            System.out.print("1. Enqueue\n2. Dequeue\n3. Finish\nEnter your choice: ");
            action = scanner.nextInt();
            if (action == 1) { // Enqueue operation
                if (queue.size() < upperBound) {
                    System.out.print("Enter item to enqueue: ");
                    int item = scanner.nextInt();
                    queue.offer(item);
                    System.out.println("Enqueued " + item + " into the queue.");
                } else {
                    System.out.println("The queue is full.");
                }
            } else if (action == 2) { // Dequeue operation
                if (!queue.isEmpty()) {
                    int dequeuedItem = queue.poll();
                    System.out.println("Dequeued " + dequeuedItem + " from the queue.");
                } else {
                    System.out.println("The queue is empty.");
                }
            } else if (action == 3) {
                System.out.println("Finishing queue operations.");
                break; // Exit the while loop
            } else {
                System.out.println("Invalid choice.");
            }
        }
        System.out.println("The final queue is: " + queue);
    }
    }