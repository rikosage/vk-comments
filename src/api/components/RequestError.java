package api.components;

public class RequestError {
    private int code;
    private String message;

    public RequestError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return String.format("API Error\nStatus: %s\nReason: %s", this.code, this.message);
    }
}
