package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Yhdistää kauppreissun ja tuoteryhmät ostoiksi
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Osto {
    
    private Kauppareissu kauppareissu;
    private Tuoteryhma tuoteryhma;
    private int kauppaid;
    private int tuoteryhmaid;
    // attribuutit
    private int maara;
    private int hinta;
    private int tunnusNro;
    private String tuote;
    // seuraava id
    private static int seuraavaNro = 1;
    
    /**
     */
    public Osto() {
        //
    }
    
    
    /**
     * @param kauppareissu kauppareissu
     * @param tuoteryhma tuoteryhma
     */
    public Osto (Kauppareissu kauppareissu, Tuoteryhma tuoteryhma) {
        this.kauppareissu = kauppareissu;
        this.tuoteryhma = tuoteryhma;
    }
    
    
    /**
     * @param tunnus kauppareissun tunnus
     */
    public Osto(int tunnus) {
        this.kauppaid = tunnus;
        
    }
    
    /**
     * @return ostetun tuoteryhmän nimi
     * </pre>
     */
    public Tuoteryhma getNimi() {
        return tuoteryhma;
    }
    
    /**
     * Antaa väliaikaisesti luoduille ostoille tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     * @param nro tunnusnumero
     */
    public void annaTiedot(int nro) {
        
        tuote = "tuote";
        kauppaid = nro;
        maara = 5;
        hinta = 30;
       
        
        
    }
    
    
    /**Liittää oston seuraavan tunnusnumeron
     * @return oston tunnusnumero
     * @example
     * <pre name="test">
     * osto eka = new Osto();
     * eka.getTunnus() === 0;
     * eka.rekisteroi();
     * eka.rekisteroi();
     * Osto toka = new Osto();
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
    
    
    /**Palauttaa oston tunnusnumeron
     * @return tunnusnumero
     */
    public int getTunnus() {
        return tunnusNro; 
    }
    
    
    /**
     * Tulostaa tuoteryhman
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        
        
        out.println("Osto: " + tuote + " hinta: " + hinta + " lukumäärä: " + maara);
        
    }
    
    
    /**
     * Tulostetaan tuoteryhman tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
        
    }
    

    /**Asetetaan ostolle määrä
     * @param maara tuoteryhmän ostettujen tuotteiden määrä
     */
    public void setMaara(int maara) {
        this.maara = maara;
    }
    
    
    /**Asetetaan tuoteryhmän ostolle hinta
     * @param hinta tuotteiden hinta
     */
    public void setHinta(int hinta) {
        this.hinta = hinta;
        
    }
    

    /**Hetaan tuoteryhmän nimi Tuoteryhmä-luokasta
     * @param nimi tuoteryhmän nimi
     */
    public void setNimi(Tuoteryhma nimi) {
         
        this.tuote = nimi.getNimi();
    }
    

    /**testiohjelma ostolle
     * @param args eik käytössä
     */
    public static void main(String[] args) {
        
        Kauppareissu eka = new Kauppareissu();
        eka.rekisteroi();
        Kauppareissu toka = new Kauppareissu();
        toka.rekisteroi();
        
        Tuoteryhma ruoka = new Tuoteryhma();
        Tuoteryhma juoma = new Tuoteryhma();
        ruoka.rekisteroi();
        juoma.rekisteroi();
        ruoka.annaTiedot();
        juoma.annaTiedot();
        
        
        Osto osto = new Osto(eka, ruoka);
        Osto osto2 = new Osto(toka, juoma);
        
        osto.annaTiedot(1);
        osto2.annaTiedot(2);
        
       
        

        osto.tulosta(System.out);
        osto2.tulosta(System.out);

    }


    public int getKaupTunnus() {
        return tunnusNro;
    }
  

}
