package fxMitatuliostettua;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Tietoja "Mitä tuli ostettua?":sta
 * @author elisa
 * @version 12.2.2020
 */
public class TietojaGUIController implements ModalControllerInterface<String>{

    @FXML private Button buttonOk;

    @FXML void OkClicked() {
        ModalController.closeStage(buttonOk);
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
