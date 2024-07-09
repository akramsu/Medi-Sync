package MediSync;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class WelcomePageController implements Initializable {
    @FXML
    private VBox vbox;

    private Parent signUpPage;
    private Parent signInPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpPage = loadFXML("/MediSync/SignUp.fxml");
        signInPage = loadFXML("/MediSync/SignIn.fxml");

        if (signUpPage != null) {
            vbox.getChildren().add(signUpPage);
        } else {
            Logger.getLogger(WelcomePageController.class.getName()).log(Level.SEVERE, "signUpPage is null");
        }
    }

    @FXML
    private void handleButtonSignup(ActionEvent event) {
        if (signUpPage != null) {
            transitionToPage(signUpPage, false);
        }
    }

    @FXML
    private void handleButtonSignin(ActionEvent event) {
        if (signInPage != null) {
            transitionToPage(signInPage, true);
        }
    }

    private void transitionToPage(Parent page, boolean toSignin) {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(.7), vbox);
        slide.setToX(toSignin ? -804 : 0);
        slide.setOnFinished(event -> {
            vbox.getChildren().clear();
            vbox.getChildren().add(page);
        });
        slide.play();
    }

    private Parent loadFXML(String fxmlFile) {
        try {
            URL resource = getClass().getResource(fxmlFile);
            if (resource == null) {
                Logger.getLogger(WelcomePageController.class.getName()).log(Level.SEVERE, "Cannot find FXML file: " + fxmlFile);
                return null;
            }
            return FXMLLoader.load(resource);
        } catch (IOException e) {
            Logger.getLogger(WelcomePageController.class.getName()).log(Level.SEVERE, "Failed to load FXML file: " + fxmlFile, e);
            return null;
        }
    }
}
