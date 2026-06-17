// Extending 'Exception' makes this a CHECKED exception
public class TransactionException extends Exception {
    private final String errorCode;

    public TransactionException(String message, String errorCode) {
        // Super passes our custom message up to Java's default Exception system
        super("[" + errorCode + "] " + message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}