package fxMitatuliostettua;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mitatuliostettua.Mitatuliostettua;
import mitatuliostettua.Tuoteryhma;



/**
 * Tuoteryhmien lisääminen ja poistaminen
 * @author elisa
 * @version 12.2.2020
 */
public class UusituoteryhmaController implements ModalControllerInterface<Tuoteryhma>,Initializable{


    @FXML private Button buttonPoista;
    @FXML private Button buttonPeruuta;
    @FXML private Button buttonTallenna;
   
    @FXML private TextField tuoteryhma;
    @SuppressWarnings("unused")
    private Mitatuliostettua mitatuliostettua;
    private Tuoteryhma tuote;
   
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();  
    }
    
    
    @FXML void peruutaClicked() {
        poistu();
    }
    
    
    @FXML void poistaCllicked() {
        Dialogs.showMessageDialog("Poistetaan, muttei toimi vielä");
    }
    
    
    @FXML void tallennaClicked() {
        lisaaTuoteryhma();
        poistu();
    }

    
    private void lisaaTuoteryhma() {
        tuote = new Tuoteryhma();
        tuote.rekisteroi();
        tuote.annaTiedot(tuoteryhma.getText());
        
    }


    /**
    * Tekee tarvittavat muut alustukset.
    */
   protected void alusta() {
       //
   }
    
    
   
    /**
     * Suljetaan ikkuna, eikä tallenneta mitään
     */
    private void poistu() {
        ModalController.closeStage(buttonPeruuta);
        
    }
    

    
    
    @Override
    public void handleShown() {
        tuoteryhma.requestFocus();    
    }

    

    /**Tullaan tänne, muokataan ja palautetaan ostot
     * @param modalityStage stage
     * @param oletus tuoteryhmä jok tuodaan
     * @return ostot
     */
    public static Tuoteryhma kysyTiedot(Stage modalityStage, Tuoteryhma oletus) {
        return ModalController.<Tuoteryhma, UusituoteryhmaController>showModal(
                UusituoteryhmaController.class.getResource("Uusituoteryhma.fxml"),
                "Tuoteryhmat",
                modalityStage, oletus, null 
            );  
        
    }


    @Override
    public Tuoteryhma getResult() {
        return tuote;
    }


    @Override
    public void setDefault(Tuoteryhma oletus) {
        
        tuote = oletus;
    }
}
