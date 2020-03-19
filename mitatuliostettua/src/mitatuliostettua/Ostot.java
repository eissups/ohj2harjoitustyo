package mitatuliostettua;

import java.util.Arrays;

/**
 * Pitää yllä ostot-rekisteriä
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Ostot {

    
    private static final int MAX_OSTOJA = 2;
    private String tiedosto = "";
    private int lkm = 0;
    private Osto[] alkiot = new Osto[MAX_OSTOJA];
    
    
    
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
        if ( lkm >= alkiot.length ) {
            alkiot = Arrays.copyOf(alkiot, alkiot.length+1);
        }
        this.alkiot[lkm] = osto;
        lkm++;
        
        
    }
    
    
    /** Palauttaa oston viitteen
     * @param i sen oston indeksi, jonka viite halutaan
     * @return oston viite 
     * @throws IndexOutOfBoundsException jos i menee alkiot-taulukon ulkopuolelle
     */
    public Osto annaViite(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi" + i);
        return alkiot[i];
        
          
    }
    
    /**Palauttaa ostojen määräm
     * @return ostojen määrän
     */
    public int getLkm() {
        return lkm;
        
    }
    
    
    /**
     * Lukee ostot tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedosto = hakemisto + "/ostot.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedosto);
    }
    
    
    
    /**
     * Tulee tallentamaan ostot tiedostoon sitten kun toimii
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallennaReissu() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedosto);
    }


    /**
     * Testiohjelma ostot-luokalle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        Tuoteryhma tuoteryhma = new Tuoteryhma();
        tuoteryhma.rekisteroi();
        tuoteryhma.annaTiedot(); 
        
        Tuoteryhma tuoteryhma2 = new Tuoteryhma();
        tuoteryhma2.rekisteroi();
        tuoteryhma2.annaTiedot(); 
        
       Ostot ostot = new Ostot();
       Osto eka = new Osto();
       Osto toka = new Osto();
  
       eka.rekisteroi();
       toka.rekisteroi();
       eka.annaTiedot(tuoteryhma, 5, 10);
       toka.annaTiedot(tuoteryhma2, 2, 30);
       
       ostot.lisaa(eka);
       ostot.lisaa(toka);
       ostot.getLkm();     
       
       System.out.println("=============== testi ===================");
       
       for(int i = 0; i < ostot.getLkm(); i++) {
           Osto ostoo = ostot.annaViite(i);
           System.out.println("Kauppareissu: " + i);
           ostoo.tulosta(System.out);
       }
       
    }
    
}
