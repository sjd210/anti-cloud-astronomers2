package com.example.anticloudastronomers2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {

    @FXML
    private BorderPane BorderPane;

    @FXML
    private Button Home;

    @FXML
    private Button Map;

    @FXML
    private Button Settings;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        System.out.println("Ping");
        AnchorPane view = FXMLLoader.load(getClass().getResource("Home.fxml"));
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnMap(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("Map.fxml"));
        BorderPane.setCenter(view);
    }

    @FXML
    private void btnSettings(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        BorderPane.setCenter(view);
    }
}