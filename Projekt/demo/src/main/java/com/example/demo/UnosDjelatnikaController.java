package com.example.demo;

import entiteti.Djelatnik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import podaci.Database;

import java.util.List;

public class UnosDjelatnikaController {

    @FXML
    private TextField imeDjelatnika;

    @FXML
    private TextField prezimeDjelatnika;

    @FXML
    private TextField sifraDjelatnika;

    @FXML
    private TextField funkcijaDjelatnika;


    @FXML
    private void onUnesiItemClick() {
        StringBuilder korisnikovePogreske=new StringBuilder();
        List<Djelatnik> djelatnici=HelloController.getDjelatnici();

        String imeDjelatnikaText=imeDjelatnika.getText();
        if(imeDjelatnikaText.isEmpty()){
            korisnikovePogreske.append("Ime djelatnika ne smije biti prazno!\n");
        }

        String prezimeDjelatnikaText=prezimeDjelatnika.getText();
        if(prezimeDjelatnikaText.isEmpty()){
            korisnikovePogreske.append("Prezime djelatnika ne smije btii prazno!\n");
        }

        String sifraDjelatnikaText=sifraDjelatnika.getText();
        if(sifraDjelatnikaText.isEmpty()){
            korisnikovePogreske.append("Sifra djelatnika ne smije btii prazno!\n");
        }

        String funkcijaDjelatnikaText=funkcijaDjelatnika.getText();
        if(funkcijaDjelatnikaText.isEmpty()){
            korisnikovePogreske.append("Funkcija djelatnika ne smije btii prazno!\n");
        }


        if(korisnikovePogreske.isEmpty()){

            Long id=Long.parseLong(String.valueOf(djelatnici.size()));

            djelatnici.add(new Djelatnik(id+1,imeDjelatnikaText,prezimeDjelatnikaText,sifraDjelatnikaText,funkcijaDjelatnikaText));
            HelloController.setDjelatnici(djelatnici);

            Database.dodajDjelatnika(new Djelatnik(id,imeDjelatnikaText,prezimeDjelatnikaText,sifraDjelatnikaText,funkcijaDjelatnikaText));
            clearAll();

            HelloController.promjeneUAplikaciji.add("Admin je dodao novog djelatnika u aplikaciju");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Djelatnik spremljen!");
            alert.setHeaderText("Uspjesno ste spremili djelatnika!");
            alert.setContentText("Djelatnik " + imeDjelatnikaText +" "+prezimeDjelatnikaText + " spremljen u bazu podataka!");

            alert.showAndWait();
        }
        else{

            HelloController.logger.error("Korisnik je napravio sljedece greske: "+korisnikovePogreske);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska kod spremanja djelatnika");
            alert.setHeaderText("Napravili ste gresku!");
            alert.setContentText(korisnikovePogreske.toString());

            alert.showAndWait();
        }

    }


    private void clearAll(){
        imeDjelatnika.clear();
        prezimeDjelatnika.clear();
        sifraDjelatnika.clear();
        funkcijaDjelatnika.clear();
    }


}
