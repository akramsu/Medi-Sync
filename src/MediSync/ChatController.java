package MediSync;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ChatController {

    @FXML
    private WebView webView;

    public void initialize() {
        WebEngine webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case SUCCEEDED:
                    System.out.println("Page loaded successfully");
                    break;
                case FAILED:
                    System.out.println("Failed to load page");
                    break;
                case CANCELLED:
                    System.out.println("Page loading cancelled");
                    break;
            }
        });

        String htmlContent = "<html>"
                + "<head>"
                + "<script src=\"https://chatling.ai/js/embed.js\"></script>"
                + "</head>"
                + "<body><p>Loading AI Chat...</p></body>"
                + "</html>";
        webEngine.loadContent(htmlContent);
    }
}
