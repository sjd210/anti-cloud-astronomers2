package com.example.anticloudastronomers2;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class SettingsController implements Initializable {

    @FXML
    private ChoiceBox TemperatureChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TemperatureChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    SettingsStore.temperatureType = (TemperatureChoice.getItems().get((Integer) t1));
                }
            }
        );

        List<String> list = new ArrayList<String>(List.of("Celsius", "Fahrenheit"));
        TemperatureChoice.setItems(FXCollections.observableList(list));
        TemperatureChoice.setValue(SettingsStore.temperatureType);
        System.out.println("Settings ping");
    }
}
