package MediSync;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import MediSync.MediSync_Model.UserData;
import MediSync.MediSync_Model.UserDataManager;

public class UsersController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<UserData> userTable;
    @FXML
    private Text resultText;

    @FXML
    private TableColumn<UserData, String> userEmail;
    @FXML
    private TableColumn<UserData, String> userPhone;
    @FXML
    private TableColumn<UserData, String> userDOB;
    @FXML
    private TableColumn<UserData, String> userGender;
    @FXML
    private TableColumn<UserData, String> userAddress;

    private ObservableList<UserData> allUsers;
    private UserDataManager userDataManager = new UserDataManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        userDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        userGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        searchBar.setOnKeyPressed(this::handleEnterPressed);
        searchButton.setOnAction(this::performSearch);
        pane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        loadUserData();
    }

    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            performSearch(null);
        }
    }

    private void loadUserData() {
        allUsers = FXCollections.observableArrayList(userDataManager.getAllUsers());
        userTable.setItems(allUsers);
        resultText.setText(allUsers.isEmpty() ? "No data available." : "");
    }

    @FXML
    public void performSearch(ActionEvent event) {
        String searchQuery = searchBar.getText().trim().toLowerCase();

        // Reload original data before filtering
        loadUserData();

        ObservableList<UserData> filteredData = allUsers.stream()
                                                        .filter(user -> user.getEmail().toLowerCase().startsWith(searchQuery))
                                                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
        if (filteredData.isEmpty()) {
            resultText.setText("No similar results found.");
            userTable.setItems(FXCollections.observableArrayList()); // Ensure the table is clear when no results are found
        } else {
            resultText.setText("");
            userTable.setItems(filteredData);
        }
    }
}
