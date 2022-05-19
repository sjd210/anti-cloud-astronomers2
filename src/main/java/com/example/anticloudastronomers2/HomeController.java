package com.example.anticloudastronomers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeController {

    @FXML
    private javafx.scene.layout.BorderPane BorderPane;
    @FXML
    private Text CurrentDay;

    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        System.out.println("Ping");
        Pane view = FXMLLoader.load(getClass().getResource("Home.fxml"));
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnMap(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("Map.fxml"));
        BorderPane.setCenter(null);
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnSettings(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnCalender(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("CalenderPage.fxml"));
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnDay1(ActionEvent event) throws IOException {
        System.out.println("Day 1!");
        daysAhead = 0;
    }

    @FXML
    private void btnDay2(ActionEvent event) throws IOException {
        System.out.println("Day 2");
        daysAhead = 1;
    }

    @FXML
    private void btnDay3(ActionEvent event) throws IOException {
        System.out.println("Day 3");
        daysAhead = 2;
    }

    @FXML
    private void btnDay4(ActionEvent event) throws IOException {
        System.out.println("Day 4");
        daysAhead = 3;
    }

    @FXML
    private void btnDay5(ActionEvent event) throws IOException {
        System.out.println("Day 5");
        daysAhead = 4;
    }

    @FXML
    private void btnDay6(ActionEvent event) throws IOException {
        System.out.println("Day 6");
        daysAhead = 5;
    }

    @FXML
    private void btnDay7(ActionEvent event) throws IOException {
        System.out.println("Day 7");
        daysAhead = 6;
    }

    @FXML
    private Button Day1Button;
    @FXML
    private Button Day2Button;
    @FXML
    private Button Day3Button;
    @FXML
    private Button Day4Button;
    @FXML
    private Button Day5Button;
    @FXML
    private Button Day6Button;
    @FXML
    private Button Day7Button;

    @FXML
    private Text Temperature;
    @FXML
    private Text CloudCover;
    @FXML
    private Text Sunset;
    @FXML
    private ImageView MoonPhase;
    @FXML
    private ImageView WeatherIcon;

    // daily sun / cloud / rain icons
    @FXML
    private ImageView WeatherIcon1;
    @FXML
    private ImageView WeatherIcon2;
    @FXML
    private ImageView WeatherIcon3;
    @FXML
    private ImageView WeatherIcon4;
    @FXML
    private ImageView WeatherIcon5;
    @FXML
    private ImageView WeatherIcon6;
    @FXML
    private ImageView WeatherIcon7;

    //hourly weather view Texts:
    // sun / cloud / sunset icons:
    @FXML
    private ImageView sunIcon_0000;
    @FXML
    private ImageView sunIcon_0300;
    @FXML
    private ImageView sunIcon_0600;
    @FXML
    private ImageView sunIcon_0900;
    @FXML
    private ImageView sunIcon_1200;
    @FXML
    private ImageView sunIcon_1500;
    @FXML
    private ImageView sunIcon_1800;
    @FXML
    private ImageView sunIcon_2100;
    //temperature hourly
    @FXML
    private Text temperature_0000;
    @FXML
    private Text temperature_0300;
    @FXML
    private Text temperature_0600;
    @FXML
    private Text temperature_0900;
    @FXML
    private Text temperature_1200;
    @FXML
    private Text temperature_1500;
    @FXML
    private Text temperature_1800;
    @FXML
    private Text temperature_2100;
    //cloud coverage %
    @FXML
    private Text CloudCoverage_0000;
    @FXML
    private Text CloudCoverage_0300;
    @FXML
    private Text CloudCoverage_0600;
    @FXML
    private Text CloudCoverage_0900;
    @FXML
    private Text CloudCoverage_1200;
    @FXML
    private Text CloudCoverage_1500;
    @FXML
    private Text CloudCoverage_1800;
    @FXML
    private Text CloudCoverage_2100;
    //precipitation
    @FXML
    private Text Precipitation_0000;
    @FXML
    private Text Precipitation_0300;
    @FXML
    private Text Precipitation_0600;
    @FXML
    private Text Precipitation_0900;
    @FXML
    private Text Precipitation_1200;
    @FXML
    private Text Precipitation_1500;
    @FXML
    private Text Precipitation_1800;
    @FXML
    private Text Precipitation_2100;

    private WeatherAPI Wapi = new WeatherAPI();
    private AstroWeatherAPI Aapi = new AstroWeatherAPI();
    int daysAhead = 0;


    private int find_hour_index(){ //finding the current 3-hour-index at the moment for the front page weather data
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int hour_index = Math.floorMod(hour,3);
        return hour_index;
    }

    private String find_day(){ //finding the current day of the week based off of the days ahead variable
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        List<String> dayArray = new ArrayList<>();
        dayArray.add("Sunday");
        dayArray.add("Monday");
        dayArray.add("Tuesday");
        dayArray.add("Wednesday");
        dayArray.add("Thursday");
        dayArray.add("Friday");
        dayArray.add("Saturday");
        day += daysAhead;
        return dayArray.get(day);

    }

    private void updateTemperature(int CurrentHourIndex){
        Temperature.setText(Wapi.get(daysAhead,CurrentHourIndex,"T"));
    }
    private void updateSunset(){
        Sunset.setText(Aapi.get(daysAhead,"sunset"));
    }

    private void updateWeatherIcon(int CurrentHourIndex){
        String weathertype = Wapi.get(daysAhead,CurrentHourIndex, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        WeatherIcon.setImage(image);
    }

    private void updateCloudCoverage(int CurrentHourIndex){

    }

    private void updateDay(){
        CurrentDay.setText(find_day());
    }

    private void updateMoonPhase(){
        String phase = Aapi.get(daysAhead,"moon_phase");
        Image image = new Image(returnIconFromMoonphase(phase));
        MoonPhase.setImage(image);
    }
    // updating the 3-hourly-view page.
    private void update_0000(){
        String weathertype = Wapi.get(daysAhead,0, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_0000.setImage(image);
        //cloudcover
        temperature_0000.setText(Wapi.get(daysAhead,0,"T"));
        Precipitation_0000.setText(Wapi.get(daysAhead,0,"Pp"));
    }

    private void update_0300(){
        String weathertype = Wapi.get(daysAhead,1, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_0300.setImage(image);
        //cloudcover
        temperature_0300.setText(Wapi.get(daysAhead,1,"T"));
        Precipitation_0300.setText(Wapi.get(daysAhead,1,"Pp"));
    }

    private void update_0600(){
        String weathertype = Wapi.get(daysAhead,2, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_0600.setImage(image);
        //cloudcover
        temperature_0600.setText(Wapi.get(daysAhead,2,"T"));
        Precipitation_0600.setText(Wapi.get(daysAhead,2,"Pp"));
    }

    private void update_0900(){
        String weathertype = Wapi.get(daysAhead,3, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_0900.setImage(image);
        //cloudcover
        temperature_0900.setText(Wapi.get(daysAhead,3,"T"));
        Precipitation_0900.setText(Wapi.get(daysAhead,3,"Pp"));
    }

    private void update_1200(){
        String weathertype = Wapi.get(daysAhead,4, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_1200.setImage(image);
        //cloudcover
        temperature_1200.setText(Wapi.get(daysAhead,4,"T"));
        Precipitation_1200.setText(Wapi.get(daysAhead,4,"Pp"));
    }

    private void update_1500(){
        String weathertype = Wapi.get(daysAhead,5, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_1500.setImage(image);
        //cloudcover
        temperature_1500.setText(Wapi.get(daysAhead,5,"T"));
        Precipitation_1500.setText(Wapi.get(daysAhead,5,"Pp"));
    }

    private void update_1800(){
        String weathertype = Wapi.get(daysAhead,6, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_1800.setImage(image);
        //cloudcover
        temperature_1800.setText(Wapi.get(daysAhead,6,"T"));
        Precipitation_1800.setText(Wapi.get(daysAhead,6,"Pp"));
    }

    private void update_2100(){
        String weathertype = Wapi.get(daysAhead,7, "W");
        Image image = new Image(returnIconFromWeathertype(weathertype));
        sunIcon_2100.setImage(image);
        //cloudcover
        temperature_2100.setText(Wapi.get(daysAhead,7,"T"));
        Precipitation_2100.setText(Wapi.get(daysAhead,7,"Pp"));
    }

    private void updateBarWeatherIcons(){
        String weathertype1 = Wapi.get(0,4,"W");
        String weathertype2 = Wapi.get(1,4,"W");
        String weathertype3 = Wapi.get(2,4,"W");
        String weathertype4 = Wapi.get(3,4,"W");
        String weathertype5 = Wapi.get(4,4,"W");
        String weathertype6 = Wapi.get(5,4,"W");
        String weathertype7 = Wapi.get(6,4,"W");
        Image image1 = new Image(returnIconFromWeathertype(weathertype1));
        Image image2 = new Image(returnIconFromWeathertype(weathertype2));
        Image image3 = new Image(returnIconFromWeathertype(weathertype3));
        Image image4 = new Image(returnIconFromWeathertype(weathertype4));
        Image image5 = new Image(returnIconFromWeathertype(weathertype5));
        Image image6 = new Image(returnIconFromWeathertype(weathertype6));
        Image image7 = new Image(returnIconFromWeathertype(weathertype7));
        WeatherIcon1.setImage(image1);
        WeatherIcon2.setImage(image2);
        WeatherIcon3.setImage(image3);
        WeatherIcon4.setImage(image4);
        WeatherIcon5.setImage(image5);
        WeatherIcon6.setImage(image6);
        WeatherIcon7.setImage(image7);
    }

    private String returnIconFromWeathertype(String weathertype){
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int sunset = Integer.parseInt(Aapi.get(0,"sunset").substring(0,2));
        if(hour > sunset){
            return "moon.png";
        }
        else if(weathertype == "sun"){
             return "sun.png";
        }else if(weathertype == "cloud"){
            return "cloud.png";
        }else if(weathertype == "sun with cloud"){
            return "sun_clouds.png";
        }else if(weathertype == "raining"){
            return "cloud_rain.png";
        }else{
            System.out.println("invalid weathertype parsed to returnIconFromWeathertype function");
            return null;
        }
    }
    
    private String returnIconFromMoonphase(String phase){
        if(phase == "new"){
            return "new_moon.png";
        }else if (phase == "crescent"){
            return "crescent_moon.png";
        }else if (phase == "half"){
            return "half_moon.png";
        }else if (phase == "gibbous"){
            return "gibbous_moon.png";
        }else{
            return "full_moon.png";
        }
    }


    private void refresh(){
        Wapi.update();
        Aapi.update();
        int currentHourIndex = find_hour_index();

        update_0000();
        update_0300();
        update_0600();
        update_0900();
        update_1200();
        update_1500();
        update_1800();
        update_2100();

        updateBarWeatherIcons();
        updateDay();
        updateSunset();
        //updateCloudCoverage(currentHourIndex);
        //updateMoonPhase();
        updateTemperature(currentHourIndex);
        updateWeatherIcon(currentHourIndex);
    }
}