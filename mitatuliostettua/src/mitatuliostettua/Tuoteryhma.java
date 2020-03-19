package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Tietää tuoreyhman kentät
 * Oman tunnusnumeron ylläpito
 * @author elisa
 * @version 19.3.2020
 */
public class Tuoteryhma {

    private int tunnusNro;
    private String tuoteryhmanNimi;
    private static int seuraavaNro    = 1;
    
    
    
    
    /**
     * @return tuoteryhman nimi
     * @example
     * <pre name="test">
     *  Tuoteryhma ruoka  = new Tuoteryhma();
     *  ruoka.annaTiedot();
     *  ruoka.getTuoteryhma() === "ruoka";
     * </pre>
     */
    public String getTuoteryhma() {
        return tuoteryhmanNimi;
    }
    
    /**
     * Antaa tuoteryhmälle seuraavan tunnusnumeron
     *  @return tuoteryhmän uusi tunnusNro
     * @example
     * <pre name="test">
     *   Tuoteryhma ruoka= new Tuoteryhma();
     *   ruoka.getTunnus() === 0;
     *   ruoka.rekisteroi();
     *   Tuoteryhma juoma = new Tuoteryhma();
     *   juoma.rekisteroi();
     *   int tunnus1 = ruoka.getTunnus();
     *   int tunnus2 = juoma.getTunnus();
     *   tunnus1 === tunnus2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**Palauttaa kauppareissun tunnusnumeron
     * @return tunnusnumero
     */
    public int getTunnus() {
        return tunnusNro;
    }
    
    
    /**
     * Antaa väliaikaisesti luoduille kauppareissujen tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     */
    public void annaTiedot() {
        
        tuoteryhmanNimi = "ruoka"; ///tee tähän vielä se satunnaisarpominen
        
    }
    
    
    /**
     * Tulostaa tuoteryhman
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        
        
        out.println("Osto: " + " " + tuoteryhmanNimi + " " + tunnusNro);
        
    }
    
    
    /**
     * Tulostetaan tuoteryhman tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
        
    }
    
    
    public static void main(String[] args) {
        Tuoteryhma ruoka = new Tuoteryhma();
        Tuoteryhma juoma = new Tuoteryhma();
        ruoka.rekisteroi();
        juoma.rekisteroi();
        ruoka.tulosta(System.out);
        juoma.tulosta(System.out);
        ruoka.annaTiedot();
        juoma.annaTiedot();
        ruoka.tulosta(System.out);
        juoma.tulosta(System.out);
    }

    public String getNimi() {
        return this.tuoteryhmanNimi;
        
    }
}
    
