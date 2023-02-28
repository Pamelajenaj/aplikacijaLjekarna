package entiteti;

import exceptioni.MBOMoraImati9Znamenki;
import exceptioni.MBONeSmijeSadrzavatiSlova;

import java.time.LocalDate;

public final class Pacijent extends Osoba implements provjeraMBO{

    private Long id;
    private String ime;
    private String prezime;
    private String mbo;
    private LocalDate datumRodjenja;

    public Pacijent(Long id, String ime, String prezime, String mbo, LocalDate datumRodjenja) {
        super(ime,prezime);
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.mbo = mbo;
        this.datumRodjenja = datumRodjenja;
    }

    public Pacijent() {
        super("","");
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

    public String getMbo() {
        return mbo;
    }

    public void setMbo(String mbo) {
        this.mbo = mbo;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void provjeraIspravnostuMBO(String mbo) throws MBOMoraImati9Znamenki, MBONeSmijeSadrzavatiSlova {
        if(mbo.length() != 9){
            throw new MBOMoraImati9Znamenki("Mbo mora imati 9 znamenki!");
        }

        char[] znamenkeMBO = mbo.toCharArray();

        for(int i=0;i<znamenkeMBO.length;i++) {
            if(!Character.isDigit(znamenkeMBO[i])){
                throw new MBONeSmijeSadrzavatiSlova("Mbo ne smije sadrzavati slova!");
            }
        }

    }


}
