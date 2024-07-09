package MediSync;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ConsultController {

    @FXML
    private HBox doctorList;

    @FXML
    private Pane pane;
    
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button notification;

    @FXML
    private Button contactButton;

    private Popup popup;

    @FXML
    public void initialize() {
        configureScrollPane();
        contactButton.setOnAction(this::handleContactButton);
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
    private void configureScrollPane() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
    }

    @FXML
    private void handleContactButton(ActionEvent event) {
        openWhatsAppChat();
    }

    private void openWhatsAppChat() {
        try {
            Desktop.getDesktop().browse(new URI("https://wa.me/+6282210885537"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
