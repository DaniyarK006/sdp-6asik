package payment;
//implements PaymentStrategy
import java.util.Scanner;

public class Crypto implements PaymentStrategy {
    private String walletAddress;
    private String privateKey;
    private String cryptoType;

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Crypto Type (BTC/ETH/USDC): ");
        cryptoType = scanner.nextLine().trim().toUpperCase();
        System.out.print("Wallet Address: ");
        walletAddress = scanner.nextLine().trim();
        System.out.print("Private Key: ");
        privateKey = scanner.nextLine().trim();
    }

    @Override
    public boolean pay(int amount) {
        if (isValid()) {
            System.out.println("Processing: $" + (amount / 100.0) + " via " + cryptoType);
            System.out.println("Wallet: " + maskWallet(walletAddress));
            System.out.println("TX Hash: " + generateHash());
            return true;
        }
        System.out.println("Invalid wallet data");
        return false;
    }

    @Override
    public String getMethodName() {
        return "CRYPTO_" + cryptoType;
    }

    private boolean isValid() {
        return walletAddress != null && walletAddress.length() >= 26 &&
                privateKey != null && privateKey.length() >= 32;
    }

    private String maskWallet(String wallet) {
        return wallet.substring(0, 6) + "..." + wallet.substring(wallet.length() - 4);
    }

    private String generateHash() {
        return "0x" + Long.toHexString(System.currentTimeMillis());
    }
}