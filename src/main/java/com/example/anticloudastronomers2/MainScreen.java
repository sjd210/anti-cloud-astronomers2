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
        CloudCoverAPI c = new CloudCoverAPI();
        c.update();
        System.out.println(c.get(2,"cloudcover"));

        WeatherAPI w = new WeatherAPI();
        w.update();
        w.get(0,0,"T");
       // launch();

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
    }
}
