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

    public void get(int daysAhead, int threeHourIndex, String dataType){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }

        // daysAhead parameter is the number of days ahead starting from today as day zero
        // threeHourIndex parameter gets which third hour you want the data for
        // dataType parameter is the type of weather data requested

        // "G" units="mph"      Wind Gust
        // "T" units="C"        Temperature
        // "V" units="m"        Visibility
        // "D" units="compass"  Wind Direction
        // "S" units="mph"      Wind Speed
        // "W" units=""         Weather Type
        // "P" units="hpa"      Pressure
        // "Pt" units="Pa/s"    Pressure Tendency
        // "Dp" units="C"       Dew Point
        // "H" units="%"        Screen Relative Humidity
        // "Pp" units="%"       Precipitation probability

        //Param":[{"name":"F","units":"C","$":"Feels Like Temperature"},
        // {"name":"G","units":"mph","$":"Wind Gust"},
        // {"name":"H","units":"%","$":"Screen Relative Humidity"},
        // {"name":"T","units":"C","$":"Temperature"},
        // {"name":"V","units":"","$":"Visibility"},
        // {"name":"D","units":"compass","$":"Wind Direction"},
        // {"name":"S","units":"mph","$":"Wind Speed"},
        // {"name":"U","units":"","$":"Max UV Index"},
        // {"name":"W","units":"","$":"Weather Type"},
        // {"name":"Pp","units":"%","$":"Precipitation Probability"}]}

        JSONArray threeHourData = (JSONArray)jsonArray.getJSONObject(daysAhead).get("Rep");
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
       //     System.out.println(con);

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
