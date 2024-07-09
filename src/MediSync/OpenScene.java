package MediSync;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class OpenScene {
    public Pane getPane(String fileName) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(fileName + ".fxml"));
        } catch (Exception e) {
            System.out.println("No such FXML file found.");
            e.printStackTrace();
        }
        return pane;
    }
}
