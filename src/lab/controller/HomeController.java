package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import lab.View;
import lab.util.CommonUtils;
import lab.util.FXUtils;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class HomeController extends FXController {
    public Button newOrderBtn;
    public Button goodsBtn;
    public Button agentsBtn;
    public Button ordersBtn;
    public Button reportsBtn;
    public Button mainDevBtn;

    public void showNewOrder(ActionEvent actionEvent) {
    }

    public void showGoods(ActionEvent actionEvent) {
        CommonUtils.print("showing goods view...");
        Parent goods = FXUtils.loadView(View.Goods);
        getStage().getScene().setRoot(goods);
    }

    public void showAgents(ActionEvent actionEvent) {
    }

    public void showOrders(ActionEvent actionEvent) {
    }

    public void showReports(ActionEvent actionEvent) {
    }

    public void showMain(ActionEvent actionEvent) {
        getStage().getScene().setRoot(FXUtils.loadView(View.Main));
    }
}
