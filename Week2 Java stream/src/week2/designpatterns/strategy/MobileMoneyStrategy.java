package week2.designpatterns.strategy;

public class MobileMoneyStrategy implements PaymentStrategy {
    @Override
    public PaymentResult process(PaymentRequest request) {
        if (!request.getAccountDetails().startsWith("078") && !request.getAccountDetails().startsWith("079")) {
            return new PaymentResult(false, request.getAmount(), "Invalid Mobile Money Carrier prefix.");
        }
        double fee = request.getAmount() * 0.01; // 1% MoMo processing agent fee
        return new PaymentResult(true, request.getAmount() + fee, "Mobile Money Transaction Processed Successfully via SPENN/Mali Gateway.");
    }
}