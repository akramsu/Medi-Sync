package MediSync;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import MediSync.MediSync_Model.MedicinesData;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class MedicinesController {

    @FXML
    private Pane pane;
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

    private ObservableList<MedicinesData> allMedicines;

    public void initialize() {
        MedName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        MedForm.setCellValueFactory(new PropertyValueFactory<>("medicineForm"));
        MedDesc.setCellValueFactory(new PropertyValueFactory<>("medicineDescription"));
        MedExp.setCellValueFactory(new PropertyValueFactory<>("medicineExpirationDate"));
        MedPrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));
        MedQuant.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));

        searchBar.setOnKeyPressed(this::handleEnterPressed);
        searchButton.setOnAction(this::performSearch);
        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        loadMedicinesData();
    }

    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            performSearch(null);
        }
    }

    private void loadMedicinesData() {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("medicine", MedicinesData.class);
            xstream.alias("list", List.class);
            FileReader fileReader = new FileReader("src/MediSync/MediSync_Model/medicines.xml");
            List<MedicinesData> medicinesList = (List<MedicinesData>) xstream.fromXML(fileReader);
            allMedicines = FXCollections.observableArrayList(medicinesList);
            MedTable.setItems(allMedicines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void performSearch(ActionEvent event) {
        String searchQuery = searchBar.getText().trim().toLowerCase();

        // Reload original data before filtering
        MedTable.setItems(allMedicines);

        ObservableList<MedicinesData> filteredData = allMedicines.stream()
                                                                .filter(medicine -> medicine.getMedicineName().toLowerCase().startsWith(searchQuery))
                                                                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        MedTable.setItems(filteredData);
        noResultsOverlay.setVisible(filteredData.isEmpty());
    }

    private void hideNoResultsOverlay() {
        noResultsOverlay.setVisible(false);
    }
}