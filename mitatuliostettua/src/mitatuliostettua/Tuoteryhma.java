package mitatuliostettua;

import java.io.OutputStream;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
    String[] vaihtoehtoja = { "alkoholi", "elektroniikka", "hygienia" };
    
     
    /**
     * @return tuoteryhman nimi
     * @example
     * <pre name="test">
     *  Tuoteryhma ruoka  = new Tuoteryhma();
     *  ruoka.annaTiedot();
     *  ruoka.getTuoteryhma() === "alkoholi";
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
     * #import mitatuliostettua.Tuoteryhma;
     * #import static mitatuliostettua.Tuoteryhma.*;
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
    
    
    private void setTunnus(int numero) {
        tunnusNro = numero;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;     
    }
    
    
    /**
     * Palauttaa tuoteryhman tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return tuoteryhma tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   #STATICIMPORT
     *   Tuoteryhma tuoteryhma = new Tuoteryhma();
     *   tuoteryhma.parse("   1  |  kukka");
     *   tuoteryhma.toString().startsWith("1|kukka") === true;
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getTunnus() + "|" +
                tuoteryhmanNimi;
    }
    
    
    /**Palauttaa tuotteen tunnusnumeron
     * @return tunnusnumero
     */
    public int getTunnus() {
        return tunnusNro;
    }
    
    
    /**
     * Antaa väliaikaisesti luoduille tuoteryhmille tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     * @param string tuoteryhman nimi
     */
    public void annaTiedot(String string) {
        
       tunnusNro = getTunnus();
       if (string == null) tuoteryhmanNimi = "virhe";
       else tuoteryhmanNimi = string;
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
    
    
    /**Testiohjelma tuoteryhmälle
     * @param args ei käytösssä
     */
    public static void main(String[] args) {

        Tuoteryhma ruoka = new Tuoteryhma();
        Tuoteryhma juoma = new Tuoteryhma();
        ruoka.rekisteroi();
        juoma.rekisteroi();
        ruoka.tulosta(System.out);
        juoma.tulosta(System.out);
        ruoka.annaTiedot("moi");
        juoma.annaTiedot("hei");
        ruoka.tulosta(System.out);
        juoma.tulosta(System.out);
    }

    
    /**
     * @return tuoteryhmän nimen
     */
    public String getNimi() {
        return this.tuoteryhmanNimi;
        
    }

    /**
     * @param rivi rivi joka parsitaan
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setTunnus(Mjonot.erota(sb, '|', getTunnus()));
        tuoteryhmanNimi = Mjonot.erota(sb, '|', tuoteryhmanNimi);
        
    }

   
}
    
