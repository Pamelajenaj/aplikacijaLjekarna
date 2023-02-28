package niti;

import com.example.demo.KosaricaSearchController;


public class SerijalizacijaRacunaThread implements Runnable{


    @Override
    public void run() {
        KosaricaSearchController.spremanjeRacuna();
    }
}
