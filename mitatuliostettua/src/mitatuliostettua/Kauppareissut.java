package mitatuliostettua;

import java.util.Arrays;

/**
 * @author elisa
 * @version 17.3.2020
 *
 */
public class Kauppareissut {
    
    private static final int MAX_KAUPPAREISSUJA = 2;
    private String tiedosto = "";
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
        
        
    }
    
    
    /** Palauttaa kauppareissun viitteen
     * @param i sen kauppareissun indeksi, jonka viite halutaan
     * @return kauppareissun viite 
     * @throws IndexOutOfBoundsException jos i menee alkiot-taulukon ulkopuolelle
     */
    public Kauppareissu annaViite(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi" + i);
        return alkiot[i];
        
          
    }
    
    /**Palauttaa kauppareissujen määräm
     * @return kauppareissujen määrän
     */
    public int getLkm() {
        return lkm;
        
    }
    
    
    /**
     * Lukee kauppareissut tiedostosta.  Kesken.
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
    public void tallennaReissu() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedosto);
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
