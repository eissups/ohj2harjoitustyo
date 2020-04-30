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
import java.util.NoSuchElementException;

import mitatuliostettua.Kauppareissut.KauppareissutIterator;

/**
 * pitää yllä tuoteryhmärekisteriä
 * @author elisa
 * @version 19.3.2020
 *
 */
@SuppressWarnings("unused")
public class Tuoteryhmat implements Iterable<Tuoteryhma> {

    
    private static final int MAX_TUOTERYHMIA = 20;
    private String tiedosto = "";
    private int lkm = 0;
    private Tuoteryhma[] alkiot = new Tuoteryhma[MAX_TUOTERYHMIA];
    private boolean muutettu = false;
    private String tiedostonNimi = "tuoteryhmat"; 
    
    /**
     * Oletusmuodostaja
     */
    public Tuoteryhmat() {
        //
    }
    
    
    /**
     * Lisää tuoteryhman tietorakenteeseen. Tietorakenne omistajaksi.
     * @param tuoteryhma tuoteryhma, joka lisataan
     * @example
     * <pre name="test">
     * Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
     * Tuoteryhma ruoka = new Tuoteryhma();
     * tuoteryhmat.lisaa(ruoka);
     * tuoteryhmat.getLkm() === 1;
     * Tuoteryhma juoma = new Tuoteryhma();
     * tuoteryhmat.lisaa(juoma);
     * tuoteryhmat.getLkm() === 2; 
     * tuoteryhmat.annaViite(0) === ruoka;
     * tuoteryhmat.annaViite(1) === juoma;
     * Tuoteryhma joku = new Tuoteryhma();
     * tuoteryhmat.lisaa(joku);
     * tuoteryhmat.getLkm() === 3;  
     * </pre>
     */
    public void lisaa(Tuoteryhma tuoteryhma) {
        
        if ( lkm >= alkiot.length ) {
            alkiot = Arrays.copyOf(alkiot, alkiot.length+1);
        }
        this.alkiot[lkm] = tuoteryhma;
        lkm++;
        muutettu = true;
    }
    
    
    /**Tähän tulee tuoteryhman poisto
     * @param tuoteryhma poistettava tuoteryhma
     */
    public void poista(Tuoteryhma tuoteryhma) {
        //
    }
    
    
    /** Palauttaa tuoteryhman viitteen
     * @param i sen ktuoteryhman indeksi, jonka viite halutaan
     * @return tuoteryhman viite 
     * @throws IndexOutOfBoundsException jos i menee alkiot-taulukon ulkopuolelle
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public Tuoteryhma annaViite(int i) throws IndexOutOfBoundsException{
        
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi" + i);
        return alkiot[i];             
    }
    
    
    /**Palauttaa tuoteryhmien määräm
     * @return ktuoteryhmien määrän
     */
    public int getLkm() {
        
        return lkm;
    }
    
    
    /**
     * Lukee kauppareissut tiedostosta.  Kesken.
     * @param nimi tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     *  Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
     *  Tuoteryhma eka = new Tuoteryhma(), toka = new Tuoteryhma();
     *  eka.annaTiedot("moi");
     *  toka.annaTiedot("hei");
     *  String hakemisto = "testiii";
     *  String tiedNimi = hakemisto+"/tuoteryhmt";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  tuoteryhmat.lueTiedostosta(tiedNimi);
     *  tuoteryhmat.lisaa(eka);
     *  tuoteryhmat.lisaa(toka);
     *  tuoteryhmat.tallennaTuoteryhma();
     *  tuoteryhmat = new Tuoteryhmat();            // Poistetaan vanhat luomalla uusi
     *  tuoteryhmat.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Tuoteryhma> i = tuoteryhmat.iterator();
     *  i.hasNext() === true;
     *  tuoteryhmat.lisaa(toka);
     *  tuoteryhmat.tallennaTuoteryhma();
     *  ftied.delete() === false;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === false;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        setTiedostonNimi(nimi);
        try ( BufferedReader fi = new BufferedReader(new FileReader("tuoteryhmat.dat")) ) {
            tiedosto = fi.readLine();
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Tuoteryhma tuoteryhma = new Tuoteryhma();
                tuoteryhma.parse(rivi); // voisi olla virhekäsittely
                lisaa(tuoteryhma);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + "tuoteryhmat.dat" + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @param nimi tiedoston nimi
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostostaa(String nimi) throws SailoException {
        lueTiedostosta(nimi);
    }
    
    
    /**
     * Tallentaa kauppareissut tiedostoon sitten kun toimii
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallennaTuoteryhma() throws SailoException {

        if ( !muutettu ) return;

        File fbak = new File("tuoteryhmat.bak");
        File ftied = new File("tuoteryhmat.dat");
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Tuoteryhma tuoteryhma : this) {
                fo.println(tuoteryhma.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi;
    }


    /**
     * Asettaa tiedoston perusnimen ilan tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonNimi(String nimi) {
        tiedostonNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedosto() {
        return getTiedostonNimi() + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonNimi + ".bak";
    }


    /**
     * Palauttaa koko nimen
     * @return koko nimi merkkijononna
     */
    public String getKokoNimi() {
        return tiedosto;
    }
    
    
    /**
     * @author elisa
     * @version 3.4.2020
     *
     */
    public class TuoteryhmatIterator implements Iterator<Tuoteryhma> {
        private int kohdalla = 0;
    
       
        /**
         * Onko olemassa vielä seuraavaa tuoteryhmaa
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä tuoteryhmia
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava tuoteryhma
         * @return seuraava tuoteryhma
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Tuoteryhma next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return annaViite(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Me ei poisteta");
        }

    }
    
    
    @Override
    public Iterator<Tuoteryhma> iterator() {
        return new TuoteryhmatIterator();
    }
    
    
    /**Etsii vastaavat tuoteryhmät
     * @param hakuehto ehto
     * @param k tunnusnymero
     * @return löytyneet tuoteryhmät uutena 
     * @example
     * <pre name="test">
     * Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
     * Tuoteryhma tuoteryhma = new Tuoteryhma();
     * Tuoteryhma tuote = new Tuoteryhma();
     * tuoteryhmat.lisaa(tuoteryhma):
     * tuoteryhmat.lisaa(tuote);
     * Collection<Tuoteryhma> loytyneet = new ArrayList<Tuoteryhma>();
     * loytyneet.add(tuoteryhma);
     * loytyneet,add(tuote);
     * tuoteryhmat.etsi() === loytyneet;
     * </pre>
     */
    public Collection<Tuoteryhma> etsi(String hakuehto, int k) { 
        
        Collection<Tuoteryhma> loytyneet = new ArrayList<Tuoteryhma>(); 
        for (Tuoteryhma tuoteryhma : this) { 
            loytyneet.add(tuoteryhma);  
        } 
        return loytyneet; 
    }
    
    
    /**
     * Testiohjelma tuoteryhmille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tuoteryhmat tuoteryhmat = new Tuoteryhmat();

        Tuoteryhma ruoka = new Tuoteryhma();
        Tuoteryhma juoma = new Tuoteryhma();
        ruoka.rekisteroi();
        ruoka.annaTiedot("jii");
        juoma.rekisteroi();
        juoma.annaTiedot("jaa");

        //try {
            tuoteryhmat.lisaa(ruoka);
            tuoteryhmat.lisaa(juoma);

            System.out.println("============= Jäsenet testi =================");

            for (int i = 0; i < tuoteryhmat.getLkm(); i++) {
                Tuoteryhma tuoteryhma = tuoteryhmat.annaViite(i);
                System.out.println("Tuoteryhma: " + i);
                tuoteryhma.tulosta(System.out);
             }

        //} catch (SailoException ex) {
        //    System.out.println(ex.getMessage());
          //}
    }


    /**Paluttaa kaikki tuoteryhmät taulukkona
     * @return kaikki tuoteryhmät
     */
    public Tuoteryhma[] getTuoteryhmat() {
        return alkiot;
        
    }
    
}

