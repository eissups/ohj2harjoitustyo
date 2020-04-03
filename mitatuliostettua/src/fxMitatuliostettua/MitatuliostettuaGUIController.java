package fxMitatuliostettua;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import mitatuliostettua.Kauppareissu;
import mitatuliostettua.Mitatuliostettua;
import mitatuliostettua.Osto;
import mitatuliostettua.SailoException;
import mitatuliostettua.Tuoteryhma;




/**
 * Ensin avautuva pääikkuna
 * @author elisa
 * @version 27.1.2020
 * @version 13.2.2020
 */
public class MitatuliostettuaGUIController implements Initializable{
    
    @FXML private MenuItem Menulisaa;
    @FXML private MenuItem Menulopeta;
    @FXML private MenuItem Menupoista;
    @FXML private MenuItem Menuapua;
    @FXML private MenuItem Menutietoja;
    @FXML private TextField texthakukentta;
    @FXML private ComboBoxChooser<?> chooserhakutapavalinta;
    @FXML private DatePicker hakupaiva;
    @FXML private DatePicker datepickertiedot;
    @FXML private Button Buttonlisaauusi;
    @FXML private Button buttonpoista;
    @FXML private Button buottonmuokkaa;
    @FXML private Button Buttonuusituoteryhma;
    @FXML private DatePicker paivamaara;

    @FXML private ListChooser<Kauppareissu> chooserKauppareissut;
    @FXML private ScrollPane panelKauppareissu;
    
 
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    
    
    
    @FXML private void aloitahaku() throws SailoException {
        if ( valittuKauppareissu != null )
            hae(valittuKauppareissu.getTunnus()); 

    }
    

    
    @FXML private void hakupaivavalittu() throws SailoException {
        if ( valittuKauppareissu != null )
            hae(valittuKauppareissu.getTunnus()); 
    }
    
    

    @FXML private void lisaauusi() {
        //Dialogs.showMessageDialog("Lisäys ei vielä toimi");
        try {
            uusiKauppareissu();
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    
    @FXML private void muokkaaostettujaClicked() {
        
        //ModalController.showModal(MitatuliostettuaGUIController.class.getResource("Uusituoteryhma.fxml"), "Uusi tuoteryhmä", null, "");
        Tuoteryhma tuoteryhma = null;
        try {
            tuoteryhma = uusiTuoteryhma();
            uusiOsto(tuoteryhma);
        } catch (SailoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace(); 
        }
        
    }
    
    @FXML public boolean lopeta() {
        tallenna();
        return true;
        
    }
    
    
    
    @FXML private void paivavalittu() {
        tallenna();
    }

    
    
    @FXML private void paivays() {
        Dialogs.showMessageDialog("Järjestettäisiin haetun päivän mukaan, ei toimi vielä");
    }

    
    
    @FXML private void poista() {
        Dialogs.showMessageDialog("Poista, ei toimi vielä");
    }
    
    

    @FXML private void uusituoteryhma() {
        try {
            uusiTuoteryhma();
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    
    
    /**
     * Avataan ohjesivu
     */
    
    @FXML
    private void Apuatarvitaan() {
        Desktop desktop = Desktop.getDesktop();
                 try {
                    URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/autelian");
                    desktop.browse(uri);
                } catch (URISyntaxException e) {
                    return;
                } catch (IOException e) {
                   return;
               }
    }

    
    /**
     * Avataan tietoja ohjelmasta
     */
    @FXML
    private void Avaatiedot() {
        ModalController.showModal(MitatuliostettuaGUIController.class.getResource("Tietoja.fxml"), "Tietoja", null, "");
    }
    
    
   /**
    * Kysytään mitä lisätään ja mennään jompaan kumpaan
    */
    @FXML
    private void lisays() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Valitse");
        alert.setHeaderText(null);
        alert.setContentText("Mitä haluat lisätä");

        ButtonType buttonTypeYes = new ButtonType("Kauppareissun", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Tuoteryhmän", ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == buttonTypeYes ) lisaauusi(); 
        if ( result.get() == buttonTypeCancel ) uusituoteryhma(); 
    }
    
    
    ///------------------------------------------------------------------------------------
    
    private Mitatuliostettua mitatuliostettua;
    private String mitatuli = "mitatuliostettua";
    private Kauppareissu valittuKauppareissu;
    private TextArea areaKauppareissu = new TextArea();
   
    /**
     * tallennetan
     * @return jotain
     */
    protected String tallenna() {
        try {
            mitatuliostettua.talleta();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
     }

    /**
     * Näyttää listasta valitun kauppareissun tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaKauppareissu() {
        valittuKauppareissu = chooserKauppareissut.getSelectedObject();

        if (valittuKauppareissu == null)  {
            areaKauppareissu.clear();
            return;
        }

        areaKauppareissu.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKauppareissu)) {
            tulosta(os, valittuKauppareissu);
        }
    }
    
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös jäsenlistan kuuntelija 
     */
    protected void alusta() {
        panelKauppareissu.setContent(areaKauppareissu);
        areaKauppareissu.setFont(new Font("Courier New", 12));
        panelKauppareissu.setFitToHeight(true);
        
        chooserKauppareissut.clear();
        chooserKauppareissut.addSelectionListener(e -> naytaKauppareissu());
    }

    
    private void naytaVirhe(String virhe) {
        //
    }
    
    
    private void setTitle(String title) {
         ModalController.getStage(texthakukentta).setTitle(title);
    }
   
    
    /**
     * Alustaa koko homman lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     * @throws SailoException gt
     */
    protected String lueTiedosto(String nimi) throws SailoException {
        mitatuli = nimi;
        setTitle("Kauppareissu - " + mitatuli);
        try {
            mitatuliostettua.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
     }

    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     * @throws SailoException g
     */
    public boolean avaa() throws SailoException {
        lueTiedosto("kauppareissut.dat");
        return true;
    }
    

    /**
     * @param mitatuliostettua mitatuliostettua
     */
    public void setMitatuliostettua(Mitatuliostettua mitatuliostettua) {
        
        this.mitatuliostettua = mitatuliostettua;
        naytaKauppareissu();
    }
    
    
    /** 
    * Hakee kauppareissujen tiedot listaan 
    * @param jnro kauppareissun numero, joka aktivoidaan haun jälkeen 
     * @throws SailoException ih
    */ 
   protected void hae(int jnro) throws SailoException { 
       int k = chooserhakutapavalinta.getSelectionModel().getSelectedIndex();
       String ehto = texthakukentta.getText(); 
       if (k > 0 || ehto.length() > 0)
           naytaVirhe(String.format("Ei osata hakea (kenttä: %d, ehto: %s)", k, ehto));
       else
           naytaVirhe(null);
       
       chooserKauppareissut.clear();

       int index = 0;
       Collection<Kauppareissu> kauppareissut;
       kauppareissut = mitatuliostettua.etsi(ehto, k);
       int i = 0;
       for (Kauppareissu kauppareissu :kauppareissut) {
           if (kauppareissu.getTunnus() == jnro) index = i;
           chooserKauppareissut.add(kauppareissu.getPvm(), kauppareissu);
           i++;
       }

       chooserKauppareissut.setSelectedIndex(index); 
   }    
   
   
   /**
    * Luo uuden kuppareissun jota aletaan editoimaan 
 * @throws SailoException jhg
    */
   protected void uusiKauppareissu() throws SailoException {
       Kauppareissu uusi = new Kauppareissu();
       uusi.rekisteroi();
       uusi.annaTiedot();
       try {
           mitatuliostettua.lisaa(uusi);
       } catch (SailoException e) {
           Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
           return;
       }
       hae(uusi.getTunnus());
   }
   
   
     /**Tekee uuden tyhjän tuoteryhmän oston editointia varten 
     * @return tuoteryhma
     * @throws SailoException yt
     */
    public Tuoteryhma uusiTuoteryhma() throws SailoException {
           Tuoteryhma tuoteryhma = new Tuoteryhma();
           tuoteryhma.rekisteroi();
           tuoteryhma.annaTiedot();
           mitatuliostettua.lisaaTuoteryhma(tuoteryhma);
           return tuoteryhma;          
       }
   
   
   /** 
    * Tekee uuden tyhjän oston editointia varten 
    * @param tuoteryhma .
    * @throws SailoException gd
    */ 
   public void uusiOsto(Tuoteryhma tuoteryhma) throws SailoException { 
       if ( valittuKauppareissu == null ) return;  
       Osto ost = new Osto();  
       ost.rekisteroi();  
       ost.annaTiedot(valittuKauppareissu.getTunnus(), tuoteryhma.getNimi());  
       
       mitatuliostettua.lisaaOsto(ost); 
      
       hae(valittuKauppareissu.getTunnus()); 
   } 


   /**
    * Tulostaa kauppareissun tiedot
    * @param os tietovirta johon tulostetaan
    * @param kauppareissu tulostettava kauppareissu
    */
   public void tulosta(PrintStream os, final Kauppareissu kauppareissu) {
       os.println("----------------------------------------------");
       kauppareissu.tulosta(os);
       os.println("----------------------------------------------");
       try {
           List<Osto> ostot = mitatuliostettua.annaOstot(kauppareissu);
           for (Osto ost : ostot) 
               ost.tulosta(os);     
       } catch (SailoException ex) {
           Dialogs.showMessageDialog("Ostojen hakemisessa ongelmia! " + ex.getMessage());
       }   

   }
   
   
   /**
    * Tulostaa listassa olevat kauppareissut tekstialueeseen
    * @param text alue johon tulostetaan
 * @throws SailoException gh
    */
   public void tulostaValitut(TextArea text) throws SailoException {
       try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
           os.println("Tulostetaan kaikki kauppareissut");
           Collection<Kauppareissu> kauppareissut = mitatuliostettua.etsi("", -1); 
           for (Kauppareissu kauppareissu : kauppareissut) { 
               tulosta(os, kauppareissu);
               os.println("\n\n");
           }
       }

   }


   @SuppressWarnings("unused")
private void muokkaa() {
       ModalController.showModal(MitatuliostettuaGUIController.class.getResource("KauppareissuDialogView.fxml"), "Kauppareissun muokkaus", null, "");
   }

}
	

