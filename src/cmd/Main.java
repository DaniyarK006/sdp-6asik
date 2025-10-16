package cmd;
//client/entry point
import payment.CreditCard;
import payment.Crypto;
import payment.PayPal;
import payment.Processor;
import java.util.Scanner;

public class Main {
    private final Processor processor;
    private final Scanner scanner;

    public Main() {
        processor = new Processor();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("PAYMENT SYSTEM - STRATEGY PATTERN\n");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getInput();

            switch (choice) {
                case 1:
                    processor.setStrategy(new CreditCard());
                    processPayment();
                    break;
                case 2:
                    processor.setStrategy(new PayPal());
                    processPayment();
                    break;
                case 3:
                    processor.setStrategy(new Crypto());
                    processPayment();
                    break;
                case 4:
                    processor.showTransactions();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice\n");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cryptocurrency");
        System.out.println("4. View History");
        System.out.println("5. Exit");
        System.out.print("Select: ");
    }

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processPayment() {
        System.out.print("Amount ($): ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            int amountCents = (int) (amount * 100);

            if (amountCents <= 0) {
                System.out.println("Amount must be > 0\n");
                return;
            }

            boolean success = processor.processPayment(amountCents);
            System.out.println(success ? "Success\n" : "Failed\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount\n");
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
}