package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Yhdistää kauppreissun ja tuoteryhmät ostoiksi
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Osto {
    
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
     * @param kauppaid kauppareissu
     * @param tuoteryhma tuoteryhma
     */
    public Osto (int kauppaid, Tuoteryhma tuoteryhma) {
        this.kauppaid = kauppaid;
        this.tuoteryhma = tuoteryhma;
    }
    
    
    /**
     * @param tunnus kauppareissun tunnus
     */
    public Osto(int tunnus) {
        this.kauppaid = tunnus;
        
    }
    
    /**
     * @return ostettu tuoteryhmä
     * </pre>
     */
    public Tuoteryhma getTuoteryhma() {
        return tuoteryhma;
    }
    
    /**
     * Antaa väliaikaisesti luoduille ostoille tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     * @param nro tunnusnumero
     * @param tuotee tuoteryhman nimi, jonka osto lisätään
     */
    public void annaTiedot(int nro, String tuotee ) {
        
        tuote = tuotee;
        kauppaid = nro;
        maara = 5;
        hinta = 30;
       
        
        
    }
    
    /**
     * Antaa väliaikaisesti luoduille ostoille tiedot kun niitä ei vielä oikeasti pysty kirjoittamaan
     * @param nro tunnusnumero
     */
    public void annaTiedot(int nro) {
        
        tuote = tuoteryhma.getNimi();
        tuoteryhmaid = tuoteryhma.getTunnus();
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
    
    
    /**
    * Asettaa tunnusnumeron ja samalla varmistaa että
    * seuraava numero on aina suurempi kuin tähän mennessä suurin.
    * @param nr asetettava tunnusnumero
    */
   private void setTunnus(int tunnusnumero) {
       tunnusNro = tunnusnumero;
       if ( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
   }

    
   /**
    * Palauttaa oston tiedot merkkijonona jonka voi tallentaa tiedostoon.
    * @return osto tolppaeroteltuna merkkijonona 
    * @example
    * <pre name="test">
    *   Osto osto = new Osto();
    *   osto.parse("   1   |  2  |   alkoholi  | 6 | 10 ");
    *   osto.toString()    === "1|2|alkoholi|6|10";
    * </pre>
    */
   @Override
   public String toString() {
       return "" + getTunnus() + "|" + kauppaid + "|" + tuoteryhmaid + "|" + maara + "|" + hinta;
   }

   
    /**
     * @param rivi rivi, jolta tiedot luetaan
     */
    public void parse(String rivi) {
           StringBuffer sb = new StringBuffer(rivi);
           setTunnus(Mjonot.erota(sb, '|', getTunnus()));
           kauppaid = Mjonot.erota(sb, '|', kauppaid);
           tuote = Mjonot.erota(sb, '|', tuote);
           maara = Mjonot.erota(sb, '|', maara);
           hinta = Mjonot.erota(sb, '|', hinta);
       }
    
    
    @Override
    public boolean equals(Object obj) {
        if ( obj == null ) return false;
        return this.toString().equals(obj.toString());
    }
    
    
    @Override
    public int hashCode() {
        return tunnusNro;
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
        
        
        Osto osto = new Osto(eka.getTunnus(), ruoka);
        Osto osto2 = new Osto(toka.getTunnus(), juoma);
        
        osto.annaTiedot(1, ruoka.getNimi());
        osto2.annaTiedot(2, juoma.getNimi());
        
       
        

        osto.tulosta(System.out);
        osto2.tulosta(System.out);

    }


    public int getKaupTunnus() {
        return kauppaid;
    }
  

}
