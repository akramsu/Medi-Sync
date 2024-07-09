package MediSync;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Cart implements Initializable{

    @FXML
    private Pane pane;

    @FXML
    private Label medicineLabel;

    @FXML
    private TextField medicineName;

    @FXML
    private TextField description;

    @FXML
    private Label quantity;

    @FXML
    private Spinner<Integer> spQuantity;
    int currentValue;
    @FXML
    private ChoiceBox<String> cbForm;
    private String[] choices = {"Bank Transfer", "PayPal","Visa","Master card"};

    // @FXML
    // private Button buy;

    // @FXML
    // private void buybutton(ActionEvent event) {
    //     System.out.println("button buy clicked");
    // }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbForm.getItems().addAll(choices);

		
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