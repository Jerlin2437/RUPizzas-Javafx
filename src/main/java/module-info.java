module com.example.softmeth4 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.softmeth4 to javafx.fxml;
    exports com.example.softmeth4;
}