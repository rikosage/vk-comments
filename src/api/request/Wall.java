package api.request;

import api.components.Request;

import java.io.IOException;
import java.util.HashMap;

public class Wall extends Request {

    public Wall(HashMap<String, String> params) throws IOException {
        super("wall.get", params);
        this.makeRequest();
    }



}
