package com.example.anticloudastronomers2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CalenderObjController implements Initializable {

    @FXML
    private Label DayLabel;
    private LocalDateTime myDate;

    public CalenderObjController(LocalDateTime d){
        myDate = d;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(myDate.getMonth());
        System.out.println(myDate.getDayOfMonth());
        DayLabel.setText(myDate.getMonth()+" "+myDate.getDayOfMonth());
    }
}
