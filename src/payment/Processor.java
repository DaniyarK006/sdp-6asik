package payment;
//context
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    private PaymentStrategy strategy;
    private final List<String> transactions;

    public Processor() {
        this.transactions = new ArrayList<>();
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
        System.out.println("Payment method: " + strategy.getMethodName() + "\n");
    }

    public boolean processPayment(int amount) {
        if (strategy == null) {
            System.out.println("No payment strategy selected");
            return false;
        }

        strategy.collectPaymentDetails();
        boolean success = strategy.pay(amount);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String record = "[" + timestamp + "] " + strategy.getMethodName() +
                " - $" + (amount / 100.0) + " - " + (success ? "SUCCESS" : "FAILED");
        transactions.add(record);

        return success;
    }

    public void showTransactions() {
        System.out.println("\nTransaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
        } else {
            transactions.forEach(System.out::println);
        }
        System.out.println();
    }
}