module com.example.cellar_automate_lab3 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.cellar_automate_lab3 to javafx.fxml;
    exports com.example.cellar_automate_lab3;
}