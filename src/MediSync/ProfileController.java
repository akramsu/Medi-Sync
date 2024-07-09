package MediSync;

import java.net.URL;
import java.util.ResourceBundle;
import MediSync.MediSync_Model.UserData;
import MediSync.MediSync_Model.UserDataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ProfileController implements Initializable {
    private UserDataManager userDataManager = new UserDataManager();
    private OpenScene openScene = new OpenScene();

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Button editButton;

    private UserData currentUser;

    @FXML
    private ImageView image;
    @FXML
    private Button notification;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieve the current user data (assuming the last user added is the one to display)
        if (!userDataManager.getAllUsers().isEmpty()) {
            currentUser = userDataManager.getAllUsers().get(userDataManager.getAllUsers().size() - 1);
            displayUserData();
            setupNotificationPopup();
        }
    }

    private void displayUserData() {
        if (currentUser != null) {
            emailLabel.setText(currentUser.getEmail());
            phoneNumberLabel.setText(currentUser.getPhoneNumber());
            dateOfBirthLabel.setText(currentUser.getDateOfBirth());
            genderLabel.setText(currentUser.getGender());
            addressLabel.setText(currentUser.getAddress());
        }
    }

    @FXML
    private void handleEditButton(ActionEvent event) {
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
}
