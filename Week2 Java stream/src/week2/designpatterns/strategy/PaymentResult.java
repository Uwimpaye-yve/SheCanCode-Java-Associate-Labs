package week2.designpatterns.strategy;

public class PaymentResult {
    private final boolean success;
    private final double finalAmountWithFee;
    private final String message;

    public PaymentResult(boolean success, double finalAmountWithFee, String message) {
        this.success = success;
        this.finalAmountWithFee = finalAmountWithFee;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public double getFinalAmountWithFee() { return finalAmountWithFee; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return "PaymentResult{success=" + success + ", total=" + finalAmountWithFee + ", details='" + message + "'}";
    }
}