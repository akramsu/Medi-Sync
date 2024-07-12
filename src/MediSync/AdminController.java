package MediSync;

import java.io.IOException;
import java.time.LocalDate;

import MediSync.MediSync_Model.MedicinesData;
import MediSync.MediSync_Model.OrderData;
import MediSync.MediSync_Model.OrderDataManager;
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
import MediSync.MediSync_Model.OrderData;
import MediSync.MediSync_Model.OrderDataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

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
    private Button update;
    @FXML
    private Button delete;

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
    private void ButtonUpdate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/updatePage.fxml"));
            Pane consultPage = loader.load();
            mainPane.setCenter(consultPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ButtonDelete(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/deletePage.fxml"));
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
    private TableView<OrderData> ordersTableView;
    @FXML
    private TableColumn<OrderData, String> orderIdColumn;
    @FXML
    private TableColumn<OrderData, String> userEmailColumn;
    @FXML
    private TableColumn<OrderData, String> medicineNameColumn;
    @FXML
    private TableColumn<OrderData, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderData, Double> totalPriceColumn;
    @FXML
    private TableColumn<OrderData, LocalDate> orderDateColumn;

    private OrderDataManager orderDataManager;

    
    public void initialize() {
        orderDataManager = new OrderDataManager();

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        loadOrders();

        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    private void loadOrders() {
        List<OrderData> orders = orderDataManager.getAllOrders();
        ObservableList<OrderData> observableOrders = FXCollections.observableArrayList(orders);
        ordersTableView.setItems(observableOrders);
    }

    @FXML
    private void performSearch(ActionEvent event) {
        // String searchQuery = searchBar.getText().trim().toLowerCase();
        // ObservableList<MedicinesData> filteredData = FXCollections.observableArrayList();

        // for (MedicinesData medicine : MedTable.getItems()) {
        //     if (medicine.getMedicineName().toLowerCase().contains(searchQuery)) {
        //         filteredData.add(medicine);
        //     }
        // }

        // MedTable.setItems(filteredData);
        // noResultsOverlay.setVisible(filteredData.isEmpty());
    }

    private void hideNoResultsOverlay() {
        // noResultsOverlay.setVisible(false);
    }
}
