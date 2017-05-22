package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import lab.View;
import lab.util.FXUtils;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class HomeController extends FXController {
    public Button newOrderBtn;
    public Button goodsBtn;
    public Button agentsBtn;
    public Button mainDevBtn;
    public Button deliveriesBtn;

    public void showNewOrder(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.NewDelivery);
    }

    public void showGoods(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Goods);
    }

    public void showAgents(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Agents);
    }

    public void showMain(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Main);
    }

    public void showDeliveries(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Deliveries);
    }
}
