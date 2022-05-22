package com.example.anticloudastronomers2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
import java.util.Random;
import java.util.ResourceBundle;

public class CalenderObjController implements Initializable {

    @FXML
    private Label DayLabel;

    @FXML
    private ImageView MoonPhase;

    @FXML
    private GridPane Grid;

    @FXML
    private ImageView Planet1;
    @FXML
    private ImageView Planet2;
    @FXML
    private ImageView Planet3;
    @FXML
    private ImageView Planet4;
    @FXML
    private ImageView Planet5;
    @FXML
    private ImageView Planet6;

    private LocalDateTime myDate;
    private AstroWeatherAPI myWeather;
    private Map<String, String> moonPhases = new HashMap<>() {{
        put("New Moon", "NewMoon.png");put("Waxing Crescent", "WaxingCrescent.png");put("First Quarter", "FirstQuater.png");
        put("Waxing Gibbous", "WaxingGibbous.png");put("Full Moon", "FullMoon.png");put("Waning Gibbous", "WaningGibbous.png");
        put("Third Quarter", "LastQuater.png");put("Waning Crescent", "WaningCrescent.png");
    }};
    private String[] planets = new String[]{"Mercury.png","Venus.png","Mars.png","Jupiter.png","Saturn.png", "Uranus.png"};



    public CalenderObjController(LocalDateTime d, AstroWeatherAPI w){
        myDate = d;
        myWeather = w;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DayLabel.setText(myDate.getMonth()+" "+myDate.getDayOfMonth());
        String w = myWeather.get((int) ChronoUnit.DAYS.between(LocalDate.now(), myDate.toLocalDate()),"moon_phase");
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/resources/com/example/MoonPhases/"+moonPhases.get(w)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MoonPhase.setImage(image);
        Random rd = new Random();
        int location = 0;
        Image[] images = new Image[6];
        for(int i = 0; i <6; i++){
            if(rd.nextBoolean()){
                try {
                    images[i] = new Image(new FileInputStream("src/main/resources/com/example/Planet/"+planets[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                location++;
            }
        }
        Planet1.setImage(images[0]);
        Planet2.setImage(images[1]);
        Planet3.setImage(images[2]);
        Planet4.setImage(images[3]);
        Planet5.setImage(images[4]);
        Planet6.setImage(images[5]);
    }
}
