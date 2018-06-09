package it.polito.tdp.formulaone;

import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.Model;
import it.polito.tdp.formulaone.model.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormulaOneController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Year> boxAnno;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	try {
	    	String year = this.boxAnno.getValue().toString();
	    	if(year.equals("Anno"))
	    		throw new IllegalArgumentException("Seleziona un anno dal menù a tendina.\n");
	    	
	    	model.createGraph(year);
	    	
	    	Driver d = model.getBestDriver();
	    	
	    	if(d!=null)
	    		this.txtResult.setText("Il pilota migliore di quell'anno è stato "+d.getForename()+ " "+d.getSurname() +"\n");
	    	else
	    		this.txtResult.setText("Non ho trovato il pilota migliore...\n");
    	} catch(NullPointerException e) {
    		this.txtResult.setText("Seleziona un anno dal menù a tendina.\n");
    	}
    }

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {
    	try {
    		int k = Integer.parseInt(this.textInputK.getText()) ;
    		if(k < 0)
    			throw new IllegalArgumentException();
    		
    		List<Driver> dreamTeam = model.dreamTeam(k);
    		
    	} catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un numero intero positivo.\n");
    	} catch(IllegalArgumentException e) {
    		this.txtResult.setText("Il numero inserito deve essere positivo.\n");
    	}
    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }
    
    public void setModel(Model model){
    	this.model = model;
    	for(Season s : model.getAllSeasons()) {
    		this.boxAnno.getItems().add(s.getYear());
    	}
    }
}
