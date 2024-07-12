package MediSync.MediSync_Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDataManager {
    private List<OrderData> orderList;
    private XStream xstream;
    private static final String FILE_PATH = "src/MediSync/MediSync_Model/orders.xml";

    public OrderDataManager() {
        orderList = new ArrayList<>();
        xstream = new XStream(new StaxDriver());
        configureXStreamSecurity();
        loadOrderData();
    }

    private void configureXStreamSecurity() {
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypesByWildcard(new String[] {
            "MediSync.**",
            "java.util.List",
            "java.util.ArrayList"
        });
        xstream.alias("order", OrderData.class);
        xstream.alias("list", List.class);
    }

    public void addOrder(OrderData order) {
        orderList.add(order);
        saveOrderData();
    }

    public void removeOrder(String orderId) {
        orderList.removeIf(order -> order.getOrderId().equals(orderId));
        saveOrderData();
    }

    public List<OrderData> getAllOrders() {
        return orderList;
    }

    private void saveOrderData() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8")) {
            xstream.toXML(orderList, writer);
            System.out.println("Order data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving order data: " + e.getMessage());
        }
    }

    private void loadOrderData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(fis, "UTF-8")) {
                orderList = (List<OrderData>) xstream.fromXML(reader);
                System.out.println("Order data loaded successfully.");
            } catch (IOException e) {
                System.err.println("Error loading order data: " + e.getMessage());
            }
        } else {
            System.err.println("No existing order data file found. A new one will be created upon saving data.");
        }
    }
}
