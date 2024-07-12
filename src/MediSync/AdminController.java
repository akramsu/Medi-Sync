package MediSync;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
    private Button selectedButton;

    public void initialize() {
        orderDataManager = new OrderDataManager();

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        loadOrders();

        initializeButtonEffects();
        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Apply initial style to the selected button
        applyButtonStyle(dashboard);
    }

    private void loadOrders() {
        List<OrderData> orders = orderDataManager.getAllOrders();
        ObservableList<OrderData> observableOrders = FXCollections.observableArrayList(orders);
        ordersTableView.setItems(observableOrders);
    }

    private void initializeButtonEffects() {
        // Set initial style for buttons
        dashboard.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-text-fill: #0d134b;");
        medicines.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        users.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        create.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        update.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        delete.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        Logout.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");

        selectedButton = dashboard;

        // Add hover effects
        addHoverEffect(dashboard);
        addHoverEffect(medicines);
        addHoverEffect(users);
        addHoverEffect(create);
        addHoverEffect(update);
        addHoverEffect(delete);
        addHoverEffect(Logout);
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(e -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: #757DE8; -fx-text-fill: white; -fx-background-radius: 10px; -fx-cursor: hand;");
            }
        });

        button.setOnMouseExited(e -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
            }
        });
    }

    private void applyButtonStyle(Button button) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        }
        button.setStyle(
            "-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 30px; " +
            "-fx-border-color: #283593; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 30px; " +
            "-fx-effect: dropshadow(gaussian, #283593, 10, 0.5, 0, 0);"
        );
        selectedButton = button;
    }

    @FXML
    private void ButtonDashboard(ActionEvent event) {
        mainPane.setCenter(pane);
        applyButtonStyle(dashboard);
    }

    @FXML
    private void ButtonMedicines(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/MedicinesPage.fxml"));
            Pane catalogPage = loader.load();
            mainPane.setCenter(catalogPage);
            applyButtonStyle(medicines);
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
            applyButtonStyle(users);
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
            applyButtonStyle(create);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonUpdate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/UpdatePage.fxml"));
            Pane consultPage = loader.load();
            mainPane.setCenter(consultPage);
            applyButtonStyle(update);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonDelete(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/DeletePage.fxml"));
            Pane consultPage = loader.load();
            mainPane.setCenter(consultPage);
            applyButtonStyle(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/LogoutPage.fxml"));
            Pane logoutPage = loader.load();
            mainPane.setCenter(logoutPage);
            applyButtonStyle(Logout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void performSearch(ActionEvent event) {
        // Implement search functionality here
    }

    private void hideNoResultsOverlay() {
        // Implement hide no results overlay functionality here
    }
}
