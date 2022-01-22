package Validation;

public class ValidationResult {
    private ValidationResultStatus status;
    private String message;

    public ValidationResult(ValidationResultStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ValidationResultStatus getStatus() {
        return status;
    }

    public void setStatus(ValidationResultStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }
}
