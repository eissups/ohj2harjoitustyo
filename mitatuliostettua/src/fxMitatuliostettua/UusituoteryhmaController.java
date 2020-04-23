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



/**
 * Tuoteryhmien lisääminen ja poistaminen
 * @author elisa
 * @version 12.2.2020
 */
public class UusituoteryhmaController implements ModalControllerInterface<Mitatuliostettua>,Initializable{


    @FXML private Button buttonPoista;
    @FXML private Button buttonPeruuta;
    @FXML private Button buttonTallenna;
   
    @FXML private TextField tuoteryhma;
    private Mitatuliostettua mitatuliostettua;
   
    
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
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
        poistu();
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
    public Mitatuliostettua getResult() {
        return mitatuliostettua;
    }

    
    
    @Override
    public void handleShown() {
        tuoteryhma.requestFocus();    
    }

    

    public static Mitatuliostettua kysyTiedot(Stage modalityStage, Mitatuliostettua mitatuliostettua) {
        return ModalController.showModal(
                UusituoteryhmaController.class.getResource("Uusituoteryhma.fxml"),
                "Tiedot",
                modalityStage, mitatuliostettua, null 
            );  
        
    }


    @Override
    public void setDefault(Mitatuliostettua oletus) {
        mitatuliostettua = oletus;
        
    }


 

}
