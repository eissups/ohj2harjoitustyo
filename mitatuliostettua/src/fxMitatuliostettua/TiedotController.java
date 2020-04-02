package fxMitatuliostettua;
import java.awt.TextField;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;


/**
 * Kauppareissun tietojen lisääminen
 * @author elisa
 * @version 12.2.2020
 */
public class TiedotController implements ModalControllerInterface<String>{
   
    @FXML private TextField textTiedot;    
    @FXML private DatePicker paivamaara;
    @FXML private Button buttonLisaaryhmia;
    @FXML private Button buttonPoistaryhma;
    @FXML private Button ButtonLisaauusituoteryma;
    @FXML private Button buttonValmis;
    

    @FXML void Lisauusituoteryhmapainettu() {
        ModalController.showModal(TiedotController.class.getResource("Uusituoteryhma.fxml"), "Uusi tuoteryhmä", null, "");
    }

    
    
    @FXML void PoistatuoteryhmaPainettu() {
        Dialogs.showMessageDialog("Tyhjennetään valitun tuoteryhmän tiedot ja poistetaan tuoteryhmä listalta, ei toimi vielä");
    }
    
  

    @FXML void Valmisklikattu() {
        tallenna();
        ModalController.closeStage(buttonValmis);  
    }
    
    

    @FXML void lisäätiedoissapainettu() {
        Dialogs.showMessageDialog("Lisätään tuoteryhmä listaan ja tyhjennetään kirjoituskentät, ei toimi vielä");
    }
    
    
   
    @Override public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub    
    }

    
    
    @Override
    public void setDefault(String arg0) {
        // 
    }
    
    /**
     * Tallennetaan
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
    }
}
