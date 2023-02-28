package com.example.demo;

import entiteti.Djelatnik;
import entiteti.Dobavljac;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DobavljaciController {
    @FXML
    private TextField nazivDobavljacaTextField;

    @FXML
    private TextField sifraDobavljacaTextField;



    @FXML
    private TableView<Dobavljac> dobavljacTableView;

    @FXML
    private TableColumn<Dobavljac, String> idDobavljacaTableColumn;

    @FXML
    private TableColumn<Dobavljac, String> nazivDobavljacaTableColumn;

    @FXML
    private TableColumn<Dobavljac, String> sifraDobavljacaTableColumn;



    @FXML
    public void initialize(){
        List<Dobavljac> dobavljaci = HelloController.getDobavljaci();

        idDobavljacaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getId().toString());
        });

        nazivDobavljacaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getNaziv().toString());
        });

        sifraDobavljacaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getSifra().toString());
        });


        ObservableList<Dobavljac> observableList = FXCollections.observableList(dobavljaci);
        dobavljacTableView.setItems(observableList);

    }


    @FXML
    public void gumbPretrazi(){
        String naziv= nazivDobavljacaTextField.getText();
        String sifra = sifraDobavljacaTextField.getText();


        List<Dobavljac> dobavljaci = HelloController.getDobavljaci();

        if(Optional.of(naziv).isEmpty() == false){
            dobavljaci = dobavljaci.stream().filter(s->s.getNaziv().toLowerCase().contains(naziv.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(sifra).isEmpty() == false){
            dobavljaci = dobavljaci.stream().filter(s->s.getSifra().toLowerCase().contains(sifra.toLowerCase())).collect(Collectors.toList());
        }


        ObservableList<Dobavljac> observableList = FXCollections.observableList(dobavljaci);
        dobavljacTableView.setItems(observableList);
    }


}
