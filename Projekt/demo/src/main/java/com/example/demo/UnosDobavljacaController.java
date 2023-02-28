package com.example.demo;

import entiteti.Djelatnik;
import entiteti.Dobavljac;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import podaci.Database;

import java.util.List;

public class UnosDobavljacaController {

    @FXML
    private TextField nazivDobavljaca;

    @FXML
    private TextField sifraDobavljaca;


    @FXML
    private void onUnesiItemClick() {
        StringBuilder korisnikovePogreske=new StringBuilder();
        List<Dobavljac> dobavljaci=HelloController.getDobavljaci();

        String nazivDobavljacaText=nazivDobavljaca.getText();
        if(nazivDobavljacaText.isEmpty()){
            korisnikovePogreske.append("Naziv dobavljaca ne smije biti prazno!\n");
        }


        String sifraDobavljacaText=sifraDobavljaca.getText();
        if(sifraDobavljacaText.isEmpty()){
            korisnikovePogreske.append("Sifra dobavljaca ne smije btii prazno!\n");
        }


        if(korisnikovePogreske.isEmpty()){

            Long id=Long.parseLong(String.valueOf(dobavljaci.size()));

            Dobavljac uneseniDobavljac = new Dobavljac.BuilderPattern(id+1).nazivDobavljaca(nazivDobavljacaText).sifraDobavljaca(sifraDobavljacaText).build();

            dobavljaci.add(uneseniDobavljac);
            HelloController.setDobavljaci(dobavljaci);

            Database.dodajDobavlajca(uneseniDobavljac);
            clearAll();

            HelloController.promjeneUAplikaciji.add("Admin je dodao novog dobavljaca u aplikaciju");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Djelatnik spremljen!");
            alert.setHeaderText("Uspjesno ste spremili dobavljaca!");
            alert.setContentText("Dobavljac " + nazivDobavljacaText +" spremljen u bazu podataka!");

            alert.showAndWait();
        }
        else{
            HelloController.logger.error("Korisnik je napravio sljedece greske: "+korisnikovePogreske);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska kod spremanja dobavljaca");
            alert.setHeaderText("Napravili ste gresku!");
            alert.setContentText(korisnikovePogreske.toString());

            alert.showAndWait();
        }

    }


    private void clearAll(){
        nazivDobavljaca.clear();
        sifraDobavljaca.clear();
    }

}
