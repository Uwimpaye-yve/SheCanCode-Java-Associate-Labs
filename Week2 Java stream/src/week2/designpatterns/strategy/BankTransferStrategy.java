package week2.designpatterns.strategy;

public class BankTransferStrategy implements PaymentStrategy {
    @Override
    public PaymentResult process(PaymentRequest request) {
        if (!request.getAccountDetails().contains("-")) {
            return new PaymentResult(false, request.getAmount(), "Invalid Bank Routing/Account structure.");
        }
        double flatFee = 1000.0; // Flat 1,000 RWF processing cost
        return new PaymentResult(true, request.getAmount() + flatFee, "Bank Wire Transfer Processed Successfully via Core Banking Node.");
    }
}