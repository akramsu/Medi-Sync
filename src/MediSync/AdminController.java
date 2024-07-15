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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Pane noResultsOverlay;

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
    private ObservableList<OrderData> originalData;

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

        applyButtonStyle(dashboard);
        searchBar.setOnKeyPressed(this::handleKeyPressed);
        searchButton.setOnAction(this::performSearch);
    }

    private void loadOrders() {
        List<OrderData> orders = orderDataManager.getAllOrders();
        originalData = FXCollections.observableArrayList(orders);
        ordersTableView.setItems(originalData);
    }

    private void initializeButtonEffects() {
        dashboard.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-text-fill: #0d134b;");
        medicines.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        users.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        create.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        update.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        delete.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");
        Logout.setStyle("-fx-background-color: transparent; -fx-text-fill: #0d134b;");

        selectedButton = dashboard;
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
        String searchQuery = searchBar.getText().trim().toLowerCase();
        ObservableList<OrderData> filteredData = FXCollections.observableArrayList();

        ordersTableView.setItems(originalData);  // Reload original data before filtering

        for (OrderData order : originalData) {
            if (order.getUserEmail().toLowerCase().startsWith(searchQuery)) {
                filteredData.add(order);
            }
        }

        ordersTableView.setItems(filteredData);
        noResultsOverlay.setVisible(filteredData.isEmpty());
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            performSearch(new ActionEvent());
        }
    }


    private void hideNoResultsOverlay() {
        noResultsOverlay.setVisible(false);
    }
}