package MediSync.MediSync_Model;

import java.util.ArrayList;
import java.util.List;

public class DataArray {
    private List<UserData> collectedData;

    // Constructor with size parameter
    public DataArray(int size) {
        this.collectedData = new ArrayList<>(size);
    }

    // Add a user data to the array
    public void addUserData(UserData userData) {
        boolean userExists = false;
        for (int i = 0; i < collectedData.size(); i++) {
            if (collectedData.get(i).getEmail().equals(userData.getEmail())) {
                collectedData.set(i, userData);
                userExists = true;
                break;
            }
        }
        if (!userExists) {
            collectedData.add(userData);
        }
    }

    // Remove a user data from the array by email
    public void removeUserData(String email) {
        collectedData.removeIf(userData -> userData.getEmail().equals(email));
    }

    // Get the collected data
    public List<UserData> getCollectedData() {
        return collectedData;
    }
}
