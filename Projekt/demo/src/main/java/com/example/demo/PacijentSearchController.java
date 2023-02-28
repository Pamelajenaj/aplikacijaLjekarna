package com.example.demo;

import entiteti.Dobavljac;
import entiteti.Pacijent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PacijentSearchController {
    @FXML
    private TextField imePacijentaTextField;

    @FXML
    private TextField prezimePacijentaTextField;

    @FXML
    private TextField mboPacijentaTextField;

    @FXML
    private DatePicker datumRodjenjaPacijentaTextField;


    @FXML
    private TableView<Pacijent> pacijentTableView;

    @FXML
    private TableColumn<Pacijent, String> idPacijentaTableColumn;

    @FXML
    private TableColumn<Pacijent, String> imePacijentaTableColumn;

    @FXML
    private TableColumn<Pacijent, String>  prezimePacijentaTableColumn;

    @FXML
    private TableColumn<Pacijent, String>  mboPacijentaTableColumn;

    @FXML
    private TableColumn<Pacijent, String>  datumRodjenjaPacijentaTableColumn;


    @FXML
    public void initialize(){
        List<Pacijent> pacijenti = HelloController.getPacijenti();

        idPacijentaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getId().toString());
        });

        imePacijentaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getIme().toString());
        });

        prezimePacijentaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getPrezime().toString());
        });

        mboPacijentaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getMbo().toString());
        });

        datumRodjenjaPacijentaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getDatumRodjenja().toString());
        });


        ObservableList<Pacijent> observableList = FXCollections.observableList(pacijenti);
        pacijentTableView.setItems(observableList);

    }


    @FXML
    public void gumbPretrazi(){
        String ime= imePacijentaTextField.getText();
        String prezime = prezimePacijentaTextField.getText();
        String mbo = mboPacijentaTextField.getText();
        LocalDate datumRodjenja = datumRodjenjaPacijentaTextField.getValue();

        List<Pacijent> pacijenti = HelloController.getPacijenti();

        if(Optional.of(ime).isEmpty() == false){
            pacijenti = pacijenti.stream().filter(s->s.getIme().toLowerCase().contains(ime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(prezime).isEmpty() == false){
            pacijenti = pacijenti.stream().filter(s->s.getPrezime().toLowerCase().contains(prezime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(mbo).isEmpty() == false){
            pacijenti = pacijenti.stream().filter(s->s.getMbo().toLowerCase().contains(mbo.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.ofNullable(datumRodjenja).isPresent()){
            pacijenti = pacijenti.stream().filter(s->s.getDatumRodjenja().toString().equals(datumRodjenja.toString())).collect(Collectors.toList());
        }


        ObservableList<Pacijent> observableList = FXCollections.observableList(pacijenti);
        pacijentTableView.setItems(observableList);
    }



}
