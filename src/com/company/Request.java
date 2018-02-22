package com.company;

import api.API;
import api.Credentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Request {

    private String method;
    private HashMap<String, String> params;
    private String token = Credentials.SERVICE_KEY.get();

    /**
     * Constructor
     * @param method Method name
     * @param params GET-parameters as HashMap
     */
    public Request(String method, HashMap<String, String> params) {
        this.method = method;
        this.params = params;
    }

    /**
     *
     * @param method Method name
     * @param params GET-parameters as HashMap
     * @param key Custom access token
     */
    public Request(String method, HashMap<String, String> params, String key) {
        this(method, params);
        this.token = key;
    }

    protected String makeUrl() {

        String result = API.BASE_URL.get() + this.method;

        Iterator it = this.params.entrySet().iterator();

        if (!this.params.isEmpty()) {
            result += "?";
        }

        while (it.hasNext()) {
            Map.Entry item = (Map.Entry) it.next();
            result += item.getKey() + "=" + item.getValue() + "&";
        }

        result += "access_token=" + this.token;

        System.out.println(result);

        return result;

    }

    public String make() throws IOException {

        URL urlObject = new URL(this.makeUrl());
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

}
