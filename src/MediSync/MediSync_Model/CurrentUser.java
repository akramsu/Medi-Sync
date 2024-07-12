package MediSync.MediSync_Model;

public class CurrentUser {
    private static CurrentUser instance;
    private UserData currentUser;

    private CurrentUser() {}

    public static synchronized CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public void setCurrentUser(UserData user) {
        this.currentUser = user;
    }

    public UserData getCurrentUser() {
        return currentUser;
    }
}
