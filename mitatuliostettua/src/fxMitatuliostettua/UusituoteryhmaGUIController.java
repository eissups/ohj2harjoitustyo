package fxMitatuliostettua;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Tuoteryhmien lisääminen ja poistaminen
 * @author elisa
 * @version 12.2.2020
 */
public class UusituoteryhmaGUIController implements ModalControllerInterface<String>{


    @FXML private Button buttonPoista;
    @FXML private Button buttonPeruuta;
    @FXML private Button buttonTallenna;
   
    
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
     * Suljetaan ikkuna, eikä tallenneta mitään
     */
    private void poistu() {
        ModalController.closeStage(buttonPeruuta);
        
    }
    
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub    
    }

    
    
    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }

}
