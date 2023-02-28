package podaci;

import com.example.demo.HelloController;
import entiteti.Djelatnik;
import entiteti.Dobavljac;
import entiteti.Lijek;
import entiteti.Pacijent;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    public static Connection connectToDatabase() throws SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/database.properties"));

        String databaseURL = configuration.getProperty("URL");
        String databaseUsername = configuration.getProperty("Username");
        String databasePassword = configuration.getProperty("Password");

        return DriverManager.getConnection(databaseURL,databaseUsername,databasePassword);
    }

    public static List<Djelatnik> getDjelatnici(Connection veza) throws SQLException {
        List<Djelatnik> djelatnici = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM DJELATNIK");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String ime = resultSet.getString("IME");
            String prezime = resultSet.getString("PREZIME");
            String sifra = resultSet.getString("SIFRA");
            String funkcija = resultSet.getString("FUNKCIJA");

            djelatnici.add(new Djelatnik(id,ime,prezime,sifra,funkcija));
        }

        return djelatnici;
    }

    public static List<Dobavljac> getDobavljaci(Connection veza) throws SQLException{
        List<Dobavljac> dobavljaci = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM DOBAVLJAC");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String naziv = resultSet.getString("NAZIV");
            String sifra = resultSet.getString("SIFRA");

            dobavljaci.add(new Dobavljac(id,naziv,sifra));
        }

        return dobavljaci;
    }


    public static List<Lijek> getLijekovi(Connection veza) throws SQLException{
        List<Lijek> lijekovi = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM LIJEK");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String naziv = resultSet.getString("NAZIV");
            String sifra = resultSet.getString("SIFRA");
            String djelatnTvar = resultSet.getString("DJELATNATVAR");
            String indexDobavljac = resultSet.getString("DOBAVLJAC");
            Integer kolicina = resultSet.getInt("KOLICINA");
            LocalDate rokTrajanja = resultSet.getDate("ROKTRAJANJA").toLocalDate();
            String rezimIzdavanja = resultSet.getString("REZIMIZDAVANJA");
            Float cijena = resultSet.getFloat("CIJENA");


            Integer index = Integer.valueOf(indexDobavljac);
            Dobavljac dobavljac = HelloController.dobavljaci.get(index-1);

            lijekovi.add(new Lijek(id,naziv,sifra,djelatnTvar,dobavljac,rokTrajanja,kolicina,rezimIzdavanja,cijena));
        }

        return lijekovi;
    }


    public static List<Pacijent> getPacijenti(Connection veza) throws SQLException{
        List<Pacijent> pacijenti = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM PACIJENT");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String ime = resultSet.getString("IME");
            String prezime = resultSet.getString("PREZIME");
            String mbo = resultSet.getString("MBO");
            LocalDate datumRodjenja = resultSet.getDate("DATUMRODJENJA").toLocalDate();

            pacijenti.add(new Pacijent(id,ime,prezime,mbo,datumRodjenja));
        }

        return pacijenti;
    }

    public static void dodajDjelatnika(Djelatnik djelatnik) {
        try {
            Connection veza = Database.connectToDatabase();

            PreparedStatement statement=veza.prepareStatement("INSERT INTO DJELATNIK (IME, PREZIME, SIFRA, FUNKCIJA) VALUES (?, ?, ?, ?)");

            statement.setString(1,djelatnik.getIme());
            statement.setString(2,djelatnik.getPrezime());
            statement.setString(3,djelatnik.getSifra());
            statement.setString(4,djelatnik.getFunkcija());

            statement.executeUpdate();

            veza.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajDobavlajca(Dobavljac dobavljac) {
        try {
            Connection veza = Database.connectToDatabase();

            PreparedStatement statement=veza.prepareStatement("INSERT INTO DOBAVLJAC (NAZIV, SIFRA) VALUES (?, ?)");

            statement.setString(1, dobavljac.getNaziv());
            statement.setString(2, dobavljac.getSifra());

            statement.executeUpdate();

            veza.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajLijek(Lijek lijek) {
        try {
            Connection veza = Database.connectToDatabase();

            PreparedStatement statement=veza.prepareStatement("INSERT INTO LIJEK (NAZIV, SIFRA, DJELATNATVAR, DOBAVLJAC, KOLICINA, ROKTRAJANJA, REZIMIZDAVANJA, CIJENA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, lijek.getNaziv());
            statement.setString(2, lijek.getSifra());
            statement.setString(3, lijek.getDjelatnaTvar());
            statement.setString(4, lijek.getDobavljac().getId().toString());
            statement.setString(5, lijek.getKolicina().toString());
            statement.setString(6, lijek.getRokTrajanja().toString());
            statement.setString(7, lijek.getRezimIzdavanja());
            statement.setString(8, lijek.getCijena().toString());

            statement.executeUpdate();

            veza.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajPacijenta(Pacijent pacijent) {
        try {
            Connection veza = Database.connectToDatabase();

            PreparedStatement statement=veza.prepareStatement("INSERT INTO PACIJENT (IME, PREZIME, MBO, DATUMRODJENJA) VALUES (?, ?, ?, ?)");

            statement.setString(1,pacijent.getIme());
            statement.setString(2,pacijent.getPrezime());
            statement.setString(3,pacijent.getMbo());
            statement.setString(4,pacijent.getDatumRodjenja().toString());

            statement.executeUpdate();

            veza.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
