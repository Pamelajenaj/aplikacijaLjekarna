package entiteti;

import java.io.Serial;
import java.io.Serializable;

public class Dobavljac implements Serializable {

    @Serial
    private static final long serialVersionUID = -4288440463604039223L;
    private Long id;
    private String naziv;
    private String sifra;

    public Dobavljac(Long id, String naziv, String sifra) {
        this.id = id;
        this.naziv = naziv;
        this.sifra = sifra;
    }

    public Dobavljac() {

    }

    public static class BuilderPattern{
        private Long id;
        private String naziv;
        private String sifra;

        public BuilderPattern(Long id) {
            this.id = id;
        }

        public BuilderPattern nazivDobavljaca(String naziv){
            this.naziv = naziv;
            return this;
        }

        public BuilderPattern sifraDobavljaca(String sifra){
            this.sifra = sifra;
            return this;
        }

        public Dobavljac build(){
            Dobavljac dobavljac= new Dobavljac();

            dobavljac.id = this.id;
            dobavljac.naziv = this.naziv;
            dobavljac.sifra = this.sifra;

            return dobavljac;
        }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
