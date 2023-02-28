package com.example.demo;

import entiteti.Djelatnik;
import entiteti.Pacijent;
import exceptioni.MBOMoraImati9Znamenki;
import exceptioni.MBONeSmijeSadrzavatiSlova;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import podaci.Database;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UnosPacijentaController {

    @FXML
    private TextField imePacijenta;

    @FXML
    private TextField prezimePacijenta;

    @FXML
    private TextField mboPacijenta;

    @FXML
    private DatePicker datumRodenjaPacijenta;


    @FXML
    private void onUnesiItemClick() {
        StringBuilder korisnikovePogreske=new StringBuilder();
        List<Pacijent> pacijenti =HelloController.getPacijenti();

        String imePacijentaText=imePacijenta.getText();
        if(imePacijentaText.isEmpty()){
            korisnikovePogreske.append("Ime pacijenta ne smije biti prazno!\n");
        }

        String prezimePacijentaText=prezimePacijenta.getText();
        if(prezimePacijentaText.isEmpty()){
            korisnikovePogreske.append("Prezime pacijenta ne smije btii prazno!\n");
        }

        String mboPacijentaText=mboPacijenta.getText();
        if(mboPacijentaText.isEmpty()){
            korisnikovePogreske.append("Mbo pacijenta ne smije btii prazno!\n");
        }else{
            try {
                Pacijent objekt = new Pacijent();
                objekt.provjeraIspravnostuMBO(mboPacijentaText);
            } catch (MBOMoraImati9Znamenki | MBONeSmijeSadrzavatiSlova e) {
                HelloController.logger.error("Korisniku se desio exception: "+e.getLocalizedMessage());
                korisnikovePogreske.append(e.getMessage()+"\n");
            }
        }

        LocalDate datumRodenjaPacijentaValue = datumRodenjaPacijenta.getValue();
        if(Optional.ofNullable(datumRodenjaPacijentaValue).isPresent()){

        }else{
            korisnikovePogreske.append("Datum rodjenja ne smije btii prazno!\n");
        }


        if(korisnikovePogreske.isEmpty()){

            Long id=Long.parseLong(String.valueOf(pacijenti.size()));

            pacijenti.add(new Pacijent(id+1,imePacijentaText,prezimePacijentaText,mboPacijentaText,datumRodenjaPacijentaValue));
            HelloController.setPacijenti(pacijenti);

            Database.dodajPacijenta(new Pacijent(id+1,imePacijentaText,prezimePacijentaText,mboPacijentaText,datumRodenjaPacijentaValue));
            clearAll();

            HelloController.promjeneUAplikaciji.add("Admin je dodao novog pacijenta u aplikaciju");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Djelatnik spremljen!");
            alert.setHeaderText("Uspjesno ste spremili pacijenta!");
            alert.setContentText("Pacijent " + imePacijentaText +" "+prezimePacijentaText + " spremljen u bazu podataka!");

            alert.showAndWait();
        }
        else{
            HelloController.logger.error("Korisnik je napravio sljedece greske: "+korisnikovePogreske);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska kod spremanja pacijenta");
            alert.setHeaderText("Napravili ste gresku!");
            alert.setContentText(korisnikovePogreske.toString());

            alert.showAndWait();
        }

    }


    private void clearAll(){
        imePacijenta.clear();
        prezimePacijenta.clear();
        mboPacijenta.clear();
        datumRodenjaPacijenta.getEditor().clear();
    }

}
