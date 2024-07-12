package MediSync;

import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import MediSync.MediSync_Model.CurrentUser;
import MediSync.MediSync_Model.UserData;
import MediSync.MediSync_Model.UserDataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    private UserDataManager userDataManager = new UserDataManager();
    private OpenScene openScene = new OpenScene();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label dateOfBirth;

    @FXML
    private DatePicker date;

    @FXML
    private Label gender;

    @FXML
    private ChoiceBox<String> genderBox;
    private String[] choices = {"Male", "Female"};

    @FXML
    private TextField address;

    @FXML
    private Button signUpButton;

    @FXML
    private Text text;

    @FXML
    public void getDateOfBirth(ActionEvent event) {
        LocalDate myDate = date.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        dateOfBirth.setText(myFormattedDate);
    }

    @FXML
    public void getGender(ActionEvent event) {
        String theGender = genderBox.getValue();
        gender.setText(theGender);
    }

    @FXML
    private void handleButtonSignUp(ActionEvent event) {
        // Retrieve user input
        String emailInput = email.getText();
        String passwordInput = password.getText();
        String phoneInput = phoneNumber.getText();
        String dobInput = dateOfBirth.getText();
        String genderInput = gender.getText();
        String addressInput = address.getText();

        // Validate input
        if (!isValidEmail(emailInput)) {
            showAlert(Alert.AlertType.WARNING, "SIGN UP Error", "Invalid email format.");
            return;
        }
        if (!isValidPhoneNumber(phoneInput)) {
            showAlert(Alert.AlertType.WARNING, "SIGN UP Error", "Invalid phone number. Only digits are allowed.");
            return;
        }
        if (passwordInput.isEmpty() || dobInput.isEmpty() || genderInput.isEmpty() || addressInput.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "SIGN UP Error", "Please fill in all fields.");
            return;
        }

        // Create UserData object
        UserData userData = new UserData(emailInput, passwordInput, phoneInput, dobInput, genderInput, addressInput);

        // Add user using UserDataManager
        userDataManager.addUser(userData);

        // Set the current user
        CurrentUser.getInstance().setCurrentUser(userData);

        // Load the landing page
        Pane page = openScene.getPane("LandingPage");
        if (page != null) {
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } else {
            System.out.println("Failed to load the landing page.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderBox.getItems().addAll(choices);
        genderBox.setOnAction(this::getGender);

        // Add event handlers for Enter key
        email.setOnKeyPressed(this::handleKeyPressed);
        password.setOnKeyPressed(this::handleKeyPressed);
        phoneNumber.setOnKeyPressed(this::handleKeyPressed);
        address.setOnKeyPressed(this::handleKeyPressed);
        date.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            signUpButton.fire();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d+");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}