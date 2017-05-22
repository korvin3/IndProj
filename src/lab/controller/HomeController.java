package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import lab.GoodsReportGenerator;
import lab.View;
import lab.util.FXUtils;

import java.io.File;

/**
 * Created by Kristina Riadchenko on 21.05.2017.
 */
public class HomeController extends FXController {
    public Button newOrderBtn;
    public Button goodsBtn;
    public Button agentsBtn;
    public Button mainDevBtn;
    public Button deliveriesBtn;
    public Button generateReportBtn;

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

    public void generateGoodsReport(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("report.csv");
        fileChooser.setTitle("Report file name");
        File file = fileChooser.showSaveDialog(getStage());
        if (file != null) {
            GoodsReportGenerator.generateReport(file);
        }
    }
}