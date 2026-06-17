// Extending RuntimeException makes this an UNCHECKED/Runtime error
public class DataAccessException extends RuntimeException {

    // This constructor accepts BOTH a custom message AND the original root cause error
    public DataAccessException(String message, Throwable cause) {
        super(message, cause); // Passes both up to Java's base error framework
    }
}