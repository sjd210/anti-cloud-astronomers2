package com.example.anticloudastronomers2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;

public class CloudCoverAPI {

    static private String BASE_URL = "https://www.7timer.info/bin/civil.php?lon=0.1313&lat=52.1951&ac=0&unit=metric&output=json&tzshift=0";
    static private int FORECASTDAYS = 7;

    private JSONArray jsonArray;

    public Integer get(int daysAhead, String dataType){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }

        // "cloudcover" - cloud cover
        // See the document for the meaning of the cloud cover number

        Integer s = (Integer)jsonArray.getJSONObject((int)(daysAhead*8)).get(dataType);
        return s;
    }

    public void update(){
        String url_string = BASE_URL;
        URL url;
        HttpURLConnection con;
        JSONObject jsonObject_new;

        try {
            url = new URL(url_string);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
         //   System.out.println(con);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            String compliedLine = "";
            while ((inputLine = in.readLine()) != null) {
                compliedLine = compliedLine + inputLine;
            }
            jsonObject_new = new JSONObject(compliedLine);
            set(jsonObject_new);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException err) {
            err.printStackTrace();
        }
    }

    private void set(JSONObject jsonObject_new){
        jsonArray = (JSONArray)jsonObject_new.get("dataseries");
    }
}
