package mitatuliostettua.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.*;
import mitatuliostettua.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.30 15:42:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KauppareissutTest {



  // Generated by ComTest BEGIN
  /** testLisaa44 */
  @Test
  public void testLisaa44() {    // Kauppareissut: 44
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu eka = new Kauppareissu(); 
    kauppareissut.lisaa(eka); 
    assertEquals("From: Kauppareissut line: 48", 1, kauppareissut.getLkm()); 
    Kauppareissu toka= new Kauppareissu(); 
    kauppareissut.lisaa(toka); 
    assertEquals("From: Kauppareissut line: 51", 2, kauppareissut.getLkm()); 
    assertEquals("From: Kauppareissut line: 52", eka, kauppareissut.annaViite(0)); 
    assertEquals("From: Kauppareissut line: 53", toka, kauppareissut.annaViite(1)); 
    Kauppareissu juu = new Kauppareissu(); 
    kauppareissut.lisaa(juu); 
    assertEquals("From: Kauppareissut line: 56", 3, kauppareissut.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLkm105 */
  @Test
  public void testGetLkm105() {    // Kauppareissut: 105
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu eka = new Kauppareissu(); 
    kauppareissut.lisaa(eka); 
    assertEquals("From: Kauppareissut line: 109", 1, kauppareissut.getLkm()); 
    Kauppareissu toka= new Kauppareissu(); 
    kauppareissut.lisaa(toka); 
    assertEquals("From: Kauppareissut line: 112", 2, kauppareissut.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMuokkaa124 */
  @Test
  public void testMuokkaa124() {    // Kauppareissut: 124
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu kauppareissu = new Kauppareissu(); 
    kauppareissu.rekisteroi(); 
    kauppareissu.annaTiedot("20.03.2020"); 
    kauppareissut.lisaa(kauppareissu); 
    assertEquals("From: Kauppareissut line: 130", "20.03.2020", kauppareissu.getPvm()); 
    kauppareissut.muokkaa(kauppareissu, "11.03.2020"); 
    assertEquals("From: Kauppareissut line: 132", "11.03.2020", kauppareissu.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta146 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta146() throws SailoException {    // Kauppareissut: 146
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu reissu1 = new Kauppareissu(), reissu2 = new Kauppareissu(); 
    reissu1.rekisteroi(); 
    reissu2.rekisteroi(); 
    reissu1.annaTiedot("paiva1"); 
    reissu2.annaTiedot("paiva2"); 
    String hakemisto = "testikauppareissut1"; 
    String tiedNimi = hakemisto+"/reissut1"; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    kauppareissut.lueTiedostosta(tiedNimi); 
    kauppareissut.lisaa(reissu1); 
    kauppareissut.lisaa(reissu2); 
    kauppareissut.tallennaReissu(); 
    kauppareissut = new Kauppareissut();  // Poistetaan vanhat luomalla uusi
    kauppareissut.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
    Iterator<Kauppareissu> i = kauppareissut.iterator(); 
    assertEquals("From: Kauppareissut line: 169", reissu1.toString(), i.next().toString()); 
    assertEquals("From: Kauppareissut line: 170", reissu2.toString(), i.next().toString()); 
    kauppareissut.lisaa(reissu2); 
    assertEquals("From: Kauppareissut line: 172", false, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Kauppareissut line: 174", true, fbak.delete()); 
    assertEquals("From: Kauppareissut line: 175", false, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi356 
   * @throws SailoException when error
   */
  @Test
  public void testEtsi356() throws SailoException {    // Kauppareissut: 356
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu kauppareissu1 = new Kauppareissu(); kauppareissu1.parse("1|20.03.2020|"); 
    Kauppareissu kauppareissu2 = new Kauppareissu(); kauppareissu2.parse("2|20.04.2020|"); 
    Kauppareissu kauppareissu3 = new Kauppareissu(); kauppareissu3.parse("3|20.05.2020|"); ; 
    kauppareissut.lisaa(kauppareissu1); kauppareissut.lisaa(kauppareissu2); kauppareissut.lisaa(kauppareissu3); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId401 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiId401() throws SailoException {    // Kauppareissut: 401
    Kauppareissut kauppareissut = new Kauppareissut(); 
    Kauppareissu eka = new Kauppareissu(), toka = new Kauppareissu(), kolmas = new Kauppareissu(); 
    eka.rekisteroi(); toka.rekisteroi(); kolmas.rekisteroi(); 
    int id1 = eka.getTunnus(); 
    kauppareissut.lisaa(eka); kauppareissut.lisaa(toka); kauppareissut.lisaa(kolmas); 
    assertEquals("From: Kauppareissut line: 408", 1, kauppareissut.etsiId(id1+1)); 
    assertEquals("From: Kauppareissut line: 409", 2, kauppareissut.etsiId(id1+2)); 
  } // Generated by ComTest END
}