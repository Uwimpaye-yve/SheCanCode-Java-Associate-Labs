public class FraudDetectedException extends TransactionException {
    public FraudDetectedException(String message) {
        // "TX_ERR_FRAUD" is our code for flagging suspicious activity
        super(message, "TX_ERR_FRAUD");
    }
}