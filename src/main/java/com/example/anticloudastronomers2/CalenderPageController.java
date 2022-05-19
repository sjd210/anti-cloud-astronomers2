package com.example.anticloudastronomers2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class CalenderPageController implements Initializable {

    @FXML
    private GridPane Grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Ping");
        for(int i = 0; i< 2; i++){
            for(int j = 0; j < 4; j++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Calenderobj.fxml"));
                CalenderObjController c = new CalenderObjController(LocalDateTime.now().plusDays(2*j+i));
                loader.setController(c);
                try {
                    Grid.add(loader.load(), i, j);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}