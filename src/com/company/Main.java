package com.company;

import api.API;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] arguments) throws IOException {

        HashMap<String, String> hash = new HashMap<String, String>();

        hash.put("group_id", "tnull");

        String response = (new Request(API.METHOD__GROUP_GET_BY_ID.get(), hash)).make();

        JSONObject json = (new JSONObject(response))
                .getJSONArray("response")
                .getJSONObject(0);

        int gid = json.getInt("gid");

        System.out.println(gid);

    }

}