package MediSync;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class BuyController implements Initializable {
    @FXML private RadioButton visaOption;
    @FXML private RadioButton masterCardOption;
    @FXML private TextField cvv;
    @FXML private TextField cardNumber;
    @FXML private Label medicineName;
    @FXML private DatePicker dp;
    @FXML private Label quantity;
    @FXML private Label price;
    @FXML private Button visa;
    @FXML private Button stripe;
    @FXML private Button paypal;
    @FXML private Button master;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // If specific initialization is needed, add here
    }

    @FXML
    protected void buttonPay(ActionEvent event) {
    }
    @FXML
    protected void visabt(ActionEvent event) {
    }
    @FXML
    protected void stripebt(ActionEvent event) {
    }
    @FXML
    protected void paypalbt(ActionEvent event) {
    }
    @FXML
    protected void masterbt(ActionEvent event) {
    }
}
