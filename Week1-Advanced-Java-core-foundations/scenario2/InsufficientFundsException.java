public class InsufficientFundsException extends TransactionException {
    public InsufficientFundsException(String message) {
        // "TX_ERR_BAL" is our dedicated error code for balance problems
        super(message, "TX_ERR_BAL");
    }
}