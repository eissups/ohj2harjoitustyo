package fxMitatuliostettua;
	
import javafx.application.Application;
import javafx.stage.Stage;
import mitatuliostettua.Mitatuliostettua;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


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
			
			Mitatuliostettua mitatuliostettua = new Mitatuliostettua();
			mitatuliostettuaCtrl.setMitatuliostettua(mitatuliostettua);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
