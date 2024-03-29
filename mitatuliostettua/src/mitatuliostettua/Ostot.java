package mitatuliostettua;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import fi.jyu.mit.ohj2.Mjonot;
import fxMitatuliostettua.MitatuliostettuaGUIController;



/**
 * Pitää yllä ostot-rekisteriä
 * @author elisa
 * @version 19.3.2020
 *
 */
@SuppressWarnings("unused")
public class Ostot implements Iterable<Osto> {

    private String tiedostonNimi = "ostot";
    private int lkm = 0;
    private final Collection<Osto> alkiot = new ArrayList<Osto>();
    private boolean muutettu = false;
    private int samojenmaara;
    
    
    
    
    
    /**
     * Oletusmuodostaja
     */
    public Ostot( ) {
        //
    }
    
    
    /**
    * Palauttaa oston kenttien lukumäärän
    * @return kenttien lukumäärä
    */
   public int getKenttia() {
       return 13;
   }


   /**
    * Eka kenttä joka on mielekäs kysyttäväksi
    * @return eknn kentän indeksi
    */
   public int ekaKentta() {
       return 1;
   }
   
   
    
    /**
     * Lisää Oston tietorakenteeseen. Tietorakenne omistajaksi.
     * @param osto osto, joka lisataan
     * @example
     * <pre name="test">
     * Ostot ostot = new Ostot();
     * Osto eka = new Osto();
     * ostot.lisaa(eka);
     * ostot.getLkm() === 1;
     * Osto toka = new Osto();
     * ostot.lisaa(toka);
     * ostot.getLkm() === 2; 
     * Osto juu = new Osto();
     * ostot.lisaa(juu);
     * ostot.getLkm() === 3;  
     * </pre>
     */
    public void lisaa(Osto osto) {
        
        alkiot.add(osto);  
        lkm++;
        muutettu = true;
    }
    
    
    
    /**
     * Lukee ostot tiedostosta.  Kesken.
     * @param tuoteryhmat tuoteryhmat
     * @param nimi tiedoston nimi
     * @throws SailoException jos ei onnaa
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
     * Tuoteryhma ruoka = new Tuoteryhma();
     * ruoka.rekisteroi();
     * ruoka.annaTiedot("jii");
     * tuoteryhmat.lisaa(ruoka);
     *  Ostot ostot = new Ostot();
     *  Osto osto1 = new Osto(), osto2 = new Osto();
     *  osto1.rekisteroi();
     *  osto2.rekisteroi();
     *  osto1.annaTiedot(1, "tuote1", 2,  10); 
     *  osto2.annaTiedot(2, "tuote2", 3, 6);
     *  String hakemisto = "testiostot";
     *  String tiedNimi = hakemisto+"/ostot";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  ostot.lueTiedostosta(tuoteryhmat, tiedNimi); 
     *  ostot.lisaa(osto1);
     *  ostot.lisaa(osto2);
     *  ostot.tallennaOsto();
     *  ostot = new Ostot();            // Poistetaan vanhat luomalla uusi
     *  ostot.lueTiedostosta1(tuoteryhmat, tiedNimi );  // johon ladataan tiedot tiedostosta.
     *  Iterator<Osto> i = ostot.iterator();
     *  i.next().toString() === osto1.toString();
     *  i.next().toString() === osto2.toString();
     *  ostot.lisaa(osto2);
     *  ostot.tallennaOsto();
     *  ftied.delete() === false;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === false;
     * </pre>
     */
    public void lueTiedostosta1(Tuoteryhmat tuoteryhmat, String nimi) throws SailoException {
        
        setTiedostonNimi(nimi);
        
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Osto osto = new Osto();
                osto.parse(rivi, tuoteryhmat); // voisi olla virhekäsittely
                lisaa(osto);
            }
            muutettu = false;

            } catch ( FileNotFoundException e ) {
                throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
            } catch ( IOException e ) {
                throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
            }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @param tuoteryhmat tuoteryhmat
     * @param nimi tiedoston nimi
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta(Tuoteryhmat tuoteryhmat, String nimi) throws SailoException {
        
        lueTiedostosta1(tuoteryhmat, nimi);
    }

    
    /**
     * Tallentaa ostot tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     * * <pre>
     * Kelmien kerho
     * 20
     * ; kommenttirivi
     * 1|0|1|tuote1|2|10
     * 1|0|1|tuote1|2|10
     * </pre>
     */
    public void tallennaOsto() throws SailoException {
        
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Osto osto : this) {
                fo.println(osto.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }

    
    /**
     * Asettaa tiedoston nimen ilman tarkenninta
     * @param tied tallennustiedoston nimi
     */
    public void setTiedostonNimi(String tied) {
        tiedostonNimi = tied;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedosto() {
        return tiedostonNimi + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonNimi + ".bak";
    }
 
    
    /**
     * Palauttaa ostojen lukumäärän
     * @return ostojen lukumäärä
     * @example
     * <pre name="test">
     * Ostot ostot = new Ostot();
     * Osto osto = new Osto();
     * Osto esto = new Osto();
     * osto.rekisteroi();
     * esto.rekisteroi();
     * ostot.lisaa(osto);
     * ostot.lisaa(esto);
     * ostot.getLkm() === 2;
     * 
     * </pre>
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Haetaan kaikki kauppareissun ostot
     * @param tunnusnro kauppareissun tunnusnumero jolle ostoja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin ostoihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Ostot ostot = new Ostot();
     *  Osto eka = new Osto(2); ostot.lisaa(eka);
     *  Osto toka = new Osto(1); ostot.lisaa(toka);
     *  Osto kolmas = new Osto(2); ostot.lisaa(kolmas);
     *  Osto neljas = new Osto(1); ostot.lisaa(neljas);
     *  Osto viides = new Osto(2);ostot.lisaa(viides);
     *  Osto kuudes = new Osto(5); ostot.lisaa(kuudes);
     *  
     *  List<Osto> loytyneet;
     *  loytyneet = ostot.annaOstot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = ostot.annaOstot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == toka === true;
     *  loytyneet.get(1) == neljas === true;
     *  loytyneet = ostot.annaOstot(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == kuudes === true;
     * </pre> 
     */
    public List<Osto> annaOstot(int tunnusnro) {
        List<Osto> loydetyt = new ArrayList<Osto>();
        for (Osto ost : alkiot)
            if (ost.getKaupTunnus() == tunnusnro) loydetyt.add(ost);
        return loydetyt;
    }


    /**
     * Testiohjelma ostot-luokalle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        Kauppareissu eka = new Kauppareissu();
        eka.rekisteroi();
        Tuoteryhma tuoteryhma = new Tuoteryhma();
        tuoteryhma.rekisteroi();
        tuoteryhma.annaTiedot("g"); 
        
        Tuoteryhma tuoteryhma2 = new Tuoteryhma();
        tuoteryhma2.rekisteroi();
        tuoteryhma2.annaTiedot("h"); 
        
       Ostot ostot = new Ostot();
       Osto osto = new Osto();
       Osto toka = new Osto();
  

       osto.annaTiedot(1, tuoteryhma.getNimi());
       toka.annaTiedot(2, tuoteryhma2.getNimi());
       osto.rekisteroi();
       toka.rekisteroi();
       
       ostot.lisaa(osto);
       ostot.lisaa(toka);   
       
       System.out.println("=============== testi ===================");
       
       
       List<Osto> ostot2 = ostot.annaOstot(1);
       
       for (Osto os : ostot2) {
           System.out.print(os.getKaupTunnus() + " ");
           os.tulosta(System.out);
       }     
    }

    
    @Override
    public Iterator<Osto> iterator() {
        return alkiot.iterator();
    }


    /**
     * @param ostot2 oostot
     * @param tunnus kauppareissun tunnus
     */
    public void muokkaa(Ostot ostot2, int tunnus) {
 
       poistaKauppareissunTiedot(tunnus);
       for(Osto e : ostot2) {
           lisaa(e); 
       }
     }


    /**
     * @param kaupid kauppareissun id
     * @return ostettujen kokonaishinta
     * @example
     * <pre name="test">
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu eka = new Kauppareissu();
     * eka.rekisteroi();
     * kauppareissut.lisaa(eka);
     * Osto osto = new Osto();
     * Ostot ostot = new Ostot(); 
     * osto.annaTiedot(eka.getTunnus(), "tuote", 3, 5);
     * Osto osto2 = new Osto();
     * osto2.annaTiedot(eka.getTunnus(), "tuote2", 6, 7);
     * ostot.lisaa(osto);
     * ostot.lisaa(osto2);
     * ostot.laskeHinta(eka.getTunnus()) === 12;
     * </pre>
     */
    public int laskeHinta(int kaupid) {
        List<Osto> loydetyt = new ArrayList<Osto>();
        loydetyt = annaOstot(kaupid);
        int summa = 0;
        for (Osto ost : loydetyt) {
            summa = summa + ost.getHinta();
        }
        return summa;
    }


    /**
     * @param tunnus tunnus
     * @return ostot
     */
    public Ostot getOstot(int tunnus) {
        Ostot ostot = new Ostot();
        for (Osto ost : alkiot)
            if (ost.getKaupTunnus() == tunnus) ostot.lisaa(ost);
        return ostot;
        
    }


    /**Poistetaan kauppareissun ostojen tiedot
     * @param tunnus kauppatunnus
     * @return n
     */
    public int poistaKauppareissunTiedot(int tunnus) {
        int n = 0;
        for (Iterator<Osto> it = alkiot.iterator(); it.hasNext();) {
            Osto os = it.next();
            if ( os.getKaupTunnus() == tunnus ) {
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
        
    }


    /**Tällä hetkellä lisätään ostoja
     * @param selectedText turha
     * @param ost osto, joka lisätään
     */
    public void lisaaMuokkaa(String selectedText, Osto ost) {
        
        lisaa(ost);
    }


    /**Etsitään sopivat kauppareissut ja palautetaan listana
     * @param kauppareissui kauppareissut joista etsitään
     * @param ehto hakuehto
     * @return lytyneet kauppariessut
     */
    public Collection<Kauppareissu> etsiSopivat(Kauppareissu[] kauppareissui, String ehto) {
        Collection<Kauppareissu> loytyneet = new ArrayList<Kauppareissu>(); 
        for (Kauppareissu kauppareissu : kauppareissui) { 
            Ostot osot = getOstot(kauppareissu.getTunnus());
            if (etsiloytyyko(osot, ehto) == true) loytyneet.add(kauppareissu);  
        } 
        return loytyneet; 
    }


    private boolean etsiloytyyko(Ostot osot, String ehto) {
        boolean onko = false;
        for (Osto os : osot) { 
            if (os.getTuoteryhma().getNimi().contains(ehto)) onko = true;
        } 
        return onko;
    }


    /**Palautetaan kaikki ostot
     * @return ostot
     */
    public Collection<Osto> annaKaikkiOstot() {
        return alkiot;
        
    }
    }
        

        
