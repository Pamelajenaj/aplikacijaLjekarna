package entiteti;

public class Korisnik {
    private String username;
    private String password;
    private String rola;

    public Korisnik(String username, String password, String rola) {
        this.username = username;
        this.password = password;
        this.rola = rola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }
}
