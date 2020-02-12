package fxMitatuliostettua;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;


/**
 * Ensin avautuva pääikkuna
 * @author elisa
 * @version 27.1.2020
 * @version 13.2.2020
 */
public class MitatuliostettuaGUIController {
    
    @FXML
    private MenuItem Menulisaa;

    @FXML
    private MenuItem Menulopeta;
    
    @FXML
    private MenuItem Menupoista;
    
    @FXML
    private MenuItem Menuapua;

    @FXML
    private MenuItem Menutietoja;

    
    @FXML private TextField texthakukentta;

    @FXML private ComboBoxChooser<?> chooserhakutapavalinta;

    @FXML private DatePicker hakupaiva;

    @FXML private DatePicker datepickertiedot;

    @FXML private Button Buttonlisaauusi;

    @FXML private Button buttonpoista;

    @FXML private Button buottonmuokkaa;

    @FXML private Button Buttonuusituoteryhma;
    
    @FXML private DatePicker paivamaara;
    
    
    @FXML
    void aloitahaku() {
        Dialogs.showMessageDialog("Haetaan valitulla ehdolla ja järjestetään lista sen mukaan, mutta ei toimi vielä");
    }
    

    
    @FXML void hakupaivavalittu() {
        //
    }
    
    

    @FXML void lisaauusi() {
        Dialogs.showMessageDialog("Lisätään uusi kauppareissu ja näyrtään keskellä, ei toimi vielä");
    }
    

    
    @FXML void muokkaaostettujaClicked() {
        
        ModalController.showModal(MitatuliostettuaGUIController.class.getResource("Tiedot.fxml"), "Tiedot", null, ""); 
        
    }
    

    
    @FXML void paivavalittu() {
        tallenna();
    }

    
    
    @FXML void paivays() {
        Dialogs.showMessageDialog("Järjestettäisiin haetun päivän mukaan, ei toimi vielä");
    }

    
    
    @FXML void poista() {
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
    
    
    /**
     * tallennetan
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
        
        }
    
  

}
	

