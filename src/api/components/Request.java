package api.components;

import api.Credentials;
import api.components.RequestError;
import org.json.JSONException;
import org.json.JSONObject;

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

    protected String response;

    protected RequestError error;

    /**
     * Constructor
     *
     * @param method Method name
     * @param params GET-parameters as HashMap
     */
    public Request(String method, HashMap<String, String> params) {
        this.method = method;
        this.params = params;
    }

    /**
     * Constructor
     *
     * @param method Method name
     * @param params GET-parameters as HashMap
     * @param key Custom access token
     */
    public Request(String method, HashMap<String, String> params, String key) {
        this(method, params);
        this.token = key;
    }

    /**
     * Builds required url depends method and GET-params of class properties
     * @return Absolute url
     */
    protected String makeUrl() {

        String result = "https://api.vk.com/method/" + this.method + "?";

        Iterator it = this.params.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry item = (Map.Entry) it.next();
            result += item.getKey() + "=" + item.getValue() + "&";
        }

        result += "access_token=" + this.token;

        return result;

    }

    /**
     * Call API method and save response to property
     * @throws IOException Exception
     */
    public void makeRequest() throws IOException {

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

        try {
            JSONObject json = new JSONObject(response.toString()).getJSONObject("error");

            this.error = new RequestError(
                json.getInt("error_code"),
                json.getString("error_msg")
            );

        } catch (JSONException e) {
            this.response = response.toString();
        }

    }

    public String getResponse() {
        return this.response;
    }

    public RequestError getError() {
        return error;
    }

    public boolean hasError() {
        return this.error != null;
    }
}
