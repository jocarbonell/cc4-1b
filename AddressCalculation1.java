import java.util.Scanner;

public class AddressCalculation1  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int base = inputInt("Base Address (B): ", scanner);
        int weight = inputInt("Weight (w): ", scanner);
        int dimensions = inputInt("Number of Dimensions: ", scanner);

        int[] bounds = new int[dimensions];
        int[] indices = new int[dimensions];

        for (int i = 0; i < dimensions; i++) {
            bounds[i] = inputInt("Upper Bound " + (i + 1) + ": ", scanner);
            indices[i] = inputInt("Array Index " + (i + 1) + ": ", scanner);
        }

        scanner.close();

        int sum = 0;
        for (int i = 0; i < dimensions; i++) {
            int temp = indices[i];
            for (int j = i + 1; j < dimensions; j++) {
                temp *= bounds[j];
            }
            sum += temp;
        }

        int address = base + weight * sum;
        System.out.println("Address: " + address);
    }

    public static int inputInt(String message, Scanner scanner) {
        int input = 0;

        while (true) {
            try {
                System.out.print(message);
                input = Integer.parseInt(scanner.nextLine());

                if (input >= 0) {
                    break;
                } else {
                    System.out.println("Invalid Input. Please enter a non-negative integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid integer.");
            }
        }

        return input;
    }
}
    

