package glavna;

import entiteti.Djelatnik;
import entiteti.Dobavljac;
import entiteti.Lijek;
import entiteti.Pacijent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Glavna {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Djelatnik[] nizDjelatnika=new Djelatnik[3];
        Dobavljac[] nizDobavljaca=new Dobavljac[3];
        Lijek[] nizLijekova=new Lijek[5];
        Pacijent[] nizPacijenata=new Pacijent[3];

        Map<Dobavljac, List<Lijek>> mapaDobavljaca= new HashMap<>();


        for (int i=0;i<3;i++){
            Djelatnik uneseniDjelatnik=unosDjelatnika(input,i);
            nizDjelatnika[i]=uneseniDjelatnik;
        }

        for (int i=0;i<3;i++){
            Dobavljac uneseniDobavljac=unosDobavljaca(input,i);
            nizDobavljaca[i]=uneseniDobavljac;
        }

        for (int i = 0; i<5; i++){
            Lijek uneseniLijek=unosLijeka(input,i,nizDobavljaca);
            nizLijekova[i]=uneseniLijek;
        }

        for (int i=0;i<3;i++){
            Pacijent uneseniPacijent=unosPacijenta(input,i);
            nizPacijenata[i]=uneseniPacijent;
        }

        for(Lijek lijek:nizLijekova){
            if(mapaDobavljaca.containsKey(lijek.getDobavljac())){
                List<Lijek> lijekovi = mapaDobavljaca.get(lijek.getDobavljac());
                lijekovi.add(lijek);
                mapaDobavljaca.put(lijek.getDobavljac(),lijekovi);

            }else{
                List<Lijek> listaNovihLijekova = new ArrayList<>();
                listaNovihLijekova.add(lijek);
                mapaDobavljaca.put(lijek.getDobavljac(),listaNovihLijekova);
            }
        }

        for(Dobavljac key : mapaDobavljaca.keySet()){
            System.out.println("Lijekovi od dobavljaca: "+key.getNaziv()+":");
            List<Lijek> listaLijekova = mapaDobavljaca.get(key);
            for (Lijek lijek : listaLijekova) {
                System.out.println(lijek.getNaziv());
            }
        }

    }

    private static Pacijent unosPacijenta(Scanner input, int i) {
        System.out.println("Unesite "+(i+1)+". pacijenta: ");
        System.out.println("Unesite ime pacijenta:");
        String ime=input.nextLine();
        System.out.println("Unesite prezime pacijenta:");
        String prezime=input.nextLine();
        System.out.println("Unesite MBO pacijenta:");
        String mbo=input.nextLine();
        System.out.print("Unesite datum rođenja pacijenta "+ prezime +" "+ ime +" u formatu (dd.MM.yyyy.):");
        String datumRodjenja=input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate formatiraniDatum=LocalDate.parse(datumRodjenja,formatter);

        Pacijent objektPacijent = new Pacijent((long) i, ime, prezime, mbo, formatiraniDatum);
        return objektPacijent;
    }

    private static Lijek unosLijeka(Scanner input, int i, Dobavljac[] nizDobavljaca) {
        System.out.println("Unesite "+(i+1)+". lijek: ");
        System.out.println("Unesite naziv lijeka:");
        String naziv=input.nextLine();
        System.out.println("Unesite sifru lijeka:");
        String sifra=input.nextLine();
        System.out.println("Unesite djelatnu tvar lijeka:");
        String djelatnaTvar=input.nextLine();
        System.out.println("Unesite dobavljaca lijeka:");
        String dobavljac=input.nextLine();
        System.out.println("Unesite kolicinu lijeka:");
        Integer kolicina= input.nextInt();
        input.nextLine();
        System.out.print("Unesite rok trajanja lijeka "+ naziv + "u formatu (dd.MM.yyyy.):");
        String rokTrajanja=input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate formatiraniRok=LocalDate.parse(rokTrajanja,formatter);
        System.out.println("Unesite rezim izdavanja (rp/OTC) lijeka:");
        String rezimIzdavanja=input.nextLine();
        System.out.println("Unesite cijenu lijeka:");
        Float cijena= input.nextFloat();


        Lijek objektLijek = new Lijek((long) i, naziv, sifra, djelatnaTvar, nizDobavljaca[Integer.parseInt(dobavljac)], formatiraniRok, kolicina, rezimIzdavanja, cijena);
        return objektLijek;

    }

    public static Dobavljac unosDobavljaca(Scanner input, int i) {
        System.out.println("Unesite "+(i+1)+". dobavljaca: ");
        System.out.println("Unesite naziv dobavljaca:");
        String naziv=input.nextLine();
        System.out.println("Unesite sifru dobavljaca:");
        String sifra=input.nextLine();

        Dobavljac objektDobavljac=new Dobavljac((long) i,naziv,sifra);
        return objektDobavljac;
    }

    public static Djelatnik unosDjelatnika(Scanner input, int i) {
        System.out.println("Unesite "+(i+1)+". djelatnika: ");
        System.out.println("Unesite ime djelatnika:");
        String ime=input.nextLine();
        System.out.println("Unesite prezime djelatnika:");
        String prezime=input.nextLine();
        System.out.println("Unesite šifru djelatnika:");
        String sifra=input.nextLine();
        System.out.println("Unesite funkciju djelatnika:");
        String funkcija=input.nextLine();

        Djelatnik objektDjelatnik=new Djelatnik((long) i,ime,prezime,sifra,funkcija);
        return objektDjelatnik;
    }

}

