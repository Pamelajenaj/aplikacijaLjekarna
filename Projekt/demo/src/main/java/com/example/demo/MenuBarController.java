package com.example.demo;

import exceptioni.NepostojecePrivilegije;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBarController {

    @FXML
    public void djelatnikSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("djelatnikSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Djelatnici");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void dobavljaciSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dobavljaciSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Dobavljaci");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void lijekiSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lijekSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Lijekovi");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void pacijentSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pacijentSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Pacijenti");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void kosaricaSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("kosaricaSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Kosarica");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void racuniSearch() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("racuni.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Racuni");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void unosDjelatnika() throws IOException {
        if(HelloController.userRole.equals("pacijent")){
            HelloController.logger.error("Pacijent je pokusao dodati novog djelatnika!");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Pogreska!");
            alert.setContentText("Nemate privilegiju dodavanja novog djelatnika");

            alert.showAndWait();
            try{
                throw new NepostojecePrivilegije("Nemate privilegije mijenjanja aplikacije!");
            }catch (NepostojecePrivilegije e){
                System.out.println("Nemate privilegije");
            }
        }else{
            Stage stage = HelloApplication.mainStage;

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosDjelatnika.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500,500);
            stage.setTitle("Unod djelatnika");
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    public void unosDobavljaca() throws IOException {
        if(HelloController.userRole.equals("pacijent")){
            HelloController.logger.error("Pacijent je pokusao dodati novog dobavljaca!");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Pogreska!");
            alert.setContentText("Nemate privilegiju dodavanja novog dobavljaca");

            alert.showAndWait();
            try{
                throw new NepostojecePrivilegije("Nemate privilegije mijenjanja aplikacije!");
            }catch (NepostojecePrivilegije e){
                System.out.println("Nemate privilegije");
            }
        }else {
            Stage stage = HelloApplication.mainStage;

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosDobavljaca.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Unos dobavljaca");
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    public void unosLijeka() throws IOException {
        if(HelloController.userRole.equals("pacijent")){
            HelloController.logger.error("Pacijent je pokusao dodati novi lijek!");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Pogreska!");
            alert.setContentText("Nemate privilegiju dodavanja novog lijeka");

            alert.showAndWait();
            try{
                throw new NepostojecePrivilegije("Nemate privilegije mijenjanja aplikacije!");
            }catch (NepostojecePrivilegije e){
                System.out.println("Nemate privilegije");
            }

        }else {
            Stage stage = HelloApplication.mainStage;

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosLijeka.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Unos lijeka");
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    public void unosPacijenta() throws IOException {
        if(HelloController.userRole.equals("pacijent")){

            HelloController.logger.error("Pacijent je pokusao dodati novog pacijenta!");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Pogreska!");
            alert.setContentText("Nemate privilegiju dodavanja novog pacijenta");

            alert.showAndWait();

            try{
                throw new NepostojecePrivilegije("Nemate privilegije mijenjanja aplikacije!");
            }catch (NepostojecePrivilegije e){
                System.out.println("Nemate privilegije");
            }
        }else {
            Stage stage = HelloApplication.mainStage;

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosPacijenta.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Unos pacijenta");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void prikazPromjeni() throws IOException {
        Stage stage = HelloApplication.mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("prikazPromjena.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500,500);
        stage.setTitle("Promjene");
        stage.setScene(scene);
        stage.show();
    }

}
