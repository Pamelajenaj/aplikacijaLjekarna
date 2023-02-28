package podaci;

import entiteti.Korisnik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {

    public static List<Korisnik> getKorisnici(){
        //File file = new File("dat/user.txt");

        List<Korisnik> korisnici = new ArrayList<>();

        int lineCounter=1;
        String line, username="", password="",rola="";

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("dat/user.txt"));

            while((line=fileReader.readLine()) != null){
                if(lineCounter % 3 == 1){
                    username = line;
                }
                else if(lineCounter % 3 == 2){
                    password = line;
                }
                else if(lineCounter % 3 == 0){
                    rola = line;

                    korisnici.add(new Korisnik(username,password,rola));
                }
                lineCounter++;
            }

            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return korisnici;
    }

}
