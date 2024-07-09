package MediSync;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;

import MediSync.MediSync_Model.MedicinesData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class CreateController implements Initializable{

    @FXML
    private TextField medicineName;
    @FXML
    private Label form;
    @FXML
    private ChoiceBox<String> cbForm;
    private String[] choices = {"Tablets", "Capsules","Liquid","Cream"};

    @FXML
    public void getForm(ActionEvent event) {
        String theForm = cbForm.getValue();
        form.setText(theForm);
    }

    @FXML
    private TextField desciption;
    @FXML
    private TextField price;
    @FXML
    private Label ExpirationDate;
    @FXML
    private DatePicker dpExpirationDate;

    String myFormattedDate;

    @FXML
    public void getExpirationDate(ActionEvent event){
        LocalDate myDate = dpExpirationDate.getValue();
        myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        ExpirationDate.setText(myFormattedDate);
    }

    @FXML
    private Label quantity;
    @FXML
    private Spinner<Integer> spQuantity;

    int currentValue;
    
    @FXML
    private Button add;

    private ArrayList<MedicinesData> medicinesList = new ArrayList<>();

    @FXML
    public void ButtonAdd(ActionEvent event){
        String name = medicineName.getText();
        String form = cbForm.getValue();
        String description = desciption.getText();
        double price = Double.parseDouble(this.price.getText());
        String expirationDate = myFormattedDate;
        int quantity = spQuantity.getValue();
        MedicinesData medicine = new MedicinesData(name, form, description, price, expirationDate, quantity);
        medicinesList.add(medicine);

        saveMedicinesToXML();

    }

    private void saveMedicinesToXML() {
        XStream xstream = new XStream();
        // Alias to make XML more readable
        xstream.alias("medicine", MedicinesData.class);

        try {
            // Serialize list to XML
            String xml = xstream.toXML(medicinesList);

            // Write XML to file
            try (FileWriter writer = new FileWriter("medicines.xml")) {
                writer.write(xml);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbForm.getItems().addAll(choices);
        cbForm.setOnAction(this::getForm);

		
        	SpinnerValueFactory<Integer> valueFactory = 
        			new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
            
        	valueFactory.setValue(1);
            
        	spQuantity.setValueFactory(valueFactory);
            
        	currentValue = spQuantity.getValue();
            
        	quantity.setText(Integer.toString(currentValue));
            
        	spQuantity.valueProperty().addListener(new ChangeListener<Integer>() {       

        		@Override
        		public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                    
        			currentValue = spQuantity.getValue();
                    
        			quantity.setText(Integer.toString(currentValue));
                    
        		}
        	});	
    }

}