package niti;


import com.example.demo.KosaricaSearchController;

public class DeserijalizacijaRacunaThread implements Runnable{

    @Override
    public void run() {
        KosaricaSearchController.dohvacanjeRacuna();
    }
}
