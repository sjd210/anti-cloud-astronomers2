package com.example.anticloudastronomers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class HomeController implements Initializable {

    @FXML
    private javafx.scene.layout.BorderPane BorderPane;

    @FXML
    private void ScrollRefresh(ActionEvent event) throws IOException { //never runs, not sure how to make scrolling a scroll pane do an action.
        //System.out.println(currentTime);
        if(System.currentTimeMillis() > currentTime + 60000){
            refresh();
        }

    }

    @FXML
    private void btnDay1(ActionEvent event) throws IOException {
        daysAhead = 0;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay2(ActionEvent event) throws IOException {
        daysAhead = 1;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay3(ActionEvent event) throws IOException {
        daysAhead = 2;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay4(ActionEvent event) throws IOException {
        daysAhead = 3;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay5(ActionEvent event) throws IOException {
        daysAhead = 4;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay6(ActionEvent event) throws IOException {
        daysAhead = 5;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private void btnDay7(ActionEvent event) throws IOException {
        daysAhead = 6;
        MainScroll.setHvalue(0);
        softRefresh();
    }

    @FXML
    private ScrollPane MainScroll;

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
    @FXML
    private Text CurrentDay;
    @FXML
    private Text DayRating;

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


    //these might not initialize
    private WeatherAPI2 Wapi;
    private AstroWeatherAPI Aapi;
    private CloudCoverAPI Capi;
    private int daysAhead;
    private Long currentTime;
    private Map<String, String> moonPhases;



    private int find_hour_index(){ //finding the current 3-hour-index at the moment for the front page weather data
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.HOUR_OF_DAY);

    }

    private String find_day(int daysAhead){ //finding the current day of the week based off of the days ahead variable
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        List<String> dayArray = new ArrayList<>();
        dayArray.add("SUNDAY");
        dayArray.add("MONDAY");
        dayArray.add("TUESDAY");
        dayArray.add("WEDNESDAY");
        dayArray.add("THURSDAY");
        dayArray.add("FRIDAY");
        dayArray.add("SATURDAY");
        day += daysAhead;
        return dayArray.get((day - 1) % 7);

    }


    private String returnIconFromWeathertype(String weathertype, int currentHour){
        int sunset = Integer.parseInt(Aapi.get(daysAhead,"sunset").substring(0,2)) + 12;
        int sunrise = Integer.parseInt(Aapi.get(daysAhead,"sunrise").substring(0,2));
        String s;
        if (currentHour > sunset | currentHour < sunrise){
            s = "src/main/resources/com/example/icons/moon.png";
        }else{
            s = "src/main/resources/com/example/WeatherIcons/" + weathertype;
        }
        return s;
    }

    private String returnIconFromMoonphase(String phase){
        return "src/main/resources/com/example/MoonPhases/"+moonPhases.get(phase);
    }

    private String findTemperature(int CurrentHourIndex){
        String temp;
        if (SettingsStore.temperatureType == "Fahrenheit"){
            temp = Wapi.get(daysAhead,CurrentHourIndex,"temp_f");
            temp = temp + "°F";
        }else{
            temp = Wapi.get(daysAhead,CurrentHourIndex,"temp_c");
            temp = temp + "°C";
        }
        return temp;

    }

    private void updateTemperature(int CurrentHourIndex){
        Temperature.setText(findTemperature(CurrentHourIndex));
    }
    private void updateSunset(){
        Sunset.setText(Aapi.get(daysAhead,"sunset"));
    }

    private void updateWeatherIcon(int CurrentHourIndex) throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,CurrentHourIndex);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype, CurrentHourIndex)));
        WeatherIcon.setImage(image);
    }

    private void updateCloudCoverage(int CurrentHourIndex){
        CloudCover.setText(Wapi.get(daysAhead,CurrentHourIndex,"cloud") + "%");
    }

    private void updateDay(){
        CurrentDay.setText(find_day(daysAhead));
    }

    private void updateDayRating(){
        int sunset = Integer.parseInt(Aapi.get(daysAhead,"sunset").substring(0,2)) + 12;
        int sunrise = Integer.parseInt(Aapi.get(daysAhead+1,"sunrise").substring(0,2));
        List<Integer> nightHours = new ArrayList<>();
        List<Integer> dawnHours = new ArrayList<>();
        for(int i = 12; i < 24; i++){
            if(i >= sunset){
                nightHours.add(i);
            }
        }
        for(int i = 0; i < 12; i++){
            if(i <= sunrise){
                dawnHours.add(i);
            }
        }
        float avgCloudcover = 0;
        for(int i :nightHours){
            avgCloudcover += Integer.parseInt(Wapi.get(daysAhead,i,"cloud"));
        }
        for(int i : dawnHours){
            avgCloudcover += Integer.parseInt(Wapi.get(daysAhead+1,i,"cloud"));
        }
        avgCloudcover = avgCloudcover/((nightHours.size()+dawnHours.size())*100);

        float avgRainchance = 0;
        for(int i : nightHours){
            avgRainchance += Integer.parseInt(Wapi.get(daysAhead,i,"chance_of_rain"));
        }
        for(int i : dawnHours){
            avgRainchance += Integer.parseInt(Wapi.get(daysAhead+1,i,"chance_of_rain"));
        }
        avgRainchance = avgRainchance/((nightHours.size()+dawnHours.size())*100);

        float rating = 3 - 2*avgCloudcover - avgRainchance;
        if(rating < 1.5){
            DayRating.setText("BAD to stargaze");
        }else if(rating < 1.9) {
            DayRating.setText("AVERAGE to stargaze");
        }else if(rating < 2.5) {
            DayRating.setText("GOOD to stargaze");
        }else{
            DayRating.setText("GREAT to stargaze");
        }

    }

    private void updateMoonPhase() throws FileNotFoundException {
        String phase = Aapi.get(daysAhead,"moon_phase");
        Image image = new Image(new FileInputStream(returnIconFromMoonphase(phase)));
        MoonPhase.setImage(image);
    }
    // updating the 3-hourly-view page.
    private void update_0000() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,1);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,1)));
        sunIcon_0000.setImage(image);
        CloudCoverage_0000.setText(Wapi.get(daysAhead,1,"cloud") + "%");
        temperature_0000.setText(findTemperature(1));
        Precipitation_0000.setText(Wapi.get(daysAhead,1,"chance_of_rain")+ "%");
    }

    private void update_0300() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,4);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,4)));
        sunIcon_0300.setImage(image);
        CloudCoverage_0300.setText(Wapi.get(daysAhead,4,"cloud") + "%");
        temperature_0300.setText(findTemperature(4));
        Precipitation_0300.setText(Wapi.get(daysAhead,4,"chance_of_rain")+ "%");
    }

    private void update_0600() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,7);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,7)));
        sunIcon_0600.setImage(image);
        CloudCoverage_0600.setText(Wapi.get(daysAhead,7,"cloud") + "%");
        temperature_0600.setText(findTemperature(7));
        Precipitation_0600.setText(Wapi.get(daysAhead,7,"chance_of_rain")+ "%");
    }

    private void update_0900() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,10);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,10)));
        sunIcon_0900.setImage(image);
        CloudCoverage_0900.setText(Wapi.get(daysAhead,10,"cloud") + "%");
        temperature_0900.setText(findTemperature(10));
        Precipitation_0900.setText(Wapi.get(daysAhead,10,"chance_of_rain")+ "%");
    }

    private void update_1200() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,13);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,13)));
        sunIcon_1200.setImage(image);
        CloudCoverage_1200.setText(Wapi.get(daysAhead,13,"cloud") + "%");
        temperature_1200.setText(findTemperature(13));
        Precipitation_1200.setText(Wapi.get(daysAhead,13,"chance_of_rain")+ "%");
    }

    private void update_1500() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,16);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,16)));
        sunIcon_1500.setImage(image);
        CloudCoverage_1500.setText(Wapi.get(daysAhead,16,"cloud") + "%");
        temperature_1500.setText(findTemperature(16));
        Precipitation_1500.setText(Wapi.get(daysAhead,16,"chance_of_rain")+ "%");
    }

    private void update_1800() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,19);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,19)));
        sunIcon_1800.setImage(image);
        CloudCoverage_1800.setText(Wapi.get(daysAhead,19,"cloud") + "%");
        temperature_1800.setText(findTemperature(19));
        Precipitation_1800.setText(Wapi.get(daysAhead,19,"chance_of_rain")+ "%");
    }

    private void update_2100() throws FileNotFoundException {
        String weathertype = Wapi.getWeathertype(daysAhead,22);
        Image image = new Image(new FileInputStream(returnIconFromWeathertype(weathertype,22)));
        sunIcon_2100.setImage(image);
        CloudCoverage_2100.setText(Wapi.get(daysAhead,22,"cloud") + "%");
        temperature_2100.setText(findTemperature(22));
        Precipitation_2100.setText(Wapi.get(daysAhead,22,"chance_of_rain") + "%");
    }

    private void updateBarWeatherIcons() throws FileNotFoundException {
        String weathertype1 = Wapi.getWeathertype(0,12);
        String weathertype2 = Wapi.getWeathertype(1,12);
        String weathertype3 = Wapi.getWeathertype(2,12);
        String weathertype4 = Wapi.getWeathertype(3,12);
        String weathertype5 = Wapi.getWeathertype(4,12);
        String weathertype6 = Wapi.getWeathertype(5,12);
        String weathertype7 = Wapi.getWeathertype(6,12);
        Image image1 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype1,12)));
        Image image2 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype2,12)));
        Image image3 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype3,12)));
        Image image4 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype4,12)));
        Image image5 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype5,12)));
        Image image6 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype6,12)));
        Image image7 = new Image(new FileInputStream(returnIconFromWeathertype(weathertype7,12)));
        WeatherIcon1.setImage(image1);
        WeatherIcon2.setImage(image2);
        WeatherIcon3.setImage(image3);
        WeatherIcon4.setImage(image4);
        WeatherIcon5.setImage(image5);
        WeatherIcon6.setImage(image6);
        WeatherIcon7.setImage(image7);
    }

    private void updateBarDayLabels(){
        Day1Button.setText(find_day(0));
        Day2Button.setText(find_day(1));
        Day3Button.setText(find_day(2));
        Day4Button.setText(find_day(3));
        Day5Button.setText(find_day(4));
        Day6Button.setText(find_day(5));
        Day7Button.setText(find_day(6));

    }



    private void refresh() throws FileNotFoundException {
        Wapi.update();
        Aapi.update();
        Capi.update();
        int currentHourIndex = find_hour_index();
        currentTime = System.currentTimeMillis();

        update_0000(); //hourly cloud coverages are daily due to how the api works.
        update_0300();
        update_0600();
        update_0900();
        update_1200();
        update_1500();
        update_1800();
        update_2100();

        updateBarDayLabels();
        updateBarWeatherIcons();
        updateDay();
        updateDayRating();
        updateSunset();
        updateCloudCoverage(currentHourIndex);
        updateMoonPhase();
        updateTemperature(currentHourIndex);
        updateWeatherIcon(currentHourIndex);
    }

    private void softRefresh() throws FileNotFoundException {
        int currentHourIndex = find_hour_index();
        currentTime = System.currentTimeMillis();

        update_0000(); //hourly cloud coverages are daily due to how the api works.
        update_0300();
        update_0600();
        update_0900();
        update_1200();
        update_1500();
        update_1800();
        update_2100();

        updateBarDayLabels();
        updateBarWeatherIcons();
        updateDay();
        updateDayRating();
        updateSunset();
        updateCloudCoverage(currentHourIndex);
        updateMoonPhase();
        updateTemperature(currentHourIndex);
        updateWeatherIcon(currentHourIndex);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Wapi = new WeatherAPI2();
        Aapi = new AstroWeatherAPI();
        Capi = new CloudCoverAPI();
        moonPhases = new HashMap<>() {{
            put("New Moon", "NewMoon.png");put("Waxing Crescent", "WaxingCrescent.png");put("First Quarter", "FirstQuater.png");
            put("Waxing Gibbous", "WaxingGibbous.png");put("Full Moon", "FullMoon.png");put("Waning Gibbous", "WaningGibbous.png");
            put("Third Quarter", "LastQuater.png");put("Waning Crescent", "WaningCrescent.png");
        }};
        daysAhead = 0;
        currentTime = 0L;
        try {
            refresh();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}