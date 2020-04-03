package mitatuliostettua;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Mtatuliostettua-luokka, joka huolehtii Tuoteryhmät ja Kauppareissut - luokista
 * @author elisa
 * @version 18.3.2020
 *
 */
public class Mitatuliostettua {

    private Kauppareissut kauppareissut = new Kauppareissut();
    private Ostot ostot = new Ostot();
    private final Tuoteryhmat tuoteryhmat = new Tuoteryhmat();
    
    /**
     * Palauttaa kauppareissujen lukumäärän
     * @return kauppareissujen lukumäärä
     */
    public int getMaara() {
        return kauppareissut.getLkm();
    }
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien jäsenten viitteet 
     * @param ehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä jäsenistä 
     */ 
    public Collection<Kauppareissu> etsi(String ehto, int k)  { 
        return kauppareissut.etsi(ehto, k); 
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
    
    
    
    /**
     * @param osto osto
     */
    public void lisaaOsto(Osto osto) {
        ostot.lisaa(osto);   
    }
    
    
  

    
    
    public void lisaaTuoteryhma(Tuoteryhma tuoteryhma) {
        tuoteryhmat.lisaa(tuoteryhma);
        
    }
    

    /**
     * Tulee poistamaan muista luokista kaikki ne alkiot, joilla on viitenumero nro
     * @param nro viitenumero
     * @return poistettujen jäsenten määrä
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
        
    }
    
    
    /**
     * Palauttaa i:n oston
     * @param kauppareissu kauppareissu jonka ostot halutaan
     * @return viite i:nteen ostoon
     * @throws SailoException v
     */
    public List<Osto> annaOstot(Kauppareissu kauppareissu) throws SailoException{ 
        
        return ostot.annaOstot(kauppareissu.getTunnus());
    }
    
    
    /**
     * Palauttaa i:n kauppareissun
     * @param i kauppareissun indeksi
     * @return viite i:nteen kauppareissuun
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
        ostot.lueTiedostosta(nimi);
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
        if ( !"".equals(virhe) ) throw new SailoException(virhe);

    }
    
    
   
   
    /**Testohjelma Mitatuliostettua-luokalle
     * @param args ei käytössä
     * 
     *    
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










  
}


