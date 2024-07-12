package MediSync;

import MediSync.MediSync_Model.MedicinesDataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    @FXML
    private TextField medicineName;
    @FXML
    private Button delete;

    private MedicinesDataManager medicinesDataManager;

    @FXML
    public void ButtonDelete(ActionEvent event) {
        try {
            System.out.println("ButtonDelete method called.");
            String name = medicineName.getText();
            System.out.println("Medicine Name: " + name);

            // Check for null or empty value and show an error message if the field is null or empty
            if (name == null || name.isEmpty()) {
                System.out.println("Error: Medicine name is null or empty.");
                showAlert("Error", "Please fill in the medicine name.");
                return;
            }

            // Remove the medicine from the medicinesDataManager
            medicinesDataManager.removeMedicine(name);

            // Clear the input field after deleting the medicine
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicinesDataManager = new MedicinesDataManager();
    }

    private void clearFields() {
        medicineName.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
