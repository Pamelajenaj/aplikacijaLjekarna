package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DobrodosliController {

    @FXML
    private Label poruka;

    @FXML
    private void initialize(){
        poruka.setText("Vasa rola je: "+HelloController.userRole);
    }
}
