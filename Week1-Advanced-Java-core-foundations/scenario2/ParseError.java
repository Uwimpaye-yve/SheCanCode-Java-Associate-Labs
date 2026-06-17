public class ParseError {
    private final int lineNumber;
    private final String rawContent;
    private final String reason;

    public ParseError(int lineNumber, String rawContent, String reason) {
        this.lineNumber = lineNumber;
        this.rawContent = rawContent;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Line " + lineNumber + " Failure | Raw Data: [" + rawContent + "] | Reason: " + reason;
    }
}