package fxMitatuliostettua;
import java.awt.TextField;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mitatuliostettua.Kauppareissu;
import mitatuliostettua.Osto;
import mitatuliostettua.Ostot;
import mitatuliostettua.SailoException;
import mitatuliostettua.Tuoteryhma;


/**
 * Kauppareissun tietojen lisääminen
 * @author elisa
 * @version 12.2.2020
 */
public class TiedotController implements ModalControllerInterface<Kauppareissu>,Initializable{
   
    @FXML private TextField textTiedot;    
    @FXML private DatePicker paivamaara;
    
    @FXML private StringGrid<Tuoteryhma> StringGridTiedot;
    
    @FXML private ComboBoxChooser<String> chooserValitse;
    @FXML private TextField textHinta;
    @FXML private TextField textMaara;
    @FXML private Button buttonLisaaryhmia;
    @FXML private Button buttonPoistaryhma;
   
    @FXML private Button ButtonLisaauusituoteryma;
    @FXML private Button buttonValmis;
   
    
    @FXML void hintaValittu(KeyEvent event) {
        this.hinta = Integer.parseInt(textHinta.getText());
    }


    @FXML void lkmValittu(KeyEvent event) {
      this.maara = Integer.parseInt(textMaara.getText());   
    }

        
    @FXML void Lisauusituoteryhmapainettu() {
        ModalController.showModal(TiedotController.class.getResource("Uusituoteryhma.fxml"), "Uusi tuoteryhmä", null, "");
    }

    
    
    @FXML void PoistatuoteryhmaPainettu() {
        Dialogs.showMessageDialog("Tyhjennetään valitun tuoteryhmän tiedot ja poistetaan tuoteryhmä listalta, ei toimi vielä");
    }
    
  

    @FXML void Valmisklikattu() {
        ModalController.closeStage(buttonValmis);  
    }
    
    

    @FXML void lisäätiedoissapainettu() {
        
        lisaa();
        //Dialogs.showMessageDialog("Lisätään tuoteryhmä listaan ja tyhjennetään kirjoituskentät, ei toimi vielä");
    }
    
    
    /**Lisätään osto, jonka tiedoiksi laitetaan kenttien tiedot
     * 
     */
    public void lisaa() {
       
        
       //mitatuliostettua.uusiOsto(chooserValitse.getSelectedText(), maara, hinta);
       
        
    }



    private Kauppareissu valittuKauppareissu;
    private Ostot ostot = new Ostot();
    private int maara;
    private Object hinta;
    private String tuoteryhma;
   
    
    
    /**
     * Tyhjentään tekstikentät 
     */
    public void tyhjenna() {
        
        StringGridTiedot.clear();
    }
    
   
    /**
     * Tekee tarvittavat muut alustukset.
     */
    protected void alusta() {
        //
    }

    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();  
        
    }
    
    

    @Override
    public void handleShown() {
        paivamaara.requestFocus();   
    }

    @Override
    public void setDefault(Kauppareissu kauppareissu) {
        valittuKauppareissu = kauppareissu;
        naytaOstot(valittuKauppareissu);
        
    }
    
    @Override
    public Kauppareissu getResult() {
        return valittuKauppareissu;
    }
    

    private void naytaOstot(Kauppareissu valittu) {
        if (valittu == null) return;
        LocalDate paiv = getPvm(valittu);
        paivamaara.setValue(paiv);
        
        if (ostot == null) tyhjenna();
        
        ostot.annaOstot(valittuKauppareissu.getTunnus());
        StringGridTiedot.set("1", 1, 1);
        int i = 0; 
        for (Osto ost : ostot) {
            this.maara = ost.getHinta();
            String luku = String.valueOf(this.maara);
            this.hinta = ost.getMaara();
            String luku2 = String.valueOf(this.hinta) ;
            this.tuoteryhma = ost.getTuoteryhma().getNimi();
            StringGridTiedot.set(this.tuoteryhma, 1, i);
            StringGridTiedot.set(luku, 2, i);
            StringGridTiedot.set(luku2, 1, i);
        }
    }



    private LocalDate getPvm(Kauppareissu valittu) {
        String muodossa = "dd.MM.yyyy";
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(muodossa);
        String paiva = valittu.getPvm(); 
        LocalDate paiv = LocalDate.parse(paiva, dtFormatter );
        return paiv;
    }



    /**
     * Tallennetaan
     * @throws SailoException 
     */
    private void tallenna() throws SailoException {
        ostot.tallennaOsto();
    }



    /**
     * @param modalityStage ythj
     * @param valittu rdh
     * @return ytdu
     */
    public static Kauppareissu kysyTiedot(Stage modalityStage, Kauppareissu valittu) {
        return ModalController.showModal(
                TiedotController.class.getResource("Tiedot.fxml"),
                "Tiedot",
                modalityStage, valittu, null 
            );  
    }



}
