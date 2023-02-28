package entiteti;

public class Djelatnik extends Osoba{

    private Long id;
    private String ime;
    private String prezime;
    private String sifra;
    private String funkcija;

    public Djelatnik(Long id, String ime, String prezime, String sifra, String funkcija) {
        super(ime,prezime);
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.sifra = sifra;
        this.funkcija = funkcija;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getFunkcija() {
        return funkcija;
    }

    public void setFunkcija(String funkcija) {
        this.funkcija = funkcija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
