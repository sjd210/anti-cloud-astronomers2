package com.example.anticloudastronomers2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {

    // Base file for all pages in app


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.
                getResource("MainScreen-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        WeatherAPI2 w2 = new WeatherAPI2();
        w2.update();
        System.out.println(w2.get(1,4,"temp_c"));
        System.out.println(w2.get(2,4,"temp_c"));
        System.out.println(w2.get(3,4,"temp_c"));
        System.out.println(w2.get(7,4,"temp_c"));
        System.out.println(w2.get(7,5,"temp_c"));
        System.out.println(w2.get(7,22,"temp_c"));
        System.out.println(w2.get(7,23,"temp_c"));

        CloudCoverAPI c = new CloudCoverAPI();
        c.update();
        System.out.println(c.get(6,"cloudcover"));

        WeatherAPI w = new WeatherAPI();
        w.update();
        System.out.println(w.get(4,0,"T"));

        AstroWeatherAPI aw = new AstroWeatherAPI();
        aw.update();
        System.out.println(aw.get(0,"moonrise"));
        System.out.println(aw.get(1,"moonrise"));
        System.out.println(aw.get(2,"moonrise"));
        System.out.println(aw.get(3,"moonrise"));
        System.out.println(aw.get(4,"moonrise"));

        System.out.println(aw.get(0,"moon_phase"));
        System.out.println(aw.get(1,"moon_phase"));
        System.out.println(aw.get(2,"moon_phase"));
        System.out.println(aw.get(3,"moon_phase"));
        System.out.println(aw.get(4,"moon_phase"));
        System.out.println(aw.get(5,"moon_phase"));
        System.out.println(aw.get(6,"moon_phase"));
    }
}
