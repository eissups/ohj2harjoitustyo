package fxMitatuliostettua;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mitatuliostettua.Mitatuliostettua;


/**
 * @author elisa
 * @version 27.1.2020
 *
 */
public class MitatuliostettuaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("MitatuliostettuaGUIView.fxml"));
		    final Pane root = (Pane)ldr.load();
		    final MitatuliostettuaGUIController mitatuliostettuaCtrl = (MitatuliostettuaGUIController) ldr.getController();
		   
		    Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("mitatuliostettua.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mitätuliostettua");
			
			primaryStage.setOnCloseRequest((event) -> {
                if ( !mitatuliostettuaCtrl.lopeta() ) event.consume();
            });
            
			
			Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
			mitatuliostettuaCtrl.setMitatuliostettua(mitatuliostettua);
			
			primaryStage.show();
			Application.Parameters params = getParameters(); 
            if ( params.getRaw().size() > 0 ) 
                mitatuliostettuaCtrl.lueTiedosto(params.getRaw().get(0));  
            else
                if ( !mitatuliostettuaCtrl.avaa() ) Platform.exit();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
