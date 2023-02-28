package com.example.demo;

import entiteti.Djelatnik;
import entiteti.Dobavljac;
import entiteti.Lijek;
import exceptioni.CijenaLijekaMoraBitiBroj;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import podaci.Database;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UnosLijekaController {
    @FXML
    private TextField nazivLijeka;

    @FXML
    private TextField sifraLijeka;

    @FXML
    private TextField djelatnaTvar;

    @FXML
    private ComboBox<String> dobavljac;

    @FXML
    private TextField kolicina;

    @FXML
    private DatePicker rokTrajanja;

    @FXML
    private TextField rezimIzdavanja;

    @FXML
    private TextField cijena;

    @FXML
    private void initialize(){
        List<Dobavljac> dobavljaci = HelloController.getDobavljaci();

        for(int i=0;i<dobavljaci.size();i++){
            dobavljac.getItems().add(dobavljaci.get(i).getNaziv());
        }
    }

    @FXML
    private void onUnesiItemClick() {
        StringBuilder korisnikovePogreske=new StringBuilder();
        List<Lijek> lijekovi=HelloController.getLijekovi();

        String nazivLijekaText=nazivLijeka.getText();
        if(nazivLijekaText.isEmpty()){
            korisnikovePogreske.append("Naziv lijeka ne smije biti prazan!\n");
        }

        String sifraLijekaText=sifraLijeka.getText();
        if(sifraLijekaText.isEmpty()){
            korisnikovePogreske.append("Sifra lijeka ne smije btii prazna!\n");
        }

        String djelatnaTvarText=djelatnaTvar.getText();
        if(djelatnaTvarText.isEmpty()){
            korisnikovePogreske.append("Djelatna tvar lijeka ne smije btii prazna!\n");
        }

        int indexDobavljaca=0;
        if(!Objects.equals(dobavljac.getValue(), "")){
            indexDobavljaca=dobavljac.getSelectionModel().getSelectedIndex();
        }else{
            korisnikovePogreske.append("Dobavljac lijeka ne smije btii prazno!\n");
        }

        String kolicinaText=kolicina.getText();
        if(kolicinaText.isEmpty()){
            korisnikovePogreske.append("Kolicina lijeka ne smije btii prazna!\n");
        }

        LocalDate rokTrajanjaValue = rokTrajanja.getValue();
        if(Optional.ofNullable(rokTrajanjaValue).isPresent()){

        }else{
            korisnikovePogreske.append("Rok trajanja lijeka ne smije btii prazno!\n");
        }

        String rezimIzdavanjaText=rezimIzdavanja.getText();
        if(rezimIzdavanjaText.isEmpty()){
            korisnikovePogreske.append("Rezim izdavanja lijeka ne smije btii prazno!\n");
        }

        String cijenaText=cijena.getText();
        if(cijenaText.isEmpty()){
            korisnikovePogreske.append("Cijena lijeka ne smije btii prazna!\n");
        }else {
            try {
                Lijek objekt = new Lijek();
                objekt.provjeraIspravnosti(cijenaText);
            }catch (CijenaLijekaMoraBitiBroj e){
                korisnikovePogreske.append("Cijena lijeka mora biti broj!\n");
            }
        }

        if(korisnikovePogreske.isEmpty()){

            Long id=Long.parseLong(String.valueOf(lijekovi.size()));

            Dobavljac dobavljacLijeka = HelloController.getDobavljaci().get(indexDobavljaca);

            lijekovi.add(new Lijek(id+1,nazivLijekaText,sifraLijekaText,djelatnaTvarText,dobavljacLijeka,rokTrajanjaValue,Integer.valueOf(kolicinaText),rezimIzdavanjaText,Float.valueOf(cijenaText)));
            HelloController.setLijekovi(lijekovi);

            Database.dodajLijek(new Lijek(id+1,nazivLijekaText,sifraLijekaText,djelatnaTvarText,dobavljacLijeka,rokTrajanjaValue,Integer.valueOf(kolicinaText),rezimIzdavanjaText,Float.valueOf(cijenaText)));
            clearAll();

            HelloController.promjeneUAplikaciji.add("Admin je dodao novi lijek u aplikaciju");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Djelatnik spremljen!");
            alert.setHeaderText("Uspjesno ste spremili lijek!");
            alert.setContentText("Lijek " + nazivLijekaText +" spremljen u bazu podataka!");

            alert.showAndWait();
        }
        else{
            HelloController.logger.error("Korisnik je napravio sljedece greske: "+korisnikovePogreske);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska kod spremanja lijeka");
            alert.setHeaderText("Napravili ste gresku!");
            alert.setContentText(korisnikovePogreske.toString());

            alert.showAndWait();
        }

    }


    private void clearAll(){
        nazivLijeka.clear();
        sifraLijeka.clear();
        djelatnaTvar.clear();
        dobavljac.getEditor().clear();
        kolicina.clear();
        rokTrajanja.getEditor().clear();
        rezimIzdavanja.clear();
        cijena.clear();
    }

}
