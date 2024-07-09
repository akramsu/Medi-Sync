package MediSync.MediSync_Model;

import java.util.ArrayList;
import java.util.List;

public class DataArray {
    private int idx;
    private List<UserData> collectedData;

    // Constructor with size parameter
    public DataArray(int size) {
        this.idx = 0;
        this.collectedData = new ArrayList<>(size);
    }

    // Add a user data to the array
    public void addUserData(UserData userData) {
        if (idx < collectedData.size()) {
            collectedData.set(idx, userData);
        } else {
            collectedData.add(userData);
        }
        idx++;
    }

    // Get the collected data
    public List<UserData> getCollectedData() {
        return collectedData;
    }
}
