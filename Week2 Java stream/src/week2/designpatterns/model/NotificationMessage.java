package week2.designpatterns.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage {

    // 1. Core Fields (Immutable once built)
    private final String recipient;
    private final String subject;
    private final String body;
    private final Priority priority;
    private final List<String> attachments;

    // 2. Priority Level Enum definition
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    // 3. Strict Private Constructor: Forces instantiation ONLY via the Builder
    private NotificationMessage(Builder builder) {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = new ArrayList<>(builder.attachments);
    }

    // 4. Clean Getters
    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public Priority getPriority() { return priority; }
    public List<String> getAttachments() { return new ArrayList<>(attachments); }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "to='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", priority=" + priority +
                ", attachmentsCount=" + attachments.size() +
                '}';
    }

    // ==========================================
    // 5. The Fluent Static Inner Builder Class
    // ==========================================
    public static class Builder {
        private String recipient;
        private String subject;
        private String body;
        private Priority priority = Priority.LOW; // Default fallback value
        private final List<String> attachments = new ArrayList<>();

        // Sets recipient and returns 'this' instance for chaining
        public Builder to(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder priority(Priority priority) {
            if (priority != null) {
                this.priority = priority;
            }
            return this;
        }

        // Adds single item incrementally to the attachment list
        public Builder attach(String attachment) {
            if (attachment != null && !attachment.isBlank()) {
                this.attachments.add(attachment);
            }
            return this;
        }

        // 6. The Final Assembly & Validation Step
        public NotificationMessage build() {
            // Validation Rule: Throw IllegalStateException if critical data is missing
            if (this.recipient == null || this.recipient.isBlank()) {
                throw new IllegalStateException("Validation Failure: Recipient ('to') field cannot be empty!");
            }
            if (this.body == null || this.body.isBlank()) {
                throw new IllegalStateException("Validation Failure: Message body field cannot be empty!");
            }

            // Returns a securely built product instance
            return new NotificationMessage(this);
        }
    }
}