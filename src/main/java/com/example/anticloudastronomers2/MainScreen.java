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
        WeatherAPI w = new WeatherAPI();
        w.update();
        w.get(0,0,"T");
       // launch();
    }
}
