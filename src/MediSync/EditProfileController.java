package MediSync;

import java.net.URL;
import java.util.ResourceBundle;
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
import java.io.IOException;

public class EditProfileController implements Initializable {
    private UserDataManager userDataManager = new UserDataManager();
    private UserData currentUser;

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

    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    private void handleSaveButton(ActionEvent event) {
        
    }
}
// package MediSync;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ResourceBundle;
// import MediSync.MediSync_Model.UserData;
// import MediSync.MediSync_Model.UserDataManager;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Button;
// import javafx.event.ActionEvent;
// import javafx.scene.layout.Pane;
// import javafx.scene.layout.BorderPane;

// public class EditProfileController implements Initializable {
//     private UserDataManager userDataManager = new UserDataManager();
//     private UserData currentUser;

//     @FXML
//     private TextField emailTextField;

//     @FXML
//     private TextField phoneNumberTextField;

//     @FXML
//     private TextField dateOfBirthTextField;

//     @FXML
//     private TextField genderTextField;

//     @FXML
//     private TextField addressTextField;

//     @FXML
//     private Button saveButton;

//     private BorderPane mainPane;

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         currentUser = userDataManager.getCurrentUser();
//         if (currentUser != null) {
//             populateUserData();
//         }
//     }

//     private void populateUserData() {
//         emailTextField.setText(currentUser.getEmail());
//         phoneNumberTextField.setText(currentUser.getPhoneNumber());
//         dateOfBirthTextField.setText(currentUser.getDateOfBirth());
//         genderTextField.setText(currentUser.getGender());
//         addressTextField.setText(currentUser.getAddress());
//     }

//     public void setMainPane(BorderPane mainPane) {
//         this.mainPane = mainPane;
//     }

//     @FXML
//     private void handleSaveButton(ActionEvent event) {
//         if (currentUser != null) {
//             currentUser.setEmail(emailTextField.getText());
//             currentUser.setPhoneNumber(phoneNumberTextField.getText());
//             currentUser.setDateOfBirth(dateOfBirthTextField.getText());
//             currentUser.setGender(genderTextField.getText());
//             currentUser.setAddress(addressTextField.getText());
//             userDataManager.saveUserData();

//             try {
//                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/ProfilePage.fxml"));
//                 Pane profilePage = loader.load();

//                 ProfileController profileController = loader.getController();
//                 profileController.setMainPane(mainPane);
//                 profileController.displayUserData(); // Update user data on profile page

//                 mainPane.getChildren().setAll(profilePage);
//             } catch (IOException e) {
//                 System.err.println("Exception while loading the profile page: " + e.getMessage());
//                 e.printStackTrace();
//             }
//         }
//     }
// }

// package MediSync;

// import java.net.URL;
// import java.util.ResourceBundle;
// import MediSync.MediSync_Model.UserData;
// import MediSync.MediSync_Model.UserDataManager;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.Pane;
// import javafx.scene.control.Button;
// import javafx.event.ActionEvent;
// import javafx.scene.layout.BorderPane;
// import java.io.IOException;

// public class EditProfileController implements Initializable {
//     private UserDataManager userDataManager = new UserDataManager();
//     private UserData currentUser;

//     @FXML
//     private TextField emailTextField;

//     @FXML
//     private PasswordField passwordTextField;

//     @FXML
//     private TextField phoneNumberTextField;

//     @FXML
//     private TextField dateOfBirthTextField;

//     @FXML
//     private TextField genderTextField;

//     @FXML
//     private TextField addressTextField;

//     @FXML
//     private Button saveButton;

//     private BorderPane mainPane;

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         currentUser = userDataManager.getCurrentUser();
//         if (currentUser != null) {
//             populateUserData();
//         }
//     }

//     private void populateUserData() {
//         emailTextField.setText(currentUser.getEmail());
//         passwordTextField.setText(currentUser.getPassword());
//         phoneNumberTextField.setText(currentUser.getPhoneNumber());
//         dateOfBirthTextField.setText(currentUser.getDateOfBirth());
//         genderTextField.setText(currentUser.getGender());
//         addressTextField.setText(currentUser.getAddress());
//     }

//     public void setMainPane(BorderPane mainPane) {
//         this.mainPane = mainPane;
//     }

//     public void setUserData(UserData userData) {
//         currentUser = userData;
//         populateUserData();
//     }

//     @FXML
//     private void handleSaveButton(ActionEvent event) {
//         if (currentUser != null) {
//             currentUser.setEmail(emailTextField.getText());
//             currentUser.setPassword(passwordTextField.getText());
//             currentUser.setPhoneNumber(phoneNumberTextField.getText());
//             currentUser.setDateOfBirth(dateOfBirthTextField.getText());
//             currentUser.setGender(genderTextField.getText());
//             currentUser.setAddress(addressTextField.getText());
            
//             // Update the user data in the ArrayList and save to XML
//             userDataManager.updateUserData(currentUser);
//             userDataManager.saveUserData();

//             // Go back to the profile page after saving
//             try {
//                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/ProfilePage.fxml"));
//                 Pane profilePage = loader.load();

//                 // Pass the mainPane to the ProfileController
//                 ProfileController profileController = loader.getController();
//                 profileController.setMainPane(mainPane);
                
//                 // Re-display the updated user data
//                 profileController.displayUserData(currentUser);

//                 mainPane.setCenter(profilePage);
//             } catch (IOException e) {
//                 System.err.println("Exception while loading the profile page: " + e.getMessage());
//                 e.printStackTrace();
//             }
//         }
//     }
// }

