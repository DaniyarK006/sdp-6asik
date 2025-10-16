// PaymentStrategy.java
package payment;
//interface
public interface PaymentStrategy {
    void collectPaymentDetails();
    boolean pay(int amount);
    String getMethodName();
}