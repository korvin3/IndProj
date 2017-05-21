package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import lab.View;
import lab.util.FXUtils;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class GoodsController extends FXController {
    public Button backBtn;

    public void goBack(ActionEvent actionEvent) {
        getStage().getScene().setRoot(FXUtils.loadView(View.Home, getStage()));
    }
}