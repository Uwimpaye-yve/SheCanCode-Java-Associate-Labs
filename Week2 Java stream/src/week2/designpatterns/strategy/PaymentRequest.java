package week2.designpatterns.strategy;

public class PaymentRequest {
    private final double amount;
    private final String accountDetails;

    public PaymentRequest(double amount, String accountDetails) {
        this.amount = amount;
        this.accountDetails = accountDetails;
    }

    public double getAmount() { return amount; }
    public String getAccountDetails() { return accountDetails; }
}