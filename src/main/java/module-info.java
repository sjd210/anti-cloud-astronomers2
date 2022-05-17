module com.example.anticloudastronomers2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.anticloudastronomers2 to javafx.fxml;
    exports com.example.anticloudastronomers2;
}