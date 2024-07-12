package MediSync;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import MediSync.MediSync_Model.CurrentUser;
import MediSync.MediSync_Model.UserData;
import MediSync.MediSync_Model.UserDataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditProfileController implements Initializable {
    private UserDataManager userDataManager = new UserDataManager();
    private UserData currentUser;

    @FXML
    private Pane pane;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField dateOfBirthTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = CurrentUser.getInstance().getCurrentUser();
        loadUserData();
    }

    private void loadUserData() {
        if (currentUser != null) {
            emailTextField.setText(currentUser.getEmail());
            phoneNumberTextField.setText(currentUser.getPhoneNumber());
            dateOfBirthTextField.setText(currentUser.getDateOfBirth());
            genderTextField.setText(currentUser.getGender());
            addressTextField.setText(currentUser.getAddress());
        }
    }

    @FXML
    private void handleSaveButton(ActionEvent event) {
        // Update current user data with inputs
        currentUser.setEmail(emailTextField.getText());
        currentUser.setPhoneNumber(phoneNumberTextField.getText());
        currentUser.setDateOfBirth(dateOfBirthTextField.getText());
        currentUser.setGender(genderTextField.getText());
        currentUser.setAddress(addressTextField.getText());

        // Update user data in UserDataManager
        userDataManager.updateUser(currentUser);

        // Navigate back to profile page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/ProfilePage.fxml"));
            Pane cartPage = loader.load();
            pane.getChildren().setAll(cartPage);
        } catch (IOException e) {
            System.out.println("Error loading cart page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        navigateToProfile(event);
    }

    private void navigateToProfile(ActionEvent event) {
        OpenScene openScene = new OpenScene();
        Pane page = openScene.getPane("Profile");
        if (page != null) {
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } else {
            System.out.println("Failed to load the profile page.");
        }
    }
}
