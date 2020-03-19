package mitatuliostettua;

/**
 * Mtatuliostettua-luokka, joka huolehtii Tuoteryhmät ja Kauppareissut - luokista
 * @author elisa
 * @version 18.3.2020
 *
 */
public class Mitatuliostettua {

    private final Kauppareissut kauppareissut = new Kauppareissut();
    private final Ostot ostot = new Ostot();
    
    /**
     * Palauttaa kauppareissujen lukumäärän
     * @return kauppareissujen lukumäärä
     */
    public int getMaara() {
        return kauppareissut.getLkm();
    }
    
    
    /**
     * Palauttaa ostoje lukumäärän
     * @return ostojen lukumäärä
     */
    public int getOstot() {
        return ostot.getLkm();
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
     * @throws SailoException os lisäystä ei voida tehdä tai indeksi menee muuten yli
     */
    public void lisaaTuote(Osto osto) throws SailoException {
        ostot.lisaa(osto);   
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
     * @param i oston indeksi
     * @return viite i:nteen ostoon
     */
    public Osto annaOsto(int i) { 
        return ostot.annaViite(i);
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
     * Lukee mitatuliostettua:n tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kauppareissut.lueTiedostosta(nimi);
    }


    /**
     * Tallettaa kauppareissun tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        kauppareissut.tallennaReissu();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }
    
    
    /**
    * @param kauppareissu kauppareissu
    * @param tuoteryhma tuoteryhma
    * @return osto, jossa kauppareissun ja tuoteryhman id
    */
   public Osto luoOsto(Kauppareissu kauppareissu, Tuoteryhma tuoteryhma) {
       return new Osto(kauppareissu.getTunnus(),tuoteryhma.getTunnus());
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
            mitatuliostettua.poista(0); //tämä ei toimi vielä
            
            System.out.println("============= testi =================");

            for (int i = 0; i < mitatuliostettua.getMaara(); i++) {
                Kauppareissu kauppareissu = mitatuliostettua.annaKauppareissu(i);
                System.out.println("Jäsen paikassa: " + i);
                kauppareissu.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}


