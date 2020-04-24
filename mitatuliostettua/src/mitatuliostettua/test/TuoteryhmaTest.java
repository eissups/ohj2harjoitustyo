package mitatuliostettua.test;
// Generated by ComTest BEGIN
import mitatuliostettua.Tuoteryhma;
import static mitatuliostettua.Tuoteryhma.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.03 22:45:44 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TuoteryhmaTest {



  // Generated by ComTest BEGIN
  /** testGetTuoteryhma26 */
  @Test
  public void testGetTuoteryhma26() {    // Tuoteryhma: 26
    Tuoteryhma ruoka  = new Tuoteryhma(); 
    ruoka.annaTiedot(""); 
    assertEquals("From: Tuoteryhma line: 29", "alkoholi", ruoka.getTuoteryhma()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi41 */
  @Test
  public void testRekisteroi41() {    // Tuoteryhma: 41
    Tuoteryhma ruoka= new Tuoteryhma(); 
    assertEquals("From: Tuoteryhma line: 45", 0, ruoka.getTunnus()); 
    ruoka.rekisteroi(); 
    Tuoteryhma juoma = new Tuoteryhma(); 
    juoma.rekisteroi(); 
    int tunnus1 = ruoka.getTunnus(); 
    int tunnus2 = juoma.getTunnus(); 
    assertEquals("From: Tuoteryhma line: 51", tunnus2-1, tunnus1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString71 */
  @Test
  public void testToString71() {    // Tuoteryhma: 71
    Tuoteryhma tuoteryhma = new Tuoteryhma(); 
    tuoteryhma.parse("   1  |  kukka"); 
    assertEquals("From: Tuoteryhma line: 75", true, tuoteryhma.toString().startsWith("1|kukka")); 
  } // Generated by ComTest END
}