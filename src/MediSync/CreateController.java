package MediSync;

import MediSync.MediSync_Model.MedicinesData;
import MediSync.MediSync_Model.MedicinesDataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private TextField medicineName;
    @FXML
    private Label form;
    @FXML
    private ChoiceBox<String> cbForm;
    private String[] choices = {"Tablets", "Capsules", "Liquid", "Cream"};

    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private Label expirationDate;
    @FXML
    private DatePicker dpExpirationDate;
    @FXML
    private Label quantity;
    @FXML
    private Spinner<Integer> spQuantity;
    @FXML
    private Button add;

    private String myFormattedDate;
    private MedicinesDataManager medicinesDataManager;

    @FXML
    public void ButtonAdd(ActionEvent event) {
        try {
            System.out.println("ButtonAdd method called."); 

            System.out.println("medicineName: " + (medicineName == null ? "null" : "initialized"));
            System.out.println("cbForm: " + (cbForm == null ? "null" : "initialized"));
            System.out.println("description: " + (description == null ? "null" : "initialized"));
            System.out.println("price: " + (price == null ? "null" : "initialized"));
            System.out.println("dpExpirationDate: " + (dpExpirationDate == null ? "null" : "initialized"));
            System.out.println("spQuantity: " + (spQuantity == null ? "null" : "initialized"));
            System.out.println("medicinesDataManager: " + (medicinesDataManager == null ? "null" : "initialized"));

            String name = medicineName.getText();
            System.out.println("Medicine Name: " + name);

            String form = cbForm.getValue();
            System.out.println("Form: " + form);

            String description = this.description.getText();
            System.out.println("Description: " + description);

            String priceText = this.price.getText();
            System.out.println("Price: " + priceText);

            LocalDate expirationDate = dpExpirationDate.getValue();
            System.out.println("Expiration Date: " + expirationDate);

            int quantity = spQuantity.getValue();
            System.out.println("Quantity: " + quantity);

            if (name == null || name.isEmpty()) {
                System.out.println("Error: Medicine name is null or empty.");
                showAlert("Error", "Please fill in the medicine name.");
                return;
            }

            if (form == null || form.isEmpty()) {
                System.out.println("Error: Form is null or empty.");
                showAlert("Error", "Please select a form.");
                return;
            }

            if (description == null || description.isEmpty()) {
                System.out.println("Error: Description is null or empty.");
                showAlert("Error", "Please fill in the description.");
                return;
            }

            if (priceText == null || priceText.isEmpty()) {
                System.out.println("Error: Price text is null or empty.");
                showAlert("Error", "Please fill in the price.");
                return;
            }

            if (expirationDate == null) {
                System.out.println("Error: Expiration date is null.");
                showAlert("Error", "Please select an expiration date.");
                return;
            }

            double price = Double.parseDouble(priceText);

            myFormattedDate = expirationDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            MedicinesData medicine = new MedicinesData(name, form, description, price, myFormattedDate, quantity);

            medicinesDataManager.addMedicine(medicine);

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid price.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void getForm(ActionEvent event) {
        String theForm = cbForm.getValue();
        form.setText(theForm);
    }

    @FXML
    public void getExpirationDate(ActionEvent event) {
        LocalDate myDate = dpExpirationDate.getValue();
        if (myDate != null) {
            myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            expirationDate.setText(myFormattedDate);
        } else {
            expirationDate.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbForm.getItems().addAll(choices);
        cbForm.setOnAction(this::getForm);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(1);
        spQuantity.setValueFactory(valueFactory);
        quantity.setText(Integer.toString(spQuantity.getValue()));
        spQuantity.valueProperty().addListener((observable, oldValue, newValue) -> quantity.setText(Integer.toString(newValue)));

        medicinesDataManager = new MedicinesDataManager();
    }

    private void clearFields() {
        medicineName.clear();
        cbForm.setValue(null);
        description.clear();
        price.clear();
        dpExpirationDate.setValue(null);
        spQuantity.getValueFactory().setValue(1);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
