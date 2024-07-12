package MediSync.MediSync_Model;

import java.time.LocalDate;

public class OrderData {
    private String orderId;
    private String userEmail;
    private String medicineName;
    private int quantity;
    private double totalPrice;
    private LocalDate orderDate;

    public OrderData() {
        this.orderId = "";
        this.userEmail = "";
        this.medicineName = "";
        this.quantity = 0;
        this.totalPrice = 0.0;
        this.orderDate = LocalDate.now();
    }

    public OrderData(String orderId, String userEmail, String medicineName, int quantity, double totalPrice, LocalDate orderDate) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    // ...

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
