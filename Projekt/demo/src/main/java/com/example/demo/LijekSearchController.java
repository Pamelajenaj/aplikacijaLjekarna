package com.example.demo;

import entiteti.Djelatnik;
import entiteti.Lijek;
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

public class LijekSearchController {
    @FXML
    private TextField nazivLijekaTextField;

    @FXML
    private TextField sifraLijekaTextField;

    @FXML
    private TextField djelatnaTvarLijekaTextField;

    @FXML
    private TextField dobavljacLijekaTextField;

    @FXML
    private TextField cijenaLijekaTextField;


    @FXML
    private TableView<Lijek> lijekTableView;

    @FXML
    private TableColumn<Lijek, String> idLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> nazivLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> sifraLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> djelatnaTvarLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> dobavljacLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> kolicinaLijekaTableColumn;

    @FXML
    private TableColumn<Lijek, String> cijenaLijekaTableColumn;


    @FXML
    public void initialize(){
        List<Lijek> lijekovi = HelloController.getLijekovi();

        idLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getId().toString());
        });

        nazivLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getNaziv().toString());
        });

        sifraLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getSifra().toString());
        });

        djelatnaTvarLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getDjelatnaTvar().toString());
        });

        dobavljacLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getDobavljac().getNaziv().toString());
        });

        kolicinaLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getKolicina().toString());
        });

        cijenaLijekaTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getCijena().toString());
        });


        ObservableList<Lijek> observableList = FXCollections.observableList(lijekovi);
        lijekTableView.setItems(observableList);

    }


    @FXML
    public void gumbPretrazi(){
        String naziv= nazivLijekaTextField.getText();
        String sifra = sifraLijekaTextField.getText();
        String djelatnaTvar= djelatnaTvarLijekaTextField.getText();
        String dobavljac = dobavljacLijekaTextField.getText();
        String cijena = cijenaLijekaTextField.getText();


        List<Lijek> lijekovi = HelloController.getLijekovi();

        if(Optional.of(naziv).isEmpty() == false){
            lijekovi = lijekovi.stream().filter(s->s.getNaziv().toLowerCase().contains(naziv.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(sifra).isEmpty() == false){
            lijekovi = lijekovi.stream().filter(s->s.getSifra().toLowerCase().contains(sifra.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(djelatnaTvar).isEmpty() == false){
            lijekovi = lijekovi.stream().filter(s->s.getDjelatnaTvar().toLowerCase().contains(djelatnaTvar.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(dobavljac).isEmpty() == false){
            lijekovi = lijekovi.stream().filter(s->s.getDobavljac().getNaziv().toLowerCase().contains(dobavljac.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(cijena).isEmpty() == false){
            lijekovi = lijekovi.stream().filter(s->s.getCijena().toString().toLowerCase().contains(cijena.toLowerCase())).collect(Collectors.toList());
        }


        ObservableList<Lijek> observableList = FXCollections.observableList(lijekovi);
        lijekTableView.setItems(observableList);
    }


}
