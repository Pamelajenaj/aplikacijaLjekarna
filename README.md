# aplikacijaLjekarna
JavaFX aplikacija za naručivanje lijekova iz ljekarne.

Applikacija se pokreće tako da nad klasom "HelloApplication" stisnemo desni klik i odabremo "run"

Kako bi se aplikacija mogla pokrenuti potrebno je otvoriti H2 > bin > prvi H2 i napraviti lokalni database 
Potrebno je odabrati Generic H2 (Embedded) sa proizvoljnim URL, Username i Password-om. 
Najjednostavnije: 
- URL = jdbc:h2:~/projektLjekarna
- Username = username
- Password = password

![image](https://user-images.githubusercontent.com/85134549/221918275-b2c364e9-0ca5-40d6-91c2-22280da1c42a.png)

Nakon toga je potrebno importati sve tablice u bazu pošto za ovaj projekt nije korištena online baza.

Tablice se importaju na sljedeći način:
- Download folder "tablice"
- kopirati path do tog foldera (npr: C:\Users\Dario\Desktop\tablice)
- u H2 napisati sljedeće komande: 
CREATE TABLE DJELATNIK AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tablice\djelatnici.txt');
CREATE TABLE DOBAVLJAC AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tablice\dobavljaci.txt');
CREATE TABLE LIJEK AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tablice\lijekovi.txt');
CREATE TABLE PACIJENT AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tablice\pacijenti.txt');

*Pod uvijetom da se za path koristi kopirani path (ovo je samo primjer)*

Napomena: H2 terminal je potrebno zatvoriti prije pokretanje aplikacije pošto na bazi istovremeno može biti samo jedna konekcija.
