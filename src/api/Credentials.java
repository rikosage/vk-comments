package api;

public enum Credentials
{
    ID("6382032"),
    SERVICE_KEY("78bfbc3b78bfbc3b78bfbc3b6e78deddeb778bf78bfbc3b22325ea7eeae20f674b9500f"),
    PROTECTED_KEY("XOJn9YFB3dsKXhCdk1BT");

    private String data;

    Credentials(String data) {
        this.data = data;
    }

    public String get() {
        return this.data;
    }
}
