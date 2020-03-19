package fxMitatuliostettua;
import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import mitatuliostettua.Kauppareissu;
import mitatuliostettua.Mitatuliostettua;
import mitatuliostettua.SailoException;
import javafx.scene.control.TextArea;


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
    
    
    
    @FXML private void aloitahaku() {
        Dialogs.showMessageDialog("Haetaan valitulla ehdolla ja järjestetään lista sen mukaan, mutta ei toimi vielä");
    }
    

    
    @FXML private void hakupaivavalittu() {
        //
    }
    
    

    @FXML private void lisaauusi() {
        //Dialogs.showMessageDialog("Lisäys ei vielä toimi");
        Kauppareissu uusi = new Kauppareissu();
        uusi.rekisteroi();
        uusi.annaTiedot();
        try {
            mitatuliostettua.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia luomisessa" + e.getMessage());
            return;
        }
        hae(uusi.getTunnus());
    }
    

    
    @FXML private void muokkaaostettujaClicked() {
        
        ModalController.showModal(MitatuliostettuaGUIController.class.getResource("Tiedot.fxml"), "Tiedot", null, ""); 
        
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
        ModalController.showModal(MitatuliostettuaGUIController.class.getResource("Uusituoteryhma.fxml"), "Uusi tuoteryhmä", null, "");
        
       
    }
    
  
    /**
     * Suljetaan
     */
    @FXML
    private void lopeta() {
        
        tallenna();
        Platform.exit();
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
    private Kauppareissu valittuKauppareissu;
    private TextArea areaKauppareissu = new TextArea();
   
    /**
     * tallennetan
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
        
        }

    /**
     * Näyttää listasta valitun kauppareissun tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaKauppareissu() {
        valittuKauppareissu = chooserKauppareissut.getSelectedObject();

        if (valittuKauppareissu == null) return;

        areaKauppareissu.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKauppareissu)) {
            valittuKauppareissu.tulosta(os);
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
    */ 
   protected void hae(int jnro) { 
       chooserKauppareissut.clear(); 

       int index = 0; 
       for (int i = 0; i < mitatuliostettua.getMaara(); i++) { 
           Kauppareissu kauppareissu = mitatuliostettua.annaKauppareissu(i); 
           if (kauppareissu.getTunnus() == jnro) index = i; 
           chooserKauppareissut.add(kauppareissu.getPvm(), kauppareissu); 
       } 
       chooserKauppareissut.getSelectionModel().select(index); // tästä tulee muutosviesti joka näyttää jäsenen 
   }    
   
   
   /**
    * Luo uuden kuppareissun jota aletaan editoimaan 
    */
   protected void uusiJKauppareissu() {
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
   

   private void muokkaa() {
       ModalController.showModal(MitatuliostettuaGUIController.class.getResource("KauppareissuDialogView.fxml"), "Kauppareissun muokkaus", null, "");
   }

}
	

