package entiteti;

import exceptioni.CijenaLijekaMoraBitiBroj;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Lijek implements Serializable,provjereIspravnosti {

    @Serial
    private static final long serialVersionUID = -746643867094175641L;

    private Long id;
    private String naziv;
    private String sifra;
    private String djelatnaTvar;
    private Dobavljac dobavljac;
    private Integer kolicina;
    private LocalDate rokTrajanja;

    private String rezimIzdavanja;

    private Float cijena;



    public Lijek(Long id, String naziv, String sifra, String djelatnaTvar, Dobavljac dobavljac, LocalDate rokTrajanja, Integer kolicina, String rezimIzdavanja, Float cijena) {
        this.id =id;
        this.naziv = naziv;
        this.sifra = sifra;
        this.djelatnaTvar = djelatnaTvar;
        this.dobavljac = dobavljac;
        this.kolicina = kolicina;
        this.rokTrajanja = rokTrajanja;
        this.rezimIzdavanja = rezimIzdavanja;
        this.cijena = cijena;
    }

    public Lijek() {

    }


    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getDjelatnaTvar() {
        return djelatnaTvar;
    }

    public void setDjelatnaTvar(String djelatnaTvar) {
        this.djelatnaTvar = djelatnaTvar;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(String proizvodac) {
        this.dobavljac = dobavljac;
    }

    public LocalDate getRokTrajanja() {
        return rokTrajanja;
    }

    public void setRokTrajanja(LocalDate rokTrajanja) {
        this.rokTrajanja = rokTrajanja;
    }

    public String getRezimIzdavanja() {
        return rezimIzdavanja;
    }

    public void setRezimIzdavanja(String rezimIzdavanja) {
        this.rezimIzdavanja = rezimIzdavanja;
    }

    public Float getCijena() {
        return cijena;
    }

    public void setCijena(Float cijena) {
        this.cijena = cijena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void provjeraIspravnosti(String cijena) {
        char[] znamenkeCijene = cijena.toCharArray();

        for(int i=0;i<znamenkeCijene.length;i++){
            if(!Character.isDigit(znamenkeCijene[i])){
                throw new CijenaLijekaMoraBitiBroj("Cijena mora biti broj!");
            }
        }
    }
}
