package lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lab.DeliveryType;
import lab.View;
import lab.datalayer.Agent;
import lab.datalayer.Good;
import lab.datalayer.Warehouse;
import lab.exception.DatabaseError;
import lab.service.AgentService;
import lab.service.DeliveryService;
import lab.service.GoodService;
import lab.service.WarehouseService;
import lab.util.FXUtils;

import java.sql.SQLException;
import java.util.Arrays;

import static lab.util.CommonUtils.print;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class NewDeliveryController extends FXController {
    public ChoiceBox<Warehouse> warehouseCb;
    public ChoiceBox<Good> goodCb;
    public ChoiceBox<Agent> agentCb;
    public ChoiceBox<DeliveryType> typeCb;
    public TextField quantityTf;
    public TextField driverTf;
    public TextField priceTf;
    public TextField deliveryTimeTf;
    public Button createBtn;
    public Button backBtn;

    @Override
    public void initialize() {
        super.initialize();

        //make text fields numeric only
        quantityTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantityTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        priceTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                priceTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        ObservableList<Warehouse> warehouses = WarehouseService.findAll();
        warehouseCb.setItems(warehouses);
        warehouseCb.getSelectionModel().select(0);

        ObservableList<Good> goods = GoodService.findAll();
        goodCb.setItems(goods);
        goodCb.getSelectionModel().select(0);

        ObservableList<Agent> agents = AgentService.findAll();
        agentCb.setItems(agents);
        agentCb.getSelectionModel().select(0);

        ObservableList<DeliveryType> deliveryTypes
                = FXCollections.observableArrayList(Arrays.asList(DeliveryType.values()));
        typeCb.setItems(deliveryTypes);
        typeCb.getSelectionModel().select(0);
    }

    public void createDelivery(ActionEvent actionEvent) {
        try {
            checkValues();
            checkQuantity();
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            DeliveryService.createNewDelivery(goodCb.getValue().getNomenclature(),
                    agentCb.getValue().getName(),
                    warehouseCb.getValue().getName(),
                    typeCb.getValue().value(),
                    toInt(quantityTf.getText()),
                    driverTf.getText(),
                    toInt(priceTf.getText()),
                    deliveryTimeTf.getText());
        } catch (DatabaseError e) {
            SQLException cause = (SQLException) e.getCause();
            if (cause.toString().contains("QUANTITYISLESS")) { //ugly ugly (but works!)
                new Alert(Alert.AlertType.WARNING, "provided quantity is too big").show();
                quantityTf.requestFocus();
                return;
            } else if (cause.toString().contains("GOODNOTFOUND")) { //ugly ugly
                new Alert(Alert.AlertType.WARNING, "warehouse doesn't have such goods").show();
                goodCb.requestFocus();
                return;
            } else throw e;
        }
        FXUtils.setCurrentView(View.Deliveries);
    }

    private void checkQuantity() {
        int deliverQuantity = toInt(quantityTf.getText());
        if (deliverQuantity == 0) {
            new Alert(Alert.AlertType.WARNING, "quantity shouldn't be 0").show();
            throw new IllegalArgumentException("quantity cannot be 0");
        }
    }

    private void checkValues() {
        checkIsFilled("warehouse", warehouseCb);
        checkIsFilled("good", goodCb);
        checkIsFilled("agent", agentCb);
        checkIsFilled("type", typeCb);
        checkIsFilled("quantity", quantityTf);
        checkIsFilled("driver", driverTf);
        checkIsFilled("price", priceTf);
        checkIsFilled("deliveryTime", deliveryTimeTf);
    }

    private void checkIsFilled(String fieldName, ChoiceBox choiceBox) {
        if (choiceBox.getValue() == null) {
            choiceBox.requestFocus();
            print(fieldName + " is not filled");
            throw new IllegalArgumentException(fieldName + " is not filled");
        }
    }

    private void checkIsFilled(String fieldName, TextField textField) {
        if (textField.getText() == null || textField.getText().equals("")) {
            textField.requestFocus();
            print(fieldName + " is not filled");
            throw new IllegalArgumentException(fieldName + " is not filled");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }

    private int toInt(String from) {
        return Integer.valueOf(from);
    }
}