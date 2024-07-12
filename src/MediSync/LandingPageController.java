package MediSync;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class LandingPageController {

    private OpenScene openScene = new OpenScene();

    @FXML
    private Button Home;

    @FXML
    private Button Catalog;

    @FXML
    private Button HealthTips;

    @FXML
    private Button Consult;

    @FXML
    private Button Profile;

    @FXML
    private Button Logout;

    @FXML
    private Pane pane;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox prescribedbox;
    @FXML
    private TableView<?> prescribedtable;
    @FXML
    private TableColumn<?, ?> medicineNameColumn;
    @FXML
    private TableColumn<?, ?> instructionsColumn;
    @FXML
    private LineChart<String, Number> bloodchart;
    @FXML
    private CategoryAxis datexaxis;
    @FXML
    private NumberAxis bloodyaxis;
    @FXML
    private VBox userhealthbox;
    @FXML
    private Button notification;

    private Popup popup;

    private XYChart.Series<String, Number> series;
    private LocalDate startDate;
    private Random random;

    private Button selectedButton;

    @FXML
    public void initialize() {
        datexaxis.setLabel("Date");
        bloodyaxis.setLabel("Blood Pressure Level");

        series = new XYChart.Series<>();
        series.setName("Blood Pressure Levels");

        bloodchart.getData().add(series);

        startDate = LocalDate.now().minusDays(6);
        random = new Random();

        long daysBetween = ChronoUnit.DAYS.between(startDate, LocalDate.now());

        for (int i = 0; i <= daysBetween; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); // Format date
            addDataPoint(formattedDate, random.nextInt(80) + 100); // Use a realistic blood pressure range
        }

        setupNotificationPopup();

        // Initialize selected button effect
        initializeButtonEffects();
        applyButtonStyle(Home);
    }

    private long dateToNumber(LocalDate date) {
        return date.toEpochDay();
    }

    private void addDataPoint(String date, int value) {
        series.getData().add(new XYChart.Data<>(date, value));
    }

    private void initializeButtonEffects() {
        // Set initial style for buttons
        Home.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-text-fill: white;");
        Catalog.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        HealthTips.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Consult.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Profile.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Logout.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        selectedButton = Home;

        // Add hover effect
        addHoverEffect(Home);
        addHoverEffect(Catalog);
        addHoverEffect(HealthTips);
        addHoverEffect(Consult);
        addHoverEffect(Profile);
        addHoverEffect(Logout);
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(e -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: #757DE8; -fx-text-fill: white; -fx-background-radius: 10px; -fx-cursor: hand;");
            }
        });

        button.setOnMouseExited(e -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
            }
        });
    }

    private void applyButtonStyle(Button button) {
        if (selectedButton != null) {
            // Revert style of previously selected button
            selectedButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        }
        // Apply style to currently selected button
        button.setStyle(
            "-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 30px; " +
            "-fx-border-color: #283593; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 30px; " +
            "-fx-effect: dropshadow(gaussian, #283593, 10, 0.5, 0, 0);"
        );
        selectedButton = button;
    }

    @FXML
    private void ButtonHome(ActionEvent event) {
        mainPane.setCenter(pane);
        applyButtonStyle(Home);
    }

    @FXML
    private void ButtonCatalog(ActionEvent event) {
        System.out.println("Catalog Button Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/CatalogPage.fxml"));
            Pane catalogPage = loader.load();
            mainPane.setCenter(catalogPage);
            applyButtonStyle(Catalog);
        } catch (IOException e) {
            System.out.println("Error loading catalog page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonHealthTips(ActionEvent event) {
        System.out.println("Health Tips Button Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/HealthTips.fxml"));
            ScrollPane healthTipsPage = loader.load();
            mainPane.setCenter(healthTipsPage);
            applyButtonStyle(HealthTips);
        } catch (IOException e) {
            System.out.println("Error loading health tips page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonConsult(ActionEvent event) {
        System.out.println("Consult Button Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/ConsultPage.fxml"));
            Pane consultPage = loader.load();
            mainPane.setCenter(consultPage);
            applyButtonStyle(Consult);
        } catch (IOException e) {
            System.out.println("Error loading consult page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonProfile(ActionEvent event) {
        System.out.println("Profile Button Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/ProfilePage.fxml"));
            Pane profilePage = loader.load();
            mainPane.setCenter(profilePage);
            applyButtonStyle(Profile);
        } catch (IOException e) {
            System.out.println("Error loading profile page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ButtonLogout(ActionEvent event) {
        System.out.println("Logout Button Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MediSync/LogoutPage.fxml")); // Correct path to FXML
            Pane logoutPage = loader.load();
            mainPane.setCenter(logoutPage); // Setting the logout page at the center
            applyButtonStyle(Logout);
        } catch (IOException e) {
            System.out.println("Error loading logout page: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for detailed debug information
        }
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
}
