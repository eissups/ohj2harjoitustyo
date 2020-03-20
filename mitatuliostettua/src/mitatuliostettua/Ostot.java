package mitatuliostettua;



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

    
    
    private String tiedosto = "";
    private int lkm = 0;
    private final Collection<Osto> alkiot        = new ArrayList<Osto>();
    
    
    
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
    public void tallennaOsto() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedosto);
    }
    
    
    /**
     * Palauttaa kerhon harrastusten lukumäärän
     * @return harrastusten lukumäärä
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
        
        Tuoteryhma tuoteryhma = new Tuoteryhma();
        tuoteryhma.rekisteroi();
        tuoteryhma.annaTiedot(); 
        
        Tuoteryhma tuoteryhma2 = new Tuoteryhma();
        tuoteryhma2.rekisteroi();
        tuoteryhma2.annaTiedot(); 
        
       Ostot ostot = new Ostot();
       Osto eka = new Osto();
       Osto toka = new Osto();
  

       eka.annaTiedot(1);
       toka.annaTiedot(2);
       
       ostot.lisaa(eka);
       ostot.lisaa(toka);
       ostot.getLkm();     
       
       System.out.println("=============== testi ===================");
       
       List<Osto> ostot2 = ostot.annaOstot(2);
       
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
