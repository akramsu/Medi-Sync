package MediSync;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class ChatController {

    @FXML
    private WebView webView;

    @FXML
    private Button loadChatButton;

    @FXML
    private Label loadingLabel;

    private WebEngine webEngine;
    private boolean isLoading = false;

    private static final String HTML_CONTENT =
        "<html>" +
        "<head>" +
        "<script>" +
        "console.log('Trying to load external script...');" +
        "</script>" +
        "<script src='https://chatling.ai/js/embed.js' onload=\"initializeChat()\"></script>" +
        "<script>" +
        "function initializeChat() {" +
        "console.log('External script loaded.');" +
        "javaApp.chatStarted();" +
        "}" +
        "</script>" +
        "</head>" +
        "<body>" +
        "<div id=\"chat-container\"></div>" +
        "</body>" +
        "</html>";

    public void initialize() {
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case SCHEDULED:
                case RUNNING:
                    isLoading = true;
                    loadingLabel.setVisible(true); // Show loading indicator
                    System.out.println("Page loading in progress: " + newState);
                    break;
                case SUCCEEDED:
                    isLoading = false;
                    loadingLabel.setVisible(false); // Hide loading indicator
                    System.out.println("Page loaded successfully");
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("javaApp", new JavaApp());
                    break;
                case FAILED:
                    isLoading = false;
                    loadingLabel.setVisible(false); // Hide loading indicator
                    System.out.println("Failed to load page");
                    Throwable exception = webEngine.getLoadWorker().getException();
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                    break;
                case CANCELLED:
                    isLoading = false;
                    loadingLabel.setVisible(false); // Hide loading indicator
                    System.out.println("Page loading cancelled");
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    private void loadChat() {
        if (!isLoading) {
            System.out.println("Loading HTML content");
            try {
                webEngine.loadContent(HTML_CONTENT);
            } catch (Exception e) {
                System.out.println("Error loading HTML content: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Previous content load in progress. Skipping new load.");
        }
    }

    public class JavaApp {
        public void chatStarted() {
            System.out.println("Chat has started working");
            showAlert("Chat has started working");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
