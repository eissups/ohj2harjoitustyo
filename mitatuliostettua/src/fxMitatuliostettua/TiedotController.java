package fxMitatuliostettua;

import java.net.URL;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringAndObject;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mitatuliostettua.Kauppareissu;
import mitatuliostettua.Mitatuliostettua;
import mitatuliostettua.Osto;
import mitatuliostettua.Ostot;
import mitatuliostettua.SailoException;
import mitatuliostettua.Tuoteryhma;
import mitatuliostettua.Tuoteryhmat;


/**
 * Kauppareissun tietojen lisääminen
 * @author elisa
 * @version 12.2.2020
 */
public class TiedotController implements ModalControllerInterface<Ostot>,Initializable{
   
    @FXML private TextField textTiedot;    
    @FXML private DatePicker paivamaara;
    
    @FXML private StringGrid<Osto> StringGridTiedot;
    
    @FXML private ComboBoxChooser<Tuoteryhma> chooserValitse;
    @FXML
    private TextField maara;

    @FXML
    private TextField hinta;
    @FXML private Button buttonLisaaryhmia;
    @FXML private Button buttonPoistaryhma;
   
    @FXML private Button ButtonLisaauusituoteryma;
    @FXML private Button buttonValmis;
    private Mitatuliostettua mitatuliostettua;
   
 
    
    @FXML void PoistatuoteryhmaPainettu() {
        Dialogs.showMessageDialog("Tyhjennetään valitun tuoteryhmän tiedot ja poistetaan tuoteryhmä listalta, ei toimi vielä");
    }
    
  

    @FXML void Valmisklikattu() {
        ModalController.closeStage(buttonValmis);  
    }
    
    

    @FXML void lisäätiedoissapainettu() throws SailoException {
        
        if (chooserValitse.getSelectedIndex() == 0) {
            return;
        }
        uusiOsto();
        //Dialogs.showMessageDialog("Lisätään tuoteryhmä listaan ja tyhjennetään kirjoituskentät, ei toimi vielä");
    }
    

    private static Kauppareissu valittuKauppareissu;
    private static Ostot ostot = new Ostot();
    private int maara1 = 0;
    private int hinta1 = 0;
    private String tuoteryhma;
    private TextField[] edits;
    private int kentta = 0;
    private Tuoteryhmat tuoteryhmat;
    private Tuoteryhma tuote;
    
    
    /** 
     * Tekee uuden tyhjän oston editointia varten 
     * @throws SailoException gd
     */ 
    public void uusiOsto() throws SailoException { 
        if ( valittuKauppareissu  == null ) return;  
        Osto ost = new Osto();  
        ost.rekisteroi(); 
        
        int hintaa = Integer.parseInt(hinta.getText());
        int maaraa = Integer.parseInt(maara.getText());
        SingleSelectionModel<StringAndObject<Tuoteryhma>> tuote = chooserValitse.getSelectionModel();
        StringAndObject<Tuoteryhma> joku = tuote.getSelectedItem();
        Tuoteryhma tuotery = joku.getObject();
        ost.annaTiedot(valittuKauppareissu.getTunnus(), tuotery , maaraa, hintaa);  
        ostot.lisaaMuokkaa(chooserValitse.getSelectedText(), ost);
        naytaOsto(ost);
       
    } 
    
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
        etsiTuoteryhmat();
    }

    

    private void etsiTuoteryhmat() {
        tuoteryhmat = new Tuoteryhmat();
        try {
            tuoteryhmat.lueTiedostosta();
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
    public void setDefault(Ostot oletus) {
        ostot = oletus;
        naytaOstot(valittuKauppareissu);
        naytaTuoteryhmat();
        
    }
    
    private void naytaTuoteryhmat() {
        for(Tuoteryhma tuoter : tuoteryhmat ) {
            chooserValitse.add(tuoter.getTuoteryhma(), tuoter); 
        }      
    }



    @Override
    public Ostot getResult() {
        return ostot;
    }
    
    
    

    private void naytaOstot(Kauppareissu valittu) {
        StringGridTiedot.clear();;
        if (valittu == null) return;
        LocalDate paiv = getPvm(valittu);
        paivamaara.setValue(paiv);
        
        if (ostot == null) return;
        
        StringGridTiedot.clear();
        
        
            for (Osto osto: ostot)
                naytaOsto(osto);
       
       
    }




    private void naytaOsto(Osto osto) {
        
        int kenttia = osto.getKenttia(); 
        String[] rivi = new String[kenttia-osto.ekaKentta()]; 
        for (int i=0, k=osto.ekaKentta(); k < kenttia; i++, k++) 
            rivi[i] = osto.anna(k); 
        StringGridTiedot.add(osto,rivi);
        
    }



    private LocalDate getPvm(Kauppareissu valittu) {
        String muodossa = "yyyy-MM-dd";
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(muodossa);
        String paiva = valittu.getPvm(); 
        LocalDate paiv = LocalDate.parse(paiva, dtFormatter );
        return paiv;
    }



    /**
     * Tallennetaan
     * @throws SailoException 
     */
    @SuppressWarnings("unused")
    private void tallenna() throws SailoException {
        ostot.tallennaOsto();
    }



    /**
     * @param modalityStage ythj
     * @param oletus yjrf 
     * @param valittu rdh
     * @return ytdu
     */
    public static Ostot kysyTiedot(Stage modalityStage, Ostot oletus, Kauppareissu valittu) {
        valittuKauppareissu = valittu;
        return ModalController.<Ostot, TiedotController>showModal(
                TiedotController.class.getResource("Tiedot.fxml"),
                "Tiedot",
                modalityStage, oletus, null 
            );  
    }

}
