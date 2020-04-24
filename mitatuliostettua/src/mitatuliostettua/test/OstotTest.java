package mitatuliostettua.test;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import mitatuliostettua.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.24 15:13:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OstotTest {



  // Generated by ComTest BEGIN
  /** testLisaa93 */
  @Test
  public void testLisaa93() {    // Ostot: 93
    Ostot ostot = new Ostot(); 
    Osto eka = new Osto(); 
    ostot.lisaa(eka); 
    assertEquals("From: Ostot line: 97", 1, ostot.getLkm()); 
    Osto toka = new Osto(); 
    ostot.lisaa(toka); 
    assertEquals("From: Ostot line: 100", 2, ostot.getLkm()); 
    Osto juu = new Osto(); 
    ostot.lisaa(juu); 
    assertEquals("From: Ostot line: 103", 3, ostot.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaOstot231 */
  @Test
  public void testAnnaOstot231() {    // Ostot: 231
    Ostot ostot = new Ostot(); 
    Osto eka = new Osto(2); ostot.lisaa(eka); 
    Osto toka = new Osto(1); ostot.lisaa(toka); 
    Osto kolmas = new Osto(2); ostot.lisaa(kolmas); 
    Osto neljas = new Osto(1); ostot.lisaa(neljas); 
    Osto viides = new Osto(2); ostot.lisaa(viides); 
    Osto kuudes = new Osto(5); ostot.lisaa(kuudes); 
    List<Osto> loytyneet; 
    loytyneet = ostot.annaOstot(3); 
    assertEquals("From: Ostot line: 244", 0, loytyneet.size()); 
    loytyneet = ostot.annaOstot(1); 
    assertEquals("From: Ostot line: 246", 2, loytyneet.size()); 
    assertEquals("From: Ostot line: 247", true, loytyneet.get(0) == toka); 
    assertEquals("From: Ostot line: 248", true, loytyneet.get(1) == neljas); 
    loytyneet = ostot.annaOstot(5); 
    assertEquals("From: Ostot line: 250", 1, loytyneet.size()); 
    assertEquals("From: Ostot line: 251", true, loytyneet.get(0) == kuudes); 
  } // Generated by ComTest END
}