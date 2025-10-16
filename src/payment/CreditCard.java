package payment;
//implements PaymentStrategy
import java.util.Scanner;

public class CreditCard implements PaymentStrategy {
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Card Number: ");
        cardNumber = scanner.nextLine().trim();
        System.out.print("Expiration (MM/YY): ");
        expirationDate = scanner.nextLine().trim();
        System.out.print("CVV: ");
        cvv = scanner.nextLine().trim();
    }

    @Override
    public boolean pay(int amount) {
        if (isValid()) {
            System.out.println("Processing: $" + (amount / 100.0) + " via Credit Card");
            System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
            return true;
        }
        System.out.println("Invalid card data");
        return false;
    }

    @Override
    public String getMethodName() {
        return "CREDIT_CARD";
    }

    private boolean isValid() {
        return cardNumber != null && cardNumber.matches("\\d{13,19}") &&
                expirationDate != null && expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                cvv != null && cvv.matches("\\d{3,4}");
    }
}