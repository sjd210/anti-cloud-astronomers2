package com.example.anticloudastronomers2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CalenderObjController implements Initializable {

    @FXML
    private Label DayLabel;

    @FXML
    private ImageView MoonPhase;

    private LocalDateTime myDate;
    private AstroWeatherAPI myWeather;
    Map<String, String> moonPhases = new HashMap<String, String>() {{
        put("New Moon", "NewMoon.png");put("Waxing Crescent", "WaxingCrescent.png");put("First Quater", "FirstQuater.png");
        put("Waxing Gibbous", "WaxingGibbous.png");put("Full Moon", "FullMoon.png");put("Waning Gibbous", "WaningGibbous.png");
        put("Last Quater", "LastQuater.png");put("Waning Crescent", "WaningCrescent.png");


    }};


    public CalenderObjController(LocalDateTime d, AstroWeatherAPI w){
        myDate = d;
        myWeather = w;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DayLabel.setText(myDate.getMonth()+" "+myDate.getDayOfMonth());
        String w = myWeather.get((int) ChronoUnit.DAYS.between(LocalDate.now(), myDate.toLocalDate()),"moon_phase");
        System.out.println(w);
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/resources/com/example/MoonPhases/"+moonPhases.get(w)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MoonPhase.setImage(image);
    }
}
