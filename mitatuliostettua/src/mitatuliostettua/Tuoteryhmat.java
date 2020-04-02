package mitatuliostettua;

import java.util.Arrays;

/**
 * pitää yllä tuoteryhmärekisteriä
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Tuoteryhmat {

    
    private static final int MAX_TUOTERYHMIA = 20;
    private String tiedosto = "";
    private int lkm = 0;
    private Tuoteryhma[] alkiot = new Tuoteryhma[MAX_TUOTERYHMIA];
    
   
    
    
    
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
    }
    
    
    /**Tähän tulee tuoteryhman poisto
     * @param tuoteryhma poistettava tuoteryhma
     */
    @SuppressWarnings("unused")
    public void poista(Tuoteryhma tuoteryhma) {
        //
    }
    
    
    /** Palauttaa tuoteryhman viitteen
     * @param i sen ktuoteryhman indeksi, jonka viite halutaan
     * @return tuoteryhman viite 
     * @throws IndexOutOfBoundsException jos i menee alkiot-taulukon ulkopuolelle
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
     * Lukee tuoteryhmat tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedosto = hakemisto + "/nimet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedosto);
    }
    
    
    
    /**
     * Tulee tallentamaan kauppareissut tiedostoon sitten kun toimii
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallennaTuoteryhma() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedosto);
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
        ruoka.annaTiedot();
        juoma.rekisteroi();
        juoma.annaTiedot();

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
}
