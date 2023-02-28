package com.example.demo;

import entiteti.Dobavljac;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class PrikazPromjenaController {

    @FXML
    private TableView<String> promjeneTableView;


    @FXML
    private TableColumn<String, String> svePromjene;




    @FXML
    public void initialize(){
        List<String> promjene = HelloController.promjeneUAplikaciji;

        svePromjene.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue());
        });


        ObservableList<String> observableList = FXCollections.observableList(promjene);
        promjeneTableView.setItems(observableList);

    }
}
