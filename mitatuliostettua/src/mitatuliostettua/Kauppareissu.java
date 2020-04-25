package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author elisa
 * @version 17.3.2020
 * Tietää kauppareissun kentät
 * Tunnusnumeron ylläpito
 */
public class Kauppareissu implements Cloneable{

    private int tunnusNro = 0;
    private String paivamaara = "";    
    private static int seuraavaNro = 1;
    
    
    /**
     * @return kauppareissun päivämäärä
     * @example
     * <pre name="test">
     *   Kauppareissu eka = new Kauppareissu();
     *   eka.annaTiedot("20.01.2018");
     *   eka.getPvm() === "20.01.2018";
     *   Kauppareissu toka = new Kauppareissu();
     *   toka.annaTiedot("15.03.2020");
     *   toka.getPvm() === "15.03.2020";
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
     * Kauppareissu eka = new Kauppareissu();
     * eka.getTunnus() === 0;
     * @return tunnusnumero
     */
    public int getTunnus() {
        
        return tunnusNro; 
    }
    
    
    /**
     * Antaa väliaikaisesti luoduille kauppareissujen tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     */
    public void annaTiedot() {
        
        paivamaara = "Päivämäärä pitää valita";
        
    }
    
    /**
     * Antaa kauppareissun tiedot
     * @param paiva kauppareissun päivämäärä
     */
    public void annaTiedot(String paiva) {
        
        paivamaara = paiva;
        
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
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     * @example
     * <pre name="test">
     * Kauppareissu eka = new Kauppareissu();
     * eka.getTunnus() === 0;
     * eka.setTunnusNro(2);
     * eka.getTunnus() === 2;
     * </pre>
     */
    public void setTunnusNro(int nr) {
        
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    

    /**
     * Palauttaa kauppareissub tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kauppareissu tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     * Kauppareissu kauppareissu = new Kauppareissu();
     * kauppareissu.annaTiedot("20.03.2020");
     * kauppareissu.toString() === "0|20.03.2020";
     * Kauppareissu rissu = new Kauppareissu();
     * rissu.annaTiedot("06.05.2019");
     * rissu.toString() === "0|06.05.2019";
     * </pre>
     */
    @Override
    public String toString() {
        
        return "" +
                getTunnus() + "|" +
                paivamaara;
                
    }


    /**
     * Selvitää kauppareissun tiedot | erotellusta merkkijonosta
     * @param rivi josta kauppareissun tiedot otetaan
     * @example
     * <pre name="test">
     * Kauppareissu kauppareissu = new Kauppareissu();
     * kauppareissu.rekisteroi();
     * kauppareissu.parse("1|06.05.2019");
     * kauppareissu.getPvm() === "06.05.2019";
     * kauppareissu.getTunnus() === 1;
     * Kauppareissu rissu = new Kauppareissu();
     * rissu.rekisteroi();
     * rissu.parse("0|20.03.2020");
     * rissu.getPvm() === "20.03.2020";
     * rissu.getTunnus() === 0;
     * </pre>
     */
    public void parse(String rivi) {
        
        StringBuffer sb = new StringBuffer(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnus()));
        paivamaara = Mjonot.erota(sb, '|', paivamaara);
    }
    


    @Override
    public int hashCode() {
        
        return tunnusNro;
    }


    /**
     * Tehdään klooni kauppareissusta
     * @return kloonattu kauppareissu
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kauppareissu kauppareissu = new Kauppareissu();
     *   kauppareissu.parse("0|10.02.2012");
     *   Kauppareissu kopio = kauppareissu.clone();
     *   Object olio = kauppareissu.clone();
     *   kopio.toString() === kauppareissu.toString();
     *   kauppareissu.parse("4|03.12.2020");
     *   kopio.toString().equals(kauppareissu.toString()) === false;
     *   olio instanceof Kauppareissu === true;
     * </pre>
     */
    @Override
    public Kauppareissu clone() throws CloneNotSupportedException {
        Kauppareissu uusiKauppareissu;
        uusiKauppareissu = (Kauppareissu)super.clone();
        return uusiKauppareissu;
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
