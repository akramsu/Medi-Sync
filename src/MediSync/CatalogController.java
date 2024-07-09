package MediSync;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import MediSync.MediSync_Model.MedicinesData;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class CatalogController {

    @FXML
    private Pane pane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button cart;
    @FXML
    private Button notification;

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




    @FXML
    public void ButtonCart(ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/CartPage.fxml"));
                Pane cartPage = loader.load();
                pane.getChildren().setAll(cartPage);
            } catch (IOException e) {
                System.out.println("Error loading cart page: " + e.getMessage());
                e.printStackTrace();
            }
    }

    public void initialize() {
        MedName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        MedForm.setCellValueFactory(new PropertyValueFactory<>("medicineForm"));
        MedDesc.setCellValueFactory(new PropertyValueFactory<>("medicineDescription"));
        MedExp.setCellValueFactory(new PropertyValueFactory<>("medicineExpirationDate"));
        MedPrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));
        MedQuant.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));

        loadMedicinesData();
        setupNotificationPopup();
        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    }

    private void loadMedicinesData() {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("medicine", MedicinesData.class);
            xstream.alias("list", List.class);
            FileReader fileReader = new FileReader("src/MediSync/MediSync_Model/medicines.xml");
            List<MedicinesData> medicinesList = (List<MedicinesData>) xstream.fromXML(fileReader);
            ObservableList<MedicinesData> data = FXCollections.observableArrayList(medicinesList);
            MedTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private Popup popup;

    private void setupNotificationPopup() {
        popup = new Popup();
        Label messageLabel = new Label(getHealthTips());
        messageLabel.setFont(new Font("Segoe UI", 18));
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setWrapText(true);

        VBox layout = new VBox(messageLabel);
        layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-padding: 20; -fx-background-radius: 10;");
        layout.setPrefSize(300, 200);

        popup.getContent().add(layout);
        popup.setAutoHide(true);
    }

    private String getHealthTips() {
        return "Remember to take your medication at 8 PM tonight.";
    }

    @FXML
    public void ButtonNotification(ActionEvent event) {
        if (!popup.isShowing()) {
            popup.show(notification,
                notification.localToScreen(notification.getBoundsInLocal()).getMinX(),
                notification.localToScreen(notification.getBoundsInLocal()).getMaxY());
        } else {
            popup.hide();
        }
    }
    private void hideNoResultsOverlay() {
        noResultsOverlay.setVisible(false);
    }


}
