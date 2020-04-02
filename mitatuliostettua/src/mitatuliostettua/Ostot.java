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



/**
 * Pitää yllä ostot-rekisteriä
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Ostot implements Iterable<Osto> {

    
    
    private String tiedostonNimi = "ostot";
    private int lkm = 0;
    private final Collection<Osto> alkiot = new ArrayList<Osto>();
    private boolean muutettu = false;
    
    
    
    
    /**
     * Oletusmuodostaja
     */
    public Ostot( ) {
        //
    }
    
    /**
     * Lisää Oston tietorakenteeseen. Tietorakenne omistajaksi.
     * @param osto osto, joka lisataan
     * @example
     * <pre name="test">
     * Ostot ostot = new Ostot();
     * Osto eka = new Kauppareissu();
     * ostot.lisaa(eka);
     * ostot.getLkm() === 1;
     * Osto toka = new Osto();
     * ostot.lisaa(toka);
     * ostot.getLkm() === 2; 
     * osoto.annaViite(0) === eka;
     * ostot.annaViite(1) === toka;
     * osto juu = new Osto();
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
     * @param tuotutiedosto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tuotutiedosto) throws SailoException {
        setTiedostonNimi(tuotutiedosto);
        
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Osto osto = new Osto();
                osto.parse(rivi); // voisi olla virhekäsittely
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
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedosto());
    }

    
    /**
     * Tallentaa ostot tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallennaOsto() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedosto());
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
     *  Ostot ostot = ostot();
     *  Osto eka = new Osto(2); ostot.lisaa(eka);
     *  Osto toka = new osto(1); ostot.lisaa(toka);
     *  Osto kolmas = new Osto(2); ostot.lisaa(kolmas);
     *  Osto neljas = new Osto(1); ostot.lisaa(neljas);
     *  Osto viides = new osto(2);ostot.lisaa(viides);
     *  Osto kuudes = new Osto(5); ostot.lisaa(kuudes);
     *  
     *  List<osto> loytyneet;
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
        tuoteryhma.annaTiedot(); 
        
        Tuoteryhma tuoteryhma2 = new Tuoteryhma();
        tuoteryhma2.rekisteroi();
        tuoteryhma2.annaTiedot(); 
        
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
        // TODO Auto-generated method stub
        return null;
    }
    
}
