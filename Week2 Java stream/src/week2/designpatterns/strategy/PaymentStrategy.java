package week2.designpatterns.strategy;

public interface PaymentStrategy {
    PaymentResult process(PaymentRequest request);
}