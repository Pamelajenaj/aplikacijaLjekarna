package entiteti;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class Racun implements Serializable {
    @Serial
    private static final long serialVersionUID = 8510517524520303549L;

    public Set<Lijek> lijekovi;
    public String pacijent;
    public LocalDateTime datumIVrijemeKupovine;

    public Racun(Set<Lijek> lijekovi, String pacijent, LocalDateTime datumIVrijemeKupovine) {
        this.lijekovi = lijekovi;
        this.pacijent = pacijent;
        this.datumIVrijemeKupovine = datumIVrijemeKupovine;
    }

    public Set<Lijek> getLijekovi() {
        return lijekovi;
    }

    public void setLijekovi(Set<Lijek> lijekovi) {
        this.lijekovi = lijekovi;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }

    public LocalDateTime getDatumIVrijemeKupovine() {
        return datumIVrijemeKupovine;
    }

    public void setDatumIVrijemeKupovine(LocalDateTime datumIVrijemeKupovine) {
        this.datumIVrijemeKupovine = datumIVrijemeKupovine;
    }
}
