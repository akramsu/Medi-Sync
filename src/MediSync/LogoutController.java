package MediSync;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LogoutController {
    @FXML private Pane pane;
    @FXML private Button logout, exit, notification;
    private Popup popup;

    @FXML
    public void initialize() {
        setupNotificationPopup();
    }

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

    @FXML
    public void ButtonLogout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText(null);
            
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                System.out.println("You successfully logged out!");

                // Load the Welcome Page
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MediSync/WelcomePage.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading Welcome Page: " + e.getMessage());
        }
    }


    @FXML
    public void ButtonExit(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText(null);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

}
