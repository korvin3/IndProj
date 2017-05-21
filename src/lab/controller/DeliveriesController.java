package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab.DeliveryStatus;
import lab.View;
import lab.datalayer.Delivery;
import lab.service.DeliveryService;
import lab.util.FXUtils;

/**
 * Created by Roman Kolesnik on 22.05.2017.
 */
public class DeliveriesController extends FXController {
    public Button backBtn;
    public TableColumn<Delivery, String> goodColumn;
    public TableColumn<Delivery, String> agentColumn;
    public TableColumn<Delivery, String> warehouseColumn;
    public TableColumn<Delivery, String> typeColumn;
    public TableColumn<Delivery, Integer> quantityColumn;
    public TableColumn<Delivery, String> driverColumn;
    public TableColumn<Delivery, String> statusColumn;
    public TableView<Delivery> deliveriesTableView;
    public Button deliverBtn;
    public Button cancelBtn;

    private Delivery selectedDelivery;

    @Override
    public void initialize() {
        super.initialize();

        goodColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        agentColumn.setCellValueFactory(cellData -> cellData.getValue().agentProperty());
        warehouseColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        driverColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        deliveriesTableView.setItems(DeliveryService.findAll());
        deliveriesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedDelivery = newValue;
                String deliveryStatus = selectedDelivery.getStatus();
                if (DeliveryStatus.Sent.toString().equals(deliveryStatus)) { //very ugly, I know
                    enableButtons();
                } else {
                    disableButtons();
                }
            }
        });
    }

    private void enableButtons() {
        deliverBtn.setDisable(false);
        cancelBtn.setDisable(false);
    }

    private void disableButtons() {
        deliverBtn.setDisable(true);
        cancelBtn.setDisable(true);
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }

    public void deliver(ActionEvent actionEvent) {
        DeliveryService.markAsDelivered(selectedDelivery);
        selectedDelivery.statusProperty().setValue(DeliveryStatus.Delivered.toString());
        disableButtons();
    }

    public void cancelDeliver(ActionEvent actionEvent) {
        DeliveryService.cancelDelivery(selectedDelivery);
        selectedDelivery.statusProperty().setValue(DeliveryStatus.Canceled.toString());
        disableButtons();
    }
}
