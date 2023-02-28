package com.example.demo;

import entiteti.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import podaci.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import podaci.Datoteke;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    public static List<Djelatnik> djelatnici;
    public static List<Dobavljac> dobavljaci;
    public static List<Lijek> lijekovi;
    public static List<Pacijent> pacijenti;
    public static List<Racun> racuni = new ArrayList<>();

    public static List<Korisnik> korisnici;

    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    public static String userRole;
    public static List<String> promjeneUAplikaciji = new ArrayList<>();

    @FXML
    private TextField korisnikovUsernameTextField;

    @FXML
    private TextField korisnikovPasswordTextField;

    @FXML
    private ComboBox<String> korisnikoveRole;


    @FXML
    public void initialize() {

        korisnikoveRole.getItems().add("admin");
        korisnikoveRole.getItems().add("pacijent");

        try{
            Connection connection = Database.connectToDatabase();

            djelatnici = Database.getDjelatnici(connection);
            dobavljaci = Database.getDobavljaci(connection);
            lijekovi = Database.getLijekovi(connection);
            pacijenti = Database.getPacijenti(connection);
            korisnici = Datoteke.getKorisnici();

            ObjectInputStream file = new ObjectInputStream(new FileInputStream("dat/serijalizacija/racuni.dat"));
            racuni = (List<Racun>) file.readObject();
            file.close();

            connection.close();
        } catch (SQLException | IOException e) {
            System.out.println("Ne moguce povezivanje na bazu.");
        }catch (ClassNotFoundException e) {
            System.out.println("Greska");
        }

    }

    @FXML
    private void onLoginClick() throws IOException {

        String username = korisnikovUsernameTextField.getText();
        String password = korisnikovPasswordTextField.getText();
        String rola = korisnikoveRole.getValue();

        for(int i=0;i<korisnici.size();i++) {
            if(username.equals(korisnici.get(i).getUsername())){
                if(password.equals(korisnici.get(i).getPassword())){
                    if(rola.equals(korisnici.get(i).getRola())){

                        userRole = rola;

                        Stage stage = HelloApplication.mainStage;

                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dobrodosli.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 500,500);
                        stage.setTitle("Hello!");
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        }



    }

    public static List<Djelatnik> getDjelatnici() {
        return djelatnici;
    }

    public static void setDjelatnici(List<Djelatnik> djelatnici) {
       HelloController.djelatnici = djelatnici;
    }

    public static List<Dobavljac> getDobavljaci() {
        return dobavljaci;
    }

    public static void setDobavljaci(List<Dobavljac> dobavljaci) {
        HelloController.dobavljaci = dobavljaci;
    }

    public static List<Lijek> getLijekovi() {
        return lijekovi;
    }

    public static void setLijekovi(List<Lijek> lijekovi) {
        HelloController.lijekovi = lijekovi;
    }

    public static List<Pacijent> getPacijenti() {
        return pacijenti;
    }

    public static void setPacijenti(List<Pacijent> pacijenti) {
       HelloController.pacijenti = pacijenti;
    }

    public static List<Racun> getRacuni() {
        return racuni;
    }

    public static void setRacuni(List<Racun> racuni) {
        HelloController.racuni = racuni;
    }
}