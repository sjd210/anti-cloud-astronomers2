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

public class WeatherAPI {

    static private String BASE_URL = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/";
    static private String CAMBRIDGE = "310042?";
    static private String RESOLUTION = "res=3hourly&";
    static private String KEY = "key=5434136e-72e2-4a52-9b67-463430a9a83a";

    private JSONArray jsonArray;

    public void get(int days_ahead, int threeHourIndex, String dataType){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }
        JSONArray threeHourData = (JSONArray)jsonArray.getJSONObject(days_ahead).get("Rep");

        // {"Pp":"4","S":"11","D":"SSW","T":"22","$":"720","U":"6","F":"20","V":"VG","G":"13","W":"7","H":"57"}
        System.out.println(((JSONObject)threeHourData.get(threeHourIndex)).get(dataType));
    }

    public void update(){
        String url_string = BASE_URL + CAMBRIDGE + RESOLUTION + KEY;
        URL url;
        HttpURLConnection con;
        JSONObject jsonObject_new;

        try {
            url = new URL(url_string);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(con);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonObject_new = new JSONObject(inputLine);
                set(jsonObject_new);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException err){
            err.printStackTrace();
        }
    }

    private void set(JSONObject jsonObject_new){
        jsonArray = (JSONArray) jsonObject_new.getJSONObject("SiteRep").getJSONObject("DV").
                getJSONObject("Location").get("Period");
    }
}
