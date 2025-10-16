package payment;
//implements PaymentStrategy
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PayPal implements PaymentStrategy {
    private String email;
    private String password;
    private boolean authenticated;
    private static final Map<String, String> DATABASE = new HashMap<>();

    static {
        DATABASE.put("user@example.com", "pass123");
        DATABASE.put("admin@paypal.com", "admin123");
    }

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (!authenticated && attempts < 3) {
            System.out.print("PayPal Email: ");
            email = scanner.nextLine().trim();
            System.out.print("Password: ");
            password = scanner.nextLine().trim();

            if (authenticate()) {
                authenticated = true;
                System.out.println("Authentication successful");
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts: " + (3 - attempts));
            }
        }
    }

    @Override
    public boolean pay(int amount) {
        if (authenticated) {
            System.out.println("Processing: $" + (amount / 100.0) + " via PayPal");
            System.out.println("Account: " + maskEmail(email));
            return true;
        }
        System.out.println("Not authenticated");
        return false;
    }

    @Override
    public String getMethodName() {
        return "PAYPAL";
    }

    private boolean authenticate() {
        return DATABASE.containsKey(email) && DATABASE.get(email).equals(password);
    }

    private String maskEmail(String email) {
        int at = email.indexOf('@');
        return email.charAt(0) + "***" + email.substring(at);
    }
}