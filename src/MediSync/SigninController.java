package MediSync;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import MediSync.MediSync_Model.UserData;
import MediSync.MediSync_Model.UserDataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SigninController implements Initializable {
    private UserDataManager userDataManager = new UserDataManager();
    private OpenScene openScene = new OpenScene();

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button signin;

    @FXML
    public void handleButtonSignin(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user.equals("admin") && pass.equals("admin")) {
            loadScene(event, "AdminPage");
        } else if (isValidUser(user, pass)) {
            loadScene(event, "LandingPage");
        } else {
            showAlert(Alert.AlertType.WARNING, "Signin Error", "Invalid username or password.");
        }
    }

    private boolean isValidUser(String user, String pass) {
        List<UserData> users = userDataManager.getAllUsers();
        for (UserData u : users) {
            if (u.getEmail().equals(user) && u.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    private void loadScene(ActionEvent event, String pageName) {
        Pane page = openScene.getPane(pageName);
        if (page != null) {
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } else {
            System.out.println("Failed to load the " + pageName + ".");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code if needed
    }
}
