package mitatuliostettua;

import java.io.OutputStream;
import java.io.PrintStream;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Yhdistää kauppareissun ja tuoteryhmät ostoiksi
 * @author elisa
 * @version 19.3.2020
 *
 */
public class Osto implements Cloneable {
    
    private Tuoteryhma tuoteryhma;
    private int kauppaid;
    private int tuoteryhmaid;
    private int maara;
    private int hinta;
    private int tunnusNro;
    private String tuote;
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
     * @param k Minkä kentän sisältö halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     * Osto osto = new Osto();
     * Tuoteryhma tuote = new Tuoteryhma();
     * osto.annaTiedot(1, tuote, 2, 1);
     * osto.anna(0) === "1";
     * osto.anna(2) === "2";
     * osto.anna(3) === "1";
     * </pre>
     */
    public String anna(int k) {
        switch (k) {
            case 0:
                return "" + kauppaid;
            case 1:
                return "" + tuote;
            case 2:
                return "" + maara;
            case 3:
                return "" + hinta;    
            default:
                return "???";
        }
    }
    
    /**
     * Palauttaa vain tuoteryhmän
     * @return ostettu tuoteryhmä
     * </pre>
     */
    public Tuoteryhma getTuoteryhma() {
        
        return tuoteryhma;
    }
    
    
    /**Palauttaa ostettujen määräm
     * @return ostettu tuoteryhmä
     * @example
     * <pre name="test">
     * Kauppareissu kauppareissu = new Kauppareissu();
     * kauppareissu.rekisteroi();
     * kauppareissu.annaTiedot("20.02.2020");
     * Osto eka = new Osto();
     * eka.annaTiedot(1, "juu", 3, 2);
     * eka.getMaara() === 3;
     * </pre>
     */
    public int getMaara() {
        
        return maara;
    }
    
    
    /**
     * @return ostettu tuoteryhmä
     * </pre>
     */
    public int getHinta() {
        
        return hinta;
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
     * @param nro kauppareissun tunnusnumero
     * @param tuotee tuoteryhman nimi, jonka osto lisätään
     * @param maara1 maara
     * @param hinta1 hinta
     */
    public void annaTiedot(int nro, String tuotee, int maara1, int hinta1 ) {
        
        tuote = tuotee;
        kauppaid = nro;
        maara = maara1;
        hinta = hinta1;   
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
    
    
    /**
     * @param tunnus kauppaid
     * @param tuoter tuote
     * @param maaraa maara
     * @param hintaa hinta
     */
    public void annaTiedot(int tunnus, Tuoteryhma tuoter, int maaraa, int hintaa) {
        
        tuote = tuoter.getNimi();
        tuoteryhmaid = tuoter.getTunnus();
        tuoteryhma = tuoter;
        kauppaid = tunnus;
        maara = maaraa;
        hinta = hintaa;
    }
    
    
    /**Liittää oston seuraavan tunnusnumeron
     * @return oston tunnusnumero
     * @example
     * <pre name="test">
     * Osto eka = new Osto();
     * Osto toka = new Osto();
     * eka.getTunnus() === 0;
     * eka.rekisteroi();
     * eka.rekisteroi();
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
     * <pre name="test">
     * Kauppareissu kauppareissu = new Kauppareissu();
     * kauppareissu.rekisteroi();
     * kauppareissu.annaTiedot("20.02.2020");
     * Tuoteryhma tuote = new Tuoteryhma();
     * tuote.rekisteroi();
     * Osto eka = new Osto();
     * eka.annaTiedot(1, tuote, 3, 2);
     * eka.getTunnus() === 0;
     * </pre>
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
    *   Tuoteryhma tuote = new Tuoteryhma();
    *   Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
    *   tuoteryhmat.lisaa(tuote);
    *   Osto osto = new Osto();
    *   osto.annaTiedot(1, tuote, 5, 3);
    *   osto.toString()    === "0|0|1|null|5|3";
    * </pre>
    */
   @Override
   public String toString() {
       return "" + getTunnus() + "|" + tuoteryhmaid + "|" + kauppaid + "|" + tuote + "|" + maara + "|" + hinta;

   }

   
    /**
     * @param rivi rivi, jolta tiedot luetaan
     * @param tuoteryhmat tuoteryhmat
     * @example
     * <pre name="test">
     * Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
     * Osto osto = new Osto();
     * osto.rekisteroi();
     * osto.parse("0|0|1|null|5|3", tuoteryhmat);
     * osto.getMaara() === 5;
     * osto.getHinta() === 3;
     * Osto rissu = new Osto();
     * rissu.rekisteroi();
     * rissu.parse("1|2|1|null|4|5", tuoteryhmat);
     * rissu.getHinta() === 5;
     * rissu.getMaara() === 4
     * rissu.getTunnus() === 1;
     * </pre>
     */
    public void parse(String rivi, Tuoteryhmat tuoteryhmat) {
           StringBuffer sb = new StringBuffer(rivi);
           setTunnus(Mjonot.erota(sb, '|', getTunnus()));
           tuoteryhmaid = Mjonot.erota(sb, '|', tuoteryhmaid);
           kauppaid = Mjonot.erota(sb, '|', kauppaid);
           tuote = Mjonot.erota(sb, '|', tuote);
           maara = Mjonot.erota(sb, '|', maara);
           hinta = Mjonot.erota(sb, '|', hinta);
           tuoteryhma = annaRyhma(tuoteryhmat);
       }
    
    
    /**Annetaan jokin tuoteryhmä
     * @param tuoteryhmat tuoteryhmät
     * @return tuote
     */
    public Tuoteryhma annaRyhma(Tuoteryhmat tuoteryhmat) {
        Tuoteryhma tuote1 = new Tuoteryhma();
        for(Tuoteryhma tuoter : tuoteryhmat) {
            if(tuoter.getTunnus() == tuoteryhmaid) tuote1 = tuoter;
        }
        return tuote1;
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
        ruoka.annaTiedot("t");
        juoma.annaTiedot("f");
        
        
        Osto osto = new Osto(eka.getTunnus(), ruoka);
        Osto osto2 = new Osto(toka.getTunnus(), juoma);
        
        osto.annaTiedot(1, ruoka.getNimi());
        osto2.annaTiedot(2, juoma.getNimi());
        
        osto.tulosta(System.out);
        osto2.tulosta(System.out);
    }

    
    /**Annetaan kauppareissun tunnus
     * @return kauppareissun tunnusnumeron
     */
    public int getKaupTunnus() {
        return kauppaid;
    }


    /** Annetaan kenttien lukumäärä
     * @return kenttien määrä
     */
    public int getKenttia() {
        return 4;
    }
  

    /**Annetaan ensimmäinen kenttä, johon tulee jotakin
     * @return ensimmäinen kenttä
     */
    public int ekaKentta() {
        return 1;
    }


   
    /** Asetetaan tiedot
     * @param k mitkä tiedot halutn asettaa
     * @param jono tiedot jonossa
     * @return null
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setTunnus(Mjonot.erota(sb, '§', getTunnus()));
            return null;
        case 1:
            tuote = tjono;
            return null;
        case 2:
            String maarassanana = tjono;
            maaraksi(maarassanana);
            return null;
        case 3:
            String hintasanana = tjono;
            hinnaksi(hintasanana);
            return null;
        default:
            return "ei mikään";
        }
    }


    private void hinnaksi(String hintasanana) {
        hinta = Integer.parseInt(hintasanana);
        
    }


    private void maaraksi(String maarasana) {
        maara = Integer.parseInt(maarasana);
        
    }        
}
