package com.example.anticloudastronomers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

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
        CurrentDay.setText("Day 1");
    }

    @FXML
    private void btnDay2(ActionEvent event) throws IOException {
        System.out.println("Day 2");
        CurrentDay.setText("Day 2");
    }

    @FXML
    private void btnDay3(ActionEvent event) throws IOException {
        System.out.println("Day 3");
        CurrentDay.setText("Day 3");
    }

    @FXML
    private void btnDay4(ActionEvent event) throws IOException {
        System.out.println("Day 4");
        CurrentDay.setText("Day 4");
    }

    @FXML
    private void btnDay5(ActionEvent event) throws IOException {
        System.out.println("Day 5");
        CurrentDay.setText("Day 5");
    }

    @FXML
    private void btnDay6(ActionEvent event) throws IOException {
        System.out.println("Day 6");
        CurrentDay.setText("Day 6");
    }

    @FXML
    private void btnDay7(ActionEvent event) throws IOException {
        System.out.println("Day 7");
        CurrentDay.setText("Day 7");
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
    private ImageView sunIcon_0100;
    @FXML
    private ImageView sunIcon_0200;
    @FXML
    private ImageView sunIcon_0300;
    @FXML
    private ImageView sunIcon_0400;
    @FXML
    private ImageView sunIcon_0500;
    @FXML
    private ImageView sunIcon_0600;
    @FXML
    private ImageView sunIcon_0700;
    @FXML
    private ImageView sunIcon_0800;
    @FXML
    private ImageView sunIcon_0900;
    @FXML
    private ImageView sunIcon_1000;
    @FXML
    private ImageView sunIcon_1100;
    @FXML
    private ImageView sunIcon_1200;
    @FXML
    private ImageView sunIcon_1300;
    @FXML
    private ImageView sunIcon_1400;
    @FXML
    private ImageView sunIcon_1500;
    @FXML
    private ImageView sunIcon_1600;
    @FXML
    private ImageView sunIcon_1700;
    @FXML
    private ImageView sunIcon_1800;
    @FXML
    private ImageView sunIcon_1900;
    @FXML
    private ImageView sunIcon_2000;
    @FXML
    private ImageView sunIcon_2100;
    @FXML
    private ImageView sunIcon_2200;
    @FXML
    private ImageView sunIcon_2300;
    //temperature hourly
    @FXML
    private Text temperature_0000;
    @FXML
    private Text temperature_0100;
    @FXML
    private Text temperature_0200;
    @FXML
    private Text temperature_0300;
    @FXML
    private Text temperature_0400;
    @FXML
    private Text temperature_0500;
    @FXML
    private Text temperature_0600;
    @FXML
    private Text temperature_0700;
    @FXML
    private Text temperature_0800;
    @FXML
    private Text temperature_0900;
    @FXML
    private Text temperature_1000;
    @FXML
    private Text temperature_1100;
    @FXML
    private Text temperature_1200;
    @FXML
    private Text temperature_1300;
    @FXML
    private Text temperature_1400;
    @FXML
    private Text temperature_1500;
    @FXML
    private Text temperature_1600;
    @FXML
    private Text temperature_1700;
    @FXML
    private Text temperature_1800;
    @FXML
    private Text temperature_1900;
    @FXML
    private Text temperature_2000;
    @FXML
    private Text temperature_2100;
    @FXML
    private Text temperature_2200;
    @FXML
    private Text temperature_2300;
    //cloud coverage %
    @FXML
    private Text CloudCoverage_0000;
    @FXML
    private Text CloudCoverage_0100;
    @FXML
    private Text CloudCoverage_0200;
    @FXML
    private Text CloudCoverage_0300;
    @FXML
    private Text CloudCoverage_0400;
    @FXML
    private Text CloudCoverage_0500;
    @FXML
    private Text CloudCoverage_0600;
    @FXML
    private Text CloudCoverage_0700;
    @FXML
    private Text CloudCoverage_0800;
    @FXML
    private Text CloudCoverage_0900;
    @FXML
    private Text CloudCoverage_1000;
    @FXML
    private Text CloudCoverage_1100;
    @FXML
    private Text CloudCoverage_1200;
    @FXML
    private Text CloudCoverage_1300;
    @FXML
    private Text CloudCoverage_1400;
    @FXML
    private Text CloudCoverage_1500;
    @FXML
    private Text CloudCoverage_1600;
    @FXML
    private Text CloudCoverage_1700;
    @FXML
    private Text CloudCoverage_1800;
    @FXML
    private Text CloudCoverage_1900;
    @FXML
    private Text CloudCoverage_2000;
    @FXML
    private Text CloudCoverage_2100;
    @FXML
    private Text CloudCoverage_2200;
    @FXML
    private Text CloudCoverage_2300;
    //precipitation
    @FXML
    private Text Precipitation_0000;
    @FXML
    private Text Precipitation_0100;
    @FXML
    private Text Precipitation_0200;
    @FXML
    private Text Precipitation_0300;
    @FXML
    private Text Precipitation_0400;
    @FXML
    private Text Precipitation_0500;
    @FXML
    private Text Precipitation_0600;
    @FXML
    private Text Precipitation_0700;
    @FXML
    private Text Precipitation_0800;
    @FXML
    private Text Precipitation_0900;
    @FXML
    private Text Precipitation_1000;
    @FXML
    private Text Precipitation_1100;
    @FXML
    private Text Precipitation_1200;
    @FXML
    private Text Precipitation_1300;
    @FXML
    private Text Precipitation_1400;
    @FXML
    private Text Precipitation_1500;
    @FXML
    private Text Precipitation_1600;
    @FXML
    private Text Precipitation_1700;
    @FXML
    private Text Precipitation_1800;
    @FXML
    private Text Precipitation_1900;
    @FXML
    private Text Precipitation_2000;
    @FXML
    private Text Precipitation_2100;
    @FXML
    private Text Precipitation_2200;
    @FXML
    private Text Precipitation_2300;

}