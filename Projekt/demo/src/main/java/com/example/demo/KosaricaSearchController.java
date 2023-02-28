package com.example.demo;

import entiteti.PromjenaUEureGeneric;
import entiteti.Racun;
import entiteti.Lijek;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import niti.DeserijalizacijaRacunaThread;
import niti.SerijalizacijaRacunaThread;
import podaci.Datoteke;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class KosaricaSearchController {
    @FXML
    private ComboBox<String> lijekovi;

    @FXML
    private TableView<Lijek> kosaricaTableView;

    @FXML
    private TableColumn<Lijek, String> podaciOLijeku;

    @FXML
    private Label ukupnaCijenaLabel;

    public static List<Lijek> odabraniLijekovi= new ArrayList<>();


    @FXML
    public void initialize(){
        List<Lijek> sviLijekovi = HelloController.getLijekovi();

        for(int i=0;i<sviLijekovi.size();i++){
            lijekovi.getItems().add(sviLijekovi.get(i).getNaziv());
        }

        ukupnaCijenaLabel.setText("Ukupna cijena: 0 kn");
    }

    @FXML
    public void buttonDodajUKosaricu() {
        Integer indexOdabranogLijeka = 0;
        StringBuilder korisnikovePogreske = new StringBuilder();


        if(lijekovi.getSelectionModel().isEmpty()){
            korisnikovePogreske.append("Morate odabrati lijek!\n");
        }else{
            indexOdabranogLijeka = lijekovi.getSelectionModel().getSelectedIndex();
        }

        if(korisnikovePogreske.isEmpty()) {
            odabraniLijekovi.add(HelloController.getLijekovi().get(indexOdabranogLijeka));


            podaciOLijeku.setCellValueFactory(c-> {
                StringBuilder podaci = new StringBuilder();

                podaci.append(c.getValue().getSifra()+" "+c.getValue().getNaziv()+" "+c.getValue().getKolicina()+", Dobavljac:"+c.getValue().getDobavljac().getNaziv()+", Cijena:"+c.getValue().getCijena().toString());

                return new SimpleStringProperty(podaci.toString());
            });

            ObservableList<Lijek> observableList = FXCollections.observableList(odabraniLijekovi);
            kosaricaTableView.setItems(observableList);


            Float cijenaSvihLijekova = 0F;
            for(int i=0;i<odabraniLijekovi.size();i++){
                cijenaSvihLijekova = cijenaSvihLijekova+odabraniLijekovi.get(i).getCijena();
            }

            PromjenaUEureGeneric objekt = new PromjenaUEureGeneric();
            objekt.dodajCijenu(cijenaSvihLijekova);

            ukupnaCijenaLabel.setText("Ukupna cijena: "+cijenaSvihLijekova.toString()+" kn"+" / "+objekt.izracunajUEurima()+" EUR");


        }else {
            HelloController.logger.error("Korisnik je napravio sljedece greske: "+korisnikovePogreske);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska prilikom dodavanja lijeka");
            alert.setHeaderText("Neuspjesno dodavanje lijeka u košaricu");
            alert.setContentText(korisnikovePogreske.toString());

            alert.showAndWait();
        }

    }

    @FXML
    public void buttonNaruci() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda");
        alert.setHeaderText("Potvrdite narucivanje lijekova");
        alert.setContentText("Jeste li sigurni da zelite naruciti lijekove?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            Set<Lijek> lijekoviSet = odabraniLijekovi.stream().collect(Collectors.toSet());

            List<Racun> kosaricaZaSada = HelloController.getRacuni();
            kosaricaZaSada.add(new Racun(lijekoviSet,HelloController.userRole, LocalDateTime.now()));

            HelloController.setRacuni(kosaricaZaSada);

            SerijalizacijaRacunaThread serijalizacijaRacuna = new SerijalizacijaRacunaThread();
            DeserijalizacijaRacunaThread deserijalizacijaRacuna = new DeserijalizacijaRacunaThread();

            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(serijalizacijaRacuna);
            executorService.execute(deserijalizacijaRacuna);
            executorService.close();

            odabraniLijekovi.clear();
            ukupnaCijenaLabel.setText("Ukupna cijena: 0 kn");
            kosaricaTableView.refresh();
        }

    }

    public static synchronized void spremanjeRacuna(){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("dat/serijalizacija/racuni.dat"));

            file.writeObject(HelloController.racuni);

            file.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void dohvacanjeRacuna(){
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream("dat/serijalizacija/racuni.dat"));
            HelloController.racuni = (List<Racun>) file.readObject();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
