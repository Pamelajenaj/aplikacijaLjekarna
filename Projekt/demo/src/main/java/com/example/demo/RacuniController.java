package com.example.demo;

import entiteti.Lijek;
import entiteti.Racun;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class RacuniController {
    @FXML
    private TableView<Racun> racuniTableView;

    @FXML
    private TableColumn<Racun, String> lijekoviTableColumn;

    @FXML
    private TableColumn<Racun, String> pacijentTableColumn;

    @FXML
    private TableColumn<Racun, String> DatumIVrijemeTableColumn;


    @FXML
    public void initialize(){
        List<Racun> pacijenti = HelloController.getRacuni();

        lijekoviTableColumn.setCellValueFactory(c-> {
            List<Lijek> lijekoviNaRacunu = c.getValue().getLijekovi().stream().toList();
            StringBuilder lijekoviZaIspis = new StringBuilder();

            for(int i=0;i<lijekoviNaRacunu.size();i++){
                lijekoviZaIspis.append(lijekoviNaRacunu.get(i).getNaziv()+", ");
            }

            return new SimpleStringProperty(lijekoviZaIspis.toString());
        });

        pacijentTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getPacijent().toString());
        });

        DatumIVrijemeTableColumn.setCellValueFactory(c-> {
            return new SimpleStringProperty(c.getValue().getDatumIVrijemeKupovine().toString());
        });


        ObservableList<Racun> observableList = FXCollections.observableList(pacijenti);
        racuniTableView.setItems(observableList);

    }
}
