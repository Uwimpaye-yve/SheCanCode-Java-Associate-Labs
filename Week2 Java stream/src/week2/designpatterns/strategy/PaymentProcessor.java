package week2.designpatterns.strategy;

public class PaymentProcessor {
    private PaymentStrategy strategy;

    // Allows setting strategy at runtime or initialization
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PaymentResult executePayment(PaymentRequest request) {
        if (this.strategy == null) {
            throw new IllegalStateException("Runtime Error: No payment strategy plugged into the system!");
        }
        return this.strategy.process(request);
    }
}