package mitatuliostettua;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


/**
 * Mtatuliostettua-luokka, joka huolehtii Tuoteryhmät ja Kauppareissut - luokista
 * @author elisa
 * @version 18.3.2020
 */
public class Mitatuliostettua {

    private Kauppareissut kauppareissut = new Kauppareissut();
    private Ostot ostot = new Ostot();
    private final Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
    
    
    /**
     * Palauttaa kauppareissujen lukumäärän
     * @return kauppareissujen lukumäärä
     * @example
     * <pre name="test">
     * #import mitatuliostettua.SailoException;
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu eka = new Kauppareissu();
     * try {
     *      mitatuliostettua.lisaa(eka);
     * } catch (SailoException e) {
     *      e.printStackTrace();
     *      } 
     * eka.rekisteroi();
     * eka.annaTiedot("20.03.2020");
     * kauppareissut.lisaa(eka);
     * mitatuliostettua.getMaara() === 1;
     * </pre>
     */
    public int getMaara() {
        return kauppareissut.getLkm();
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kauppreissun viitteet 
     * @param ehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @param localDate päiva, jolla voidaan etsiä
     * @return tietorakenteen löytyneistä kauppareissuista 
     */ 
    public Collection<Kauppareissu> etsi(String ehto, int k, LocalDate localDate)  { 
        Collection<Kauppareissu> kaikkiKauppareissut = kauppareissut.etsi(ehto, k, localDate);
        if (ehto.length() < 1) return kaikkiKauppareissut;
            
        Kauppareissu[] kauppareissui = kauppareissut.etsiOikeat();
        kaikkiKauppareissut = ostot.etsiSopivat(kauppareissui, ehto);
        
        return kaikkiKauppareissut; 
        
    } 
    
    
    /** Lisää uuden kauppareissun
     * @param kauppareissu kauppareissu, joka lisätään
     * @throws SailoException jos lisäystä ei voida tehdä tai indeksi menee muuten yli
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Kauppareissu eka = new Kauppareissu();
     * Kauppareissu toka = new Kauppareissu();
     * mitatuliostettua.getMaara() === 0;
     * mitatuliostettua.lisaa(eka); 
     * mitatuliostettua.getMaara() === 1;
     * mitatuliostettua.lisaa(toka); 
     * mitatuliostettua.getMaara() === 2;
     * mitatuliostettua.annaKauppareissu(0) === eka;
     * mitatuliostettua.annaKauppareissu(1) === toka;
     * mitatuliostettua.annaKauppareissu(2) === toka; #THROWS IndexOutOfBoundsException 
     * </pre>
     */
    public void lisaa(Kauppareissu kauppareissu) throws SailoException {
        kauppareissut.lisaa(kauppareissu);   
    }
    
    
  
    /**Korvaa kauppareissun tietorakenteessa
     * @param kauppareissu kauppareissu jota korvataan tai lisätään
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Kauppareissut kauppareissut = new Kauppareissut();
     * Kauppareissu eka = new Kauppareissu();
     * kauppareissut.lisaa(eka);
     * mitatuliostettua.korvaaTaiLisaa(eka);
     * mitatuliostettua.getMaara() === 1;
     * Kauppareissu toka = new Kauppareissu();
     * kauppareissut.lisaa(toka);
     * mitatuliostettua.korvaaTaiLisaa(toka);
     * mitatuliostettua.getMaara() === 1;
     * </pre>
     */
    public void korvaaTaiLisaa(Kauppareissu kauppareissu) throws SailoException { 
        kauppareissut.korvaaTaiLisaa(kauppareissu); 
    } 

    
    
    /** Lisataan uusi tuoteryhma
     * @param tuoteryhma lisättävä tuoteryhmä
     * @example
     * <pre name="test">
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Tuoteryhma eka = new Tuoteryhma();
     * Tuoteryhma toka = new Tuoteryhma();
     * mitatuliostettua.lisaa(eka);
     * Tuoteryhma[] alkiot = new Tuoteryhma[20];
     * alkiot[0] = eka;
     * alkiot[1] = toka;
     * mitatuliostettua.lisaa(toka);
     * mitatuliostettua.getTuoteryhmat() === alkiot; 
     * </pre>
     */
    public void lisaa(Tuoteryhma tuoteryhma) {
        tuoteryhmat.lisaa(tuoteryhma);
    }
    
    
    /**Lisätään uusi osto
     * @param osto lisättävä osto
     * <pre name="test">
     * #import java.util.ArrayList;
     * #import java.util.List;
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Osto eka = new Osto();
     * Osto toka = new Osto();
     * mitatuliostettua.lisaaOsto(eka);
     * List<Osto> loydetyt = new ArrayList<Osto>();
     * loydetyt.add(eka);
     * loydetyt.add(toka);
     * mitatuliostettua.lisaaOsto(toka);
     * mitatuliostettua.annaKaikkiOstot() === loydetyt; 
     * </pre>
     */
    public void lisaaOsto(Osto osto) {
        ostot.lisaa(osto);   
    }
    
    
    /** Antaa kaikki lisätyt ostot
     * @return kaikki ostot
     * <pre name="test">
     * #import java.util.ArrayList;
     * #import java.util.List;
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Osto eka = new Osto();
     * Osto toka = new Osto();
     * mitatuliostettua.lisaaOsto(eka);
     * List<Osto> loydetyt = new ArrayList<Osto>();
     * loydetyt.add(eka);
     * loydetyt.add(toka);
     * mitatuliostettua.lisaaOsto(toka);
     * mitatuliostettua.annaKaikkiOstot() === loydetyt; 
     * </pre>
     */
    public Collection<Osto> annaKaikkiOstot() {
        return ostot.annaKaikkiOstot();
    }
    
    
    /**
     * Palauttaa i:n oston
     * @param kauppareissu kauppareissu jonka ostot halutaan
     * @return viite i:nteen ostoon
     * @throws SailoException jos ei onnistu
     */
    public List<Osto> annaOstot(Kauppareissu kauppareissu) throws SailoException{ 
        
        return ostot.annaOstot(kauppareissu.getTunnus());
    }
    
    
    /**
     * Palauttaa i:n kauppareissun
     * @param i kauppareissun indeksi
     * @return kauppareissut
     */
    public Kauppareissu annaKauppareissu(int i) { 
        return kauppareissut.annaViite(i);
    }
    
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        kauppareissut.setTiedostonNimi(hakemistonNimi + "kauppareissut");
        ostot.setTiedostonNimi(hakemistonNimi + "ostot");
    }

    
    /**
     * Lukee mitatuliostettua:n tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        
        kauppareissut = new Kauppareissut(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        ostot = new Ostot();
        setTiedosto(nimi);
        kauppareissut.lueTiedostosta(nimi);
        tuoteryhmat.lueTiedostosta(nimi);
        ostot.lueTiedostosta(tuoteryhmat);
        
    }


    /**
     * Tallettaa kauppareissun tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        String virhe = "";
        try {
            kauppareissut.tallennaReissu();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            ostot.tallennaOsto();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        try {
            tuoteryhmat.tallennaTuoteryhma();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        
        if ( !"".equals(virhe) ) throw new SailoException(virhe);

    }
    
    /**
     * Palauttaa k:tta oston kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Tuoteryhma";
        case 1: return "hinta";
        case 2: return "lukumäärä";
        default: return "Äääliö";
        }
    }

    
    /**Muokataan kauppareissun päivämäärää
     * @param valittuKauppareissu kauppareissu, jonka päivää muokataan
     * @param pvm päivämäärä joksi vaihdetaan
     */
    public void muokkaa(Kauppareissu valittuKauppareissu, String pvm) {
       kauppareissut.muokkaa(valittuKauppareissu, pvm);
        
    }


    /**Muutetaan ostoja
     * @param ostot2 uudet ostot
     * @param tunnus kauppareissun tunnus
     */
    public void muokkaaOstoja(Ostot ostot2, int tunnus) {
        ostot.muokkaa(ostot2, tunnus);
        
    }



    /**Haetan ostot
     * @param tunnus kauppareissun tunnus, jonka ostot annetaan
     * @return ostot
     */
    public Ostot getOstot(int tunnus) {
        return ostot.getOstot(tunnus);
    }


    /**Poistetaan kauppareissu ja sen tiedot
     * @param kauppareissu kauppareissu jonka tiedot poistetaan
     * @return numero kun poistettu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Kauppareissu eka = new Kauppareissu();
     * Kauppareissu toka = new Kauppareissu();
     * eka.rekisteroi();
     * toka.rekisteroi();
     * mitatuliostettua.lisaa(eka);
     * mitatuliostettua.lisaa(toka);
     * mitatuliostettua.poista(eka);
     * mitatuliostettua.getMaara() === 1;
     * </pre>
     */
    public int poista(Kauppareissu kauppareissu) {
        if ( kauppareissu == null ) return 0;
        int ret = kauppareissut.poista(kauppareissu.getTunnus()); 
        ostot.poistaKauppareissunTiedot(kauppareissu.getTunnus()); 
        return ret; 
        
    }


    
    /**
     * Palauttaa kaikki tuoteryhmat
     * @return tuoteryhmat
     */
    public Tuoteryhma[] getTuoteryhmat() {
        return tuoteryhmat.getTuoteryhmat();
       
    }


    
    /**
     * Palauttaa kauppareissun ostojen kokonasihinnan
     * @param tunnus kuppareissun tunnus
     * @return kokonaishinta
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
     * Kauppareissu eka = new Kauppareissu();
     * eka.rekisteroi();
     * mitatuliostettua.lisaa(eka);
     * Osto osto = new Osto();
     * osto.annaTiedot(eka.getTunnus(), "tuote", 3, 5);
     * Osto osto2 = new Osto();
     * osto2.annaTiedot(eka.getTunnus(), "tuote2", 6, 7);
     * mitatuliostettua.lisaaOsto(osto);
     * mitatuliostettua.lisaaOsto(osto2);
     * mitatuliostettua.LaskeHinta(eka.getTunnus()) === 12;
     * </pre>
     */
    public int LaskeHinta(int tunnus) {
        return ostot.laskeHinta(tunnus);
    }
    
   
    /**Testohjelma Mitatuliostettua-luokalle
     * @param args ei käytössä  
     */
    public static void main(String[] args) {
        
        Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
        
        try { 
         
            Kauppareissu eka = new Kauppareissu();
            eka.rekisteroi();
            eka.annaTiedot();
            Kauppareissu toka = new Kauppareissu();
            toka.rekisteroi();
            toka.annaTiedot();
            
            mitatuliostettua.lisaa(eka);
            mitatuliostettua.lisaa(toka);
            
            int id1 = eka.getTunnus();
            int id2 = toka.getTunnus();
            
            Osto ekaosto = new Osto(id1); ekaosto.annaTiedot(id1); mitatuliostettua.lisaaOsto(ekaosto);
            Osto tokaosto = new Osto(id1); tokaosto.annaTiedot(id1); mitatuliostettua.lisaaOsto(tokaosto);
            Osto kolmasosto = new Osto(id2); kolmasosto.annaTiedot(id2); mitatuliostettua.lisaaOsto(kolmasosto);
            Osto neljasosto = new Osto(id2); neljasosto.annaTiedot(id2); mitatuliostettua.lisaaOsto(neljasosto);
            Osto viidesosto = new Osto(id2);viidesosto.annaTiedot(id2); mitatuliostettua.lisaaOsto(viidesosto);
            
            
            System.out.println("============= testi =================");

            for (int i = 0; i < mitatuliostettua.getMaara(); i++) {
                Kauppareissu kauppareissu = mitatuliostettua.annaKauppareissu(i);
                System.out.println("Kauppareissu paikassa: " + i);
                kauppareissu.tulosta(System.out);
                List<Osto> loytyneet = mitatuliostettua.annaOstot(kauppareissu);
                for (Osto osto : loytyneet)
                osto.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        } 
    }


    public void lisaaTuoteryhma(Tuoteryhma tuoteryhma) {
        lisaa(tuoteryhma);
        
    }


    
}


