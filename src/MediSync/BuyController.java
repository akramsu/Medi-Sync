package MediSync;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import MediSync.MediSync_Model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class BuyController implements Initializable {
    @FXML private Button visa;
    @FXML private Button stripe;
    @FXML private Button paypal;
    @FXML private Button master;

    @FXML private TextField cvv;
    @FXML private TextField cardNumber;
    @FXML private Label medicineName;
    @FXML private DatePicker dp;
    @FXML private Label quantity;
    @FXML private Label price;
    @FXML private Label details;

    private OrderDataManager orderDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetButtonStyles(); // Reset button styles on initialization
        orderDataManager = new OrderDataManager();
    }

    @FXML
    protected void buttonPay(ActionEvent event) {
        // Generate order data
        String orderId = UUID.randomUUID().toString();
        String medicineNameText = medicineName.getText();
        int quantityValue = Integer.parseInt(quantity.getText());
        double totalPriceValue = Double.parseDouble(price.getText());
        LocalDate orderDate = LocalDate.now();

        // Retrieve the current user
        CurrentUser currentUserInstance = CurrentUser.getInstance();
        UserData currentUser = currentUserInstance.getCurrentUser();

        if (currentUser != null) {
            String userEmail = currentUser.getEmail();

            OrderData order = new OrderData(orderId, userEmail, medicineNameText, quantityValue, totalPriceValue, orderDate);
            orderDataManager.addOrder(order);

            // Show confirmation or navigate to another page
            System.out.println("Order placed successfully for user: " + userEmail);

            // Change the text in the details label
            details.setText("The payment process is successfully done. Thank you!");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    @FXML
    protected void visabt(ActionEvent event) {
        resetButtonStyles();
        visa.setStyle("-fx-border-color: #0047AB; -fx-border-width: 2px; -fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    @FXML
    protected void stripebt(ActionEvent event) {
        resetButtonStyles();
        stripe.setStyle("-fx-border-color: #0047AB; -fx-border-width: 2px; -fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    @FXML
    protected void paypalbt(ActionEvent event) {
        resetButtonStyles();
        paypal.setStyle("-fx-border-color: #0047AB; -fx-border-width: 2px; -fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    @FXML
    protected void masterbt(ActionEvent event) {
        resetButtonStyles();
        master.setStyle("-fx-border-color: #0047AB; -fx-border-width: 2px; -fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    private void resetButtonStyles() {
        String defaultStyle = "-fx-background-color: #ffffff; -fx-border-color: transparent;";
        if (visa != null) visa.setStyle(defaultStyle);
        if (stripe != null) stripe.setStyle(defaultStyle);
        if (paypal != null) paypal.setStyle(defaultStyle);
        if (master != null) master.setStyle(defaultStyle);
    }

    public void setMedicineDetails(MedicinesData medicine) {
        medicineName.setText(medicine.getMedicineName());
        quantity.setText(String.valueOf(medicine.getMedicineQuantity()));
        price.setText(String.valueOf(medicine.getMedicinePrice()));
    }
}