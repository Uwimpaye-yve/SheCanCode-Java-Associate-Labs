package week2.designpatterns.strategy;

public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public PaymentResult process(PaymentRequest request) {
        if (request.getAccountDetails() == null || request.getAccountDetails().length() < 16) {
            return new PaymentResult(false, request.getAmount(), "Invalid Card Number length.");
        }
        double fee = request.getAmount() * 0.025; // 2.5% transaction fee
        return new PaymentResult(true, request.getAmount() + fee, "Credit Card Payment Processed Successfully via Stripe Gateway.");
    }
}