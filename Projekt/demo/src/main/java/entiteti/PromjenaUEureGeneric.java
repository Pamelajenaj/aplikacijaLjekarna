package entiteti;

import java.util.ArrayList;
import java.util.List;

public class PromjenaUEureGeneric<T> {

    private List<T> cijene = new ArrayList<>();

    public void dodajCijenu(T cijena){
        cijene.add(cijena);
    }

    public Float izracunajUEurima(){
        Float cijena=0F;

        for(int i=0;i<cijene.size();i++){
            cijena = cijena+ (float)cijene.get(i);
        }

        return cijena / 7.5345F;
    }
}
