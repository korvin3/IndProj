package lab.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Kristina Riadchenko on 21.05.2017.
 */
public class FXController {
    private Stage stage;

    @FXML
    public void initialize() {
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
