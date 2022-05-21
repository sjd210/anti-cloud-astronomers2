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
import java.time.LocalDate;
import java.util.ArrayList;

public class AstroWeatherAPI {

    static private String BASE_URL = "https://api.weatherapi.com/v1/astronomy.json?";
    static private String CAMBRIDGE = "q=52.1951,0.1313";
    static private String KEY = "key=40631e1acccb42ac83a144050221805&";
    static private int FORECASTDAYS = 8;

    private ArrayList<JSONObject> jsonArray;

    AstroWeatherAPI(){
        jsonArray = new ArrayList<JSONObject>(FORECASTDAYS);

        // Bit inefficient here? - can it be done better
        for (int i = 0; i < FORECASTDAYS; i++){
            jsonArray.add(null);
        }
    }

    public String get(int daysAhead, String dataType){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }

        // {"sunrise":"05:01 AM","sunset":"08:52 PM","moonrise":"No moonrise","moonset":"06:18 AM",
        // "moon_phase":"Full Moon","moon_illumination":"83"}

        String s = (String)jsonArray.get(daysAhead).get(dataType);
        return s;
    }

    public void update(){
        LocalDate dateTime = LocalDate.now(); // Create a date object
        for (int i = 0; i < FORECASTDAYS; i++) {
            LocalDate dateTimeLoop = dateTime.plusDays(i);
            String dt = "&dt=" + dateTimeLoop.toString();
            String url_string = BASE_URL + KEY + CAMBRIDGE + dt;
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
                while ((inputLine = in.readLine()) != null) {
                    jsonObject_new = new JSONObject(inputLine);
                    set(jsonObject_new, i);
                }
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException err) {
                err.printStackTrace();
            }
        }
    }

    private void set(JSONObject jsonObject_new, int daysAhead){
        jsonArray.set(daysAhead, jsonObject_new.getJSONObject("astronomy").getJSONObject("astro"));
    }
}
