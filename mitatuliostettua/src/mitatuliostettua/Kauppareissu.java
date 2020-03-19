package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author elisa
 * @version 17.3.2020
 * Tietää kauppareissun kentät
 * Tunnusnumeron ylläpito
 */
public class Kauppareissu {

    private int tunnusNro = 0;
    private String paivamaara = "";    
    private static int seuraavaNro = 1;
    
    
    /**
     * @return kauppareissun päivämäärä
     * @example
     * <pre name="test">
     *   Kauppareissu eka = new Kauppareissu();
     *   eka.annaTiedot();
     *   eka.getPvm() === "20.01.2018";
     * </pre>
     */
    public String getPvm() {
        return paivamaara;
    }
    

    /**Liittää kauppareissuun seuraavan tunnusnumeron
     * @return kauppareissun tunnusnumero
     * @example
     * <pre name="test">
     * Kauppareissu eka = new Kauppareissu();
     * eka.getTunnus() === 0;
     * eka.rekisteroi();
     * eka.rekisteroi();
     * Kauppareissu toka = new Kauppareissu();
     * toka.rekisteroi();
     * int tunnus1 = eka.getTunnus();
     * int tunnus2 = toka.getTunnus();
     * tunnus1 === tunnus2-1;
     * </pre>
     */
    public int rekisteroi() {
        if (tunnusNro != 0) return tunnusNro;
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
        paivamaara = "20.01.2018";
        
    }
    
    
    /**
     * Tulostaa kauppareissun
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        
        out.println(paivamaara + " " + tunnusNro);
    }
    
    
    /**
     * Tulostetaan kauppareissun tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    /**
     * Testiohjelma kauppareissulle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kauppareissu eka = new Kauppareissu();
        Kauppareissu toka = new Kauppareissu();
        eka.rekisteroi();
        toka.rekisteroi();
        
        eka.tulosta(System.out);
        
        
        eka.annaTiedot();
        eka.tulosta(System.out);

        toka.annaTiedot();
        toka.tulosta(System.out);
    
    }  
}
