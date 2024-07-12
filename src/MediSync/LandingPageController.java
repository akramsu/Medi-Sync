package MediSync;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import MediSync.MediSync_Model.CurrentUser;
import MediSync.MediSync_Model.OrderData;
import MediSync.MediSync_Model.OrderDataManager;
import MediSync.MediSync_Model.UserData;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.ResourceBundle;

public class LandingPageController implements Initializable {

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
    private Pane pn;
    @FXML
    private WebView webView;

    @FXML
    private BorderPane mainPane;

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
    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label selectedDateLabel; // Label to display selected date

    private Popup popup;

    private XYChart.Series<String, Number> series;
    private LocalDate startDate;
    private Random random;

    private Button selectedButton;
    private OrderDataManager orderDataManager;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
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

        // Initialize OrderDataManager
        orderDataManager = new OrderDataManager();
        displayLatestPurchase();

        // Draw the calendar
        drawCalendar();
    }

    private void addDataPoint(String date, int value) {
        series.getData().add(new XYChart.Data<>(date, value));
    }

    private void initializeButtonEffects() {
        Home.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-text-fill: white;");
        Catalog.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        HealthTips.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Consult.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Profile.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Logout.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        selectedButton = Home;

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
            selectedButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        }
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

    private void displayLatestPurchase() {
        UserData currentUser = CurrentUser.getInstance().getCurrentUser();
        if (currentUser != null) {
            OrderData latestOrder = getLatestOrder(currentUser.getEmail());
            if (latestOrder != null) {
                showOrderDetails(latestOrder);
            } else {
                showNoPurchasesMessage();
            }
        } else {
            showNoPurchasesMessage();
        }
    }

    private OrderData getLatestOrder(String email) {
        return orderDataManager.getAllOrders().stream()
                .filter(order -> order.getUserEmail().equals(email))
                .reduce((first, second) -> second) // Get the last order
                .orElse(null);
    }

    private void showOrderDetails(OrderData order) {
        VBox latestPurchaseBox = new VBox(10); // Spacing between elements
        latestPurchaseBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-background-radius: 30; -fx-padding: 20;");
        latestPurchaseBox.setPrefSize(400, 200); // Set preferred size to ensure it fits the text
    
        Label titleLabel = new Label("Latest Purchase Details");
        titleLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 24px; -fx-font-weight: bold;");
    
        Label medicineName = new Label("Medicine: " + order.getMedicineName());
        Label quantity = new Label("Quantity: " + order.getQuantity());
        Label price = new Label("Total Price: $" + order.getTotalPrice());
        Label orderDate = new Label("Date: " + order.getOrderDate().toString());
    
        styleLabel(medicineName);
        styleLabel(quantity);
        styleLabel(price);
        styleLabel(orderDate);
    
        latestPurchaseBox.getChildren().addAll(titleLabel, medicineName, quantity, price, orderDate);
        latestPurchaseBox.setLayoutX(862); 
        latestPurchaseBox.setLayoutY(235); 
        pn.getChildren().add(latestPurchaseBox);
    }
    
    private void showNoPurchasesMessage() {
        VBox latestPurchaseBox = new VBox(10); // Spacing between elements
        latestPurchaseBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 75% 75%, #0d134b, #3F51B5); -fx-background-radius: 30; -fx-padding: 20;");
        latestPurchaseBox.setPrefSize(400, 200); // Set preferred size to match the purchase box
    
        Label noPurchasesLabel = new Label("No purchases have been made");
        noPurchasesLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 24px; -fx-font-weight: bold;");
        latestPurchaseBox.getChildren().add(noPurchasesLabel);
        latestPurchaseBox.setLayoutX(862); 
        latestPurchaseBox.setLayoutY(235);
        pn.getChildren().add(latestPurchaseBox);
    }
    
    private void styleLabel(Label label) {
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
    }      

        // Calendar Drawing Methods
    private void drawCalendar() {
        YearMonth yearMonth = YearMonth.now();
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        int daysInMonth = yearMonth.lengthOfMonth();

        String[] weekDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        // Adding the headers
        for (int i = 0; i < weekDays.length; i++) {
            Label label = new Label(weekDays[i]);
            label.setFont(new Font("Segoe UI", 16));
            label.setTextFill(Color.web("#0d134b"));
            calendarGrid.add(label, i, 0);
        }

        int col = dayOfWeek - 1;
        int row = 1;

        for (int day = 1; day <= daysInMonth; day++) {
            final int currentDay = day; // Declare currentDay as final
            Button button = new Button(String.valueOf(day));
            button.setPrefSize(50, 50);
            button.setStyle("-fx-background-color: #E3F2FD; -fx-background-radius: 10;");
            button.setOnAction(e -> {
                highlightDate(button);
                selectedDateLabel.setText("Selected Date: " + currentDay + " " + yearMonth.getMonth() + " " + yearMonth.getYear());
            });

            calendarGrid.add(button, col, row);
            col++;

            if (col > 6) {
                col = 0;
                row++;
            }
        }

        // Set the layout Y coordinate of the calendarGrid to -30
        calendarGrid.setLayoutY(250);
    }

    private void highlightDate(Button button) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: #E3F2FD; -fx-background-radius: 10;");
        }
        button.setStyle("-fx-background-color: #0d134b; -fx-background-radius: 10; -fx-text-fill: white;");
        selectedButton = button;
    }

}
