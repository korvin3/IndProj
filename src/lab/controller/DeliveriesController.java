package lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lab.DeliveryStatus;
import lab.View;
import lab.datalayer.Delivery;
import lab.service.DeliveryService;
import lab.util.FXUtils;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Kristina Riadchenko on 22.05.2017.
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
    public TableColumn<Delivery, String> deliveryTimeColumn;
    public TableView<Delivery> deliveriesTableView;
    public Button deliverBtn;
    public Button cancelBtn;
    public ChoiceBox<DeliveryStatus> filterStatusCb;
    public Button filterBtn;
    public Button resetFilterBtn;
    public DatePicker dateFromDp;
    public DatePicker dateToDp;

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
        deliveryTimeColumn.setCellValueFactory(cellData -> cellData.getValue().deliveryTimeProperty());

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

        ObservableList<DeliveryStatus> deliveryTypes
                = FXCollections.observableArrayList(Arrays.asList(DeliveryStatus.values()));
        filterStatusCb.setItems(deliveryTypes);
        filterStatusCb.getSelectionModel().select(0);

        dateFromDp.setValue(LocalDate.now().minusDays(1));
        dateToDp.setValue(LocalDate.now());
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

    public void applyFilter(ActionEvent actionEvent) {
        DeliveryStatus status = filterStatusCb.getValue();
        deliveriesTableView.setItems(DeliveryService.find(status, dateFromDp.getValue(), dateToDp.getValue()));
    }

    public void resetFilter(ActionEvent actionEvent) {
        deliveriesTableView.setItems(DeliveryService.findAll());
    }
}
