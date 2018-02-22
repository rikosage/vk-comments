package api;

public enum API {

    BASE_URL("https://api.vk.com/method/"),
    METHOD__GROUP_GET_BY_ID("groups.getById");

    private String method;

    API(String method) {
        this.method = method;
    }

    public String get() {
        return this.method;
    }
}
