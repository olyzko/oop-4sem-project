module com.example.oopproject {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.oopproject.ui.switch_handler;
    exports com.example.oopproject.controller;
    opens com.example.oopproject.ui.switch_handler to javafx.fxml;
}