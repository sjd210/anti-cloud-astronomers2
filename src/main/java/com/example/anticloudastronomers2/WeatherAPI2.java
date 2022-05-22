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

public class WeatherAPI2 {

    static private String BASE_URL = "https://api.weatherapi.com/v1/forecast.json?";
    static private String CAMBRIDGE = "q=52.1951,0.1313";
    static private String KEY = "key=40631e1acccb42ac83a144050221805&";
    static private int FORECASTDAYS = 8;

    private ArrayList<JSONArray> jsonArray;

    WeatherAPI2(){
        jsonArray = new ArrayList<JSONArray>(FORECASTDAYS);

        // Bit inefficient here? - can it be done better
        for (int i = 0; i < FORECASTDAYS; i++){
            jsonArray.add(null);
        }
    }

    public String get(int daysAhead, int hour, String dataType){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }

        /*
                time_epoch	    int	Time as epoch
                time	        string	Date and time
                temp_c	        decimal	Temperature in celsius
                temp_f	        decimal	Temperature in fahrenheit
                condition:text	string	Weather condition text
                condition:icon	string	Weather condition icon
                condition:code	int	Weather condition code
                wind_mph	    decimal	Maximum wind speed in miles per hour
                wind_kph	    decimal	Maximum wind speed in kilometer per hour
                wind_degree 	int	Wind direction in degrees
                wind_dir	    string	Wind direction as 16 point compass. e.g.: NSW
                pressure_mb 	decimal	Pressure in millibars
                pressure_in 	decimal	Pressure in inches
                precip_mm	    decimal	Precipitation amount in millimeters
                precip_in	    decimal	Precipitation amount in inches
                humidity	    int	Humidity as percentage
                cloud	        int	Cloud cover as percentage
                feelslike_c 	decimal	Feels like temperature as celcius
                feelslike_f	    decimal	Feels like temperature as fahrenheit
                windchill_c	    decimal	Windchill temperature in celcius
                windchill_f	    decimal	Windchill temperature in fahrenheit
                heatindex_c 	decimal	Heat index in celcius
                heatindex_f 	decimal	Heat index in fahrenheit
                dewpoint_c	    decimal	Dew point in celcius
                dewpoint_f	    decimal	Dew point in fahrenheit
                will_it_rain	int	1 = Yes 0 = No
                                Will it will rain or not
                will_it_snow	int	1 = Yes 0 = No
                                Will it snow or not
                is_day	int	1 = Yes 0 = No
                                Whether to show day condition icon or night icon
                vis_km	        decimal	Visibility in kilometer
                vis_miles	    decimal	Visibility in miles
                chance_of_rain	int	Chance of rain as percentage
                chance_of_snow	int	Chance of snow as percentage
                gust_mph	    decimal	Wind gust in miles per hour
                gust_kph	    decimal	Wind gust in kilometer per hour */

        String s = jsonArray.get(daysAhead).getJSONObject(hour).get(dataType).toString();
        return s;
    }

    public String getWeathertype(int daysAhead, int hour){
        if (jsonArray == null){
            System.out.printf("Have not yet connected to the API");
            throw new NotYetConnectedException();
        }

        /*
                takes daysahead and hour and returns the weather icon code */

        JSONObject s = (JSONObject) jsonArray.get(daysAhead).getJSONObject(hour).get("condition");
        String temp = s.getString("icon");
        String icon = temp.substring(temp.length() - 7);
        return icon;
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
        //   System.out.println(jsonObject_new.getJSONObject("forecast").
        //        getJSONArray("forecastday").getJSONObject(0)
        //        );
        jsonArray.set(daysAhead, jsonObject_new.getJSONObject("forecast").
                getJSONArray("forecastday").getJSONObject(0).
                getJSONArray("hour"));
    }
}
