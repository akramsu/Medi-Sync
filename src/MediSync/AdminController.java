package MediSync;

import java.io.IOException;

import MediSync.MediSync_Model.MedicinesData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdminController {
    private OpenScene openScene = new OpenScene();

    @FXML
    private Button dashboard;

    @FXML
    private Button medicines;

    @FXML
    private Button users;

    @FXML
    private Button create;

    @FXML
    private Button Logout;

    @FXML
    private Pane pane;

    @FXML
    private BorderPane mainPane;

    @FXML
    private void ButtonDashboard(ActionEvent event) {
            mainPane.setCenter(pane); 
    }

    @FXML
    private void ButtonMedicines(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/MedicinesPage.fxml"));
            Pane catalogPage = loader.load();
            mainPane.setCenter(catalogPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonUsers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/UsersPage.fxml"));
            Pane healthTipsPage = loader.load();
            mainPane.setCenter(healthTipsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void ButtonCreate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/CreatePage.fxml"));
            Pane consultPage = loader.load();
            mainPane.setCenter(consultPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/LogoutPage.fxml")); // Correct path to FXML
            Pane logoutPage = loader.load();
            mainPane.setCenter(logoutPage); // Setting the logout page at the center
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for detailed debug information
        }
    }

    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<MedicinesData> MedTable;
    @FXML
    private TableColumn<MedicinesData, String> MedName;
    @FXML
    private TableColumn<MedicinesData, String> MedForm;
    @FXML
    private TableColumn<MedicinesData, String> MedDesc;
    @FXML
    private TableColumn<MedicinesData, String> MedExp;
    @FXML
    private TableColumn<MedicinesData, Double> MedPrice;
    @FXML
    private TableColumn<MedicinesData, Integer> MedQuant;
    @FXML
    private Pane noResultsOverlay; // The overlay pane for no results

    public void initialize() {
        MedName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        MedForm.setCellValueFactory(new PropertyValueFactory<>("Phone Number"));
        MedDesc.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        MedExp.setCellValueFactory(new PropertyValueFactory<>("medicineDescription"));
        MedPrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));
        MedQuant.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));

        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }


    @FXML
    private void performSearch(ActionEvent event) {
        String searchQuery = searchBar.getText().trim().toLowerCase();
        ObservableList<MedicinesData> filteredData = FXCollections.observableArrayList();

        for (MedicinesData medicine : MedTable.getItems()) {
            if (medicine.getMedicineName().toLowerCase().contains(searchQuery)) {
                filteredData.add(medicine);
            }
        }

        MedTable.setItems(filteredData);
        noResultsOverlay.setVisible(filteredData.isEmpty());
    }

    private void hideNoResultsOverlay() {
        noResultsOverlay.setVisible(false);
    }
}
