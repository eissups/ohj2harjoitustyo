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
        
        paivamaara = "Päivämäärä pitää valita";
        
    }
    
    /**
     * Antaa väliaikaisesti luoduille kauppareissujen tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
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
     */
    private void setTunnusNro(int nr) {
        
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    

    /**
     * Palauttaa kauppareissub tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kauppareissu tolppaeroteltuna merkkijonona 
     * @example
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
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta kauppareissun tiedot otetaan
     * </pre>
     */
    public void parse(String rivi) {
        
        StringBuffer sb = new StringBuffer(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnus()));
        paivamaara = Mjonot.erota(sb, '|', paivamaara);
    }
    
    
    @Override
    public boolean equals(Object jasen) {
        
        if ( jasen == null ) return false;
        return this.toString().equals(jasen.toString());
    }


    @Override
    public int hashCode() {
        
        return tunnusNro;
    }


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
