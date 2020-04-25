package mitatuliostettua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author elisa
 * @version 17.3.2020
 * Kauppareissut, kauppareissujen hallinta
 */
public class Kauppareissut implements Iterable<Kauppareissu>{
    
    private static final int MAX_KAUPPAREISSUJA = 2;
    private String tiedosto = "";
    private String tiedostonNimi = "kauppareissut";
    private boolean muutettu = false;
    private int lkm = 0;
    private Kauppareissu[] alkiot = new Kauppareissu[MAX_KAUPPAREISSUJA];
    
   
    /**
     * Oletusmuodostaja
     */
    public Kauppareissut( ) {
        //
    }
    
    
    /**
     * Lisää kauppareissun tietorakenteeseen. Tietorakenne omistajaksi.
     * @param kauppareissu kauppareissu, joka lisataan
     * @example
     * <pre name="test">
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu eka = new Kauppareissu();
     * kauppareissut.lisaa(eka);
     * kauppareissut.getLkm() === 1;
     * Kauppareissu toka= new Kauppareissu();
     * kauppareissut.lisaa(toka);
     * kauppareissut.getLkm() === 2; 
     * kauppareissut.annaViite(0) === eka;
     * kauppareissut.annaViite(1) === toka;
     * Kauppareissu juu = new Kauppareissu();
     * kauppareissut.lisaa(juu);
     * kauppareissut.getLkm() === 3;  
     * </pre>
     */
    public void lisaa(Kauppareissu kauppareissu) {       
        if ( lkm >= alkiot.length ) {
            alkiot = Arrays.copyOf(alkiot, alkiot.length+1);
        }
        this.alkiot[lkm] = kauppareissu;
        lkm++;
        muutettu = true;   
    }
    
    
    /**
     * Korvaa kauppareissun tietorakenteessa.  Ottaa sen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kauppareissu.  Jos ei löydy,
     * niin lisätään uutena kauppareissuna.
     * @param kauppareissu korvattava tai lisättävä kauppareissu
     * @throws SailoException jos tietorakenne on jo täynnä
     */
    public void korvaaTaiLisaa(Kauppareissu kauppareissu) throws SailoException {
        int id = kauppareissu.getTunnus();
        for (int i = 0; i < lkm; i++) {
            if ( alkiot[i].getTunnus() == id ) {
                alkiot[i] = kauppareissu;
                muutettu = true;
                return;
            }
        }
        lisaa(kauppareissu);
    }
    
    
    /** Palauttaa kauppareissun viitteen
     * @param i sen kauppareissun indeksi, jonka viite halutaan
     * @return kauppareissun viite 
     * @throws IndexOutOfBoundsException jos i menee alkiot-taulukon ulkopuolelle
     * </pre>
     */
    public Kauppareissu annaViite(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi" + i);
        return alkiot[i];     
    }
    
    
    /**Palauttaa kauppareissujen määräm
     * @return kauppareissujen määrän
     * @example
     * <pre name="test">
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu eka = new Kauppareissu();
     * kauppareissut.lisaa(eka);
     * kauppareissut.getLkm() === 1;
     * Kauppareissu toka= new Kauppareissu();
     * kauppareissut.lisaa(toka);
     * kauppareissut.getLkm() === 2; 
     * </pre>
     */
    public int getLkm() {
        return lkm; 
    }
     

    /**
     * @param valittuKauppareissu kauppareissu jota muokataan
     * @param pvm päivämäärä, joksi muutetaan
     * @example
     * <pre name="test">
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu kauppareissu = new Kauppareissu();
     * kauppareissu.rekisteroi();
     * kauppareissu.annaTiedot("20.03.2020");
     * kauppareissut.lisaa(kauppareissu);
     * kauppareissu.getPvm() === "20.03.2020";
     * kauppareissut.muokkaa(kauppareissu, "11.03.2020");
     * kauppareissu.getPvm() === "11.03.2020";
     * </pre>
     */
    public void muokkaa(Kauppareissu valittuKauppareissu, String pvm) {
        valittuKauppareissu.annaTiedot(pvm);
        muutettu = true;
    }  
    
    
    /**
     * Lukee kauppareissut tiedostosta.  Kesken.
     * @param tuotutiedosto tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tuotutiedosto) throws SailoException {
        setTiedostonNimi(tuotutiedosto);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            tiedosto = fi.readLine();
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Kauppareissu kauppareissu = new Kauppareissu();
                kauppareissu.parse(rivi); // voisi olla virhekäsittely
                lisaa(kauppareissu);
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
        lueTiedostosta(getTiedostonNimi());
    }

    
    
    /**
     * Tulee tallentamaan kauppareissut tiedostoon sitten kun toimii
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallennaReissu() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Kauppareissu kauppareissu : this) {
                fo.println(kauppareissu.toString());
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
     * @version 2.4.2020
     *
     */
    public class KauppareissutIterator implements Iterator<Kauppareissu> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa kauppareissua
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä kauppareissuja
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava kauppreissu
         * @return seuraava kauppareissu
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Kauppareissu next() throws NoSuchElementException {
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


    /**
     * Palautetaan iteraattori kauppareissuistaan.
     * @return kauppareissu iteraattori
     */
    @Override
    public Iterator<Kauppareissu> iterator() {
        return new KauppareissutIterator();
    }


    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kauppareissujen viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @param localDate paiva
     * @return tietorakenteen löytyneistä jäsenistä 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Kauppareissut kauppareissut = new Kauppareissut(); 
     *   Kauppareissu kauppareissu1 = new Kauppareissu(); kauppareissu1.parse("1|20.03.2020|"); 
     *   Kauppareissu kauppareissu2 = new Kauppareissu(); kauppareissu2.parse("2|20.04.2020|"); 
     *   Kauppareissu kauppareissu3 = new Kauppareissu(); kauppareissu3.parse("3|20.05.2020|"); ; 
     *   kauppareissut.lisaa(kauppareissu1); kauppareissut.lisaa(kauppareissu2); kauppareissut.lisaa(kauppareissu3);
     *   // TODO: toistaiseksi palauttaa kaikki kauppareissut 
     * </pre> 
     */ 
    public Collection<Kauppareissu> etsi(@SuppressWarnings("unused") String hakuehto, int k, LocalDate localDate) { 
        Collection<Kauppareissu> loytyneet = new ArrayList<Kauppareissu>(); 
        
        if(localDate != null) {
            String paiva = localDate.toString();
            for (Kauppareissu kauppareissu : this) { 
                if (kauppareissu.getPvm().equals(paiva)) loytyneet.add(kauppareissu) ;  
            }
        }
        
        else if (k < 1) {
            Kauppareissu[] kaannetty = new Kauppareissu[alkiot.length];
            for(int i = 0, j = alkiot.length-1; i < kaannetty.length; i++,j--) {
                kaannetty[i] = alkiot[j];
              
            }
            for (Kauppareissu kauppareissu : kaannetty) { 
                 loytyneet.add(kauppareissu);  
            }
        }
        
        else {
            for (Kauppareissu kauppareissu : this) { 
                loytyneet.add(kauppareissu);  
            }
        } 
        return loytyneet;
    
    }
     
   
    /** 
     * Etsii kauppareissun id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen jäsenen indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kauppareissut kauppareissut = new Kauppareissut(); 
     * Kauppareissu eka = new Kauppareissu(), toka = new Kauppareissu(), kolmas = new Kauppareissu(); 
     * eka.rekisteroi(); toka.rekisteroi(); kolmas.rekisteroi(); 
     * int id1 = eka.getTunnus(); 
     * kauppareissut.lisaa(eka); kauppareissut.lisaa(toka); kauppareissut.lisaa(kolmas); 
     * kauppareissut.etsiId(id1+1) === 1; 
     * kauppareissut.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnus()) return i; 
        return -1; 
    } 
    
    
    
    /** Poistetaan kauppareissu
     * @param tunnus poistettavan kauppareissun tunnus
     * @return numero 1
     */
    public int poista(int tunnus) {
        int ind = etsiId(tunnus); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    }
    
    
    /** Haetaan alkiot etsimistä varten
     * @return kaikki alkiot
     */
    public Kauppareissu[] etsiOikeat() {
        
        return alkiot;
    }


    /**
     * Testiohjelma kauppareissut-luokalle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
       Kauppareissut kauppareissut = new Kauppareissut();
       Kauppareissu eka = new Kauppareissu();
       Kauppareissu toka = new Kauppareissu();
       Kauppareissu juu = new Kauppareissu();
       eka.rekisteroi();
       toka.rekisteroi();
       eka.annaTiedot();
       toka.annaTiedot();
       
      kauppareissut.lisaa(eka);
       kauppareissut.lisaa(toka);
       kauppareissut.lisaa(juu);
       kauppareissut.getLkm();     
       
       System.out.println("=============== testi ===================");
       
       for(int i = 0; i < kauppareissut.getLkm(); i++) {
           Kauppareissu kauppareissu = kauppareissut.annaViite(i);
           System.out.println("Kauppareissu: " + i);
           kauppareissu.tulosta(System.out);
       }
    }
}
