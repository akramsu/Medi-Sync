package MediSync.MediSync_Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.*;
import java.util.List;

public class UserDataManager {
    private DataArray userDataArray;
    private XStream xstream;
    private static final String FILE_PATH = "src/MediSync/MediSync_Model/users.xml";

    public UserDataManager() {
        userDataArray = new DataArray(100); // Example size
        xstream = new XStream(new StaxDriver());
        configureXStreamSecurity();
        loadUserData();
    }

    private void configureXStreamSecurity() {
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypesByWildcard(new String[]{"MediSync.**"});
        xstream.alias("user", UserData.class);
        xstream.alias("dataArray", DataArray.class);
        xstream.omitField(DataArray.class, "idx"); // Omit the idx field
    }

    public void addUser(UserData user) {
        userDataArray.addUserData(user);
        saveUserData();
    }

    public void removeUser(String email) {
        userDataArray.removeUserData(email);
        saveUserData();
    }

    public void updateUser(UserData user) {
        userDataArray.addUserData(user); // addUserData will update if user exists
        saveUserData();
    }

    public List<UserData> getAllUsers() {
        return userDataArray.getCollectedData();
    }

    private void saveUserData() {
        System.out.println("Saving user data...");
        System.out.println("File path: " + FILE_PATH);
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8")) {
            xstream.toXML(userDataArray, writer);
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    private void loadUserData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(fis, "UTF-8")) {
                userDataArray = (DataArray) xstream.fromXML(reader);
            } catch (IOException e) {
                System.err.println("Error loading user data: " + e.getMessage());
            }
        } else {
            System.err.println("No existing user data file found. A new one will be created upon saving data.");
        }
    }
}
