package com.example.anticloudastronomers2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WeatherAPI {

    static private String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";
    static private String CAMBRIDGE = "310042?";
    static private String RESOLUTION = "res=3hourly&";
    static private String KEY = "key=5434136e-72e2-4a52-9b67-463430a9a83a";

    private int temp;

    public void set(JSONObject jsonObject){
        JSONArray l = (JSONArray) jsonObject.getJSONObject("SiteRep").getJSONObject("DV").
                getJSONObject("Location").get("Period");
        System.out.println(l.get(0));
    }
    public void get(){
        String url_string = BASE_URL + CAMBRIDGE + RESOLUTION + KEY;
        URL url;
        HttpURLConnection con;
        JSONObject jsonObject;

        try {
            url = new URL(url_string);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(con);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonObject = new JSONObject(inputLine);
                set(jsonObject);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException err){
            err.printStackTrace();
        }
    }
}
