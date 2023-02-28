package com.example.demo;

import entiteti.Djelatnik;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DjelatnikSearchController {
    @FXML
    private TextField imeDjelatnikaTextField;

    @FXML
    private TextField prezimeDjelatnikaTextField;

    @FXML
    private TextField sifraDjelatnikaTextField;

    @FXML
    private TextField funkcijaDjelatnikaTextField;



    @FXML
    private TableView<Djelatnik> djelatnikTableView;

    @FXML
    private TableColumn<Djelatnik, String> idDjelatnikaTableColumn;

    @FXML
    private TableColumn<Djelatnik, String> imeDjelatnikaTableColumn;

    @FXML
    private TableColumn<Djelatnik, String> prezimeDjelatnikaTableColumn;

    @FXML
    private TableColumn<Djelatnik, String> sifraDjelatnikaTableColumn;

    @FXML
    private TableColumn<Djelatnik, String> funkcijaDjelatnikaTableColumn;


    @FXML
    public void initialize(){
        List<Djelatnik> djelatnici = HelloController.getDjelatnici();

        idDjelatnikaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getId().toString());
        });

        imeDjelatnikaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getIme().toString());
        });

        prezimeDjelatnikaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getPrezime().toString());
        });

        sifraDjelatnikaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getSifra().toString());
        });

        funkcijaDjelatnikaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getFunkcija().toString());
        });

        ObservableList<Djelatnik> observableList = FXCollections.observableList(djelatnici);
        djelatnikTableView.setItems(observableList);

    }


    @FXML
    public void gumbPretrazi(){
        String ime= imeDjelatnikaTextField.getText();
        String prezime = prezimeDjelatnikaTextField.getText();
        String sifra= sifraDjelatnikaTextField.getText();
        String funkcija = funkcijaDjelatnikaTextField.getText();

        List<Djelatnik> djelatnici = HelloController.getDjelatnici();

        if(Optional.of(ime).isEmpty() == false){
            djelatnici = djelatnici.stream().filter(s->s.getIme().toLowerCase().contains(ime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(prezime).isEmpty() == false){
            djelatnici = djelatnici.stream().filter(s->s.getPrezime().toLowerCase().contains(prezime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(sifra).isEmpty() == false){
            djelatnici = djelatnici.stream().filter(s->s.getSifra().toLowerCase().contains(sifra.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(funkcija).isEmpty() == false){
            djelatnici = djelatnici.stream().filter(s->s.getFunkcija().toLowerCase().contains(funkcija.toLowerCase())).collect(Collectors.toList());
        }

        ObservableList<Djelatnik> observableList = FXCollections.observableList(djelatnici);
        djelatnikTableView.setItems(observableList);
    }

}
