package MediSync;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import MediSync.MediSync_Model.CurrentUser;
import MediSync.MediSync_Model.OrderData;
import MediSync.MediSync_Model.OrderDataManager;
import MediSync.MediSync_Model.UserData;

import java.net.URL;
import java.util.ResourceBundle;

public class Cart implements Initializable {

    @FXML
    private VBox vBox;

    private OrderDataManager orderDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderDataManager = new OrderDataManager();
        displayLatestPurchase();
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
        vBox.getChildren().clear();

        Label titleLabel = new Label("Latest Purchase Details");
        titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 36px; -fx-font-weight: bold;");

        Label medicineName = new Label("Medicine: " + order.getMedicineName());
        Label quantity = new Label("Quantity: " + order.getQuantity());
        Label price = new Label("Total Price: $" + order.getTotalPrice());
        Label orderDate = new Label("Date: " + order.getOrderDate().toString());

        styleLabel(medicineName);
        styleLabel(quantity);
        styleLabel(price);
        styleLabel(orderDate);

        vBox.getChildren().addAll(titleLabel, medicineName, quantity, price, orderDate);
    }

    private void showNoPurchasesMessage() {
        vBox.getChildren().clear();
        Label noPurchasesLabel = new Label("No purchases have been made.");
        styleLabel(noPurchasesLabel);
        vBox.getChildren().add(noPurchasesLabel);
    }

    private void styleLabel(Label label) {
        label.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 24px;");
    }
}
