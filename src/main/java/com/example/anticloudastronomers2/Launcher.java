package com.example.anticloudastronomers2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("MainPane.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        stage.setMaxHeight(637);
        stage.setMaxWidth(350);
        stage.setTitle("Anti-Cloud Astronomy");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}