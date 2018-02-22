package api.request;

import api.components.Request;
import api.components.RequestError;

import java.util.HashMap;

abstract public class Entity {

    protected Request request;

    public void init(String method, HashMap params) {
        this.request = new Request(method, params);
    }

    public RequestError getError() {
        return this.request.getError();
    }

    public boolean hasError() {
        return this.request.hasError();
    }
}
