package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.*;
import java.net.*;

public class Controller {
    @FXML
    private TableView<Delivery> deliveryTableView;
    @FXML
    private TableColumn<Delivery, String> goodTableColumn;
    @FXML
    private TableColumn<Delivery, String> warehouseTableColumn;
    @FXML
    private TableColumn<Delivery, String> typeTableColumn;
    @FXML
    private TableColumn<Delivery, Integer> quantityTableColumn;
    @FXML
    private TableColumn<Delivery, String> driverTableColumn;
    @FXML
    private TableColumn<Delivery, String> statusTableColumn;

    public Controller(){}

    @FXML
    public void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        System.out.println("initialize");
        goodTableColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        typeTableColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        driverTableColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        statusTableColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        postDeliveryTableView();
    }

    public void postDeliveryTableView(){
        System.out.println("postDeliveryTableView");
        deliveryTableView.setItems(Delivery.findAll());
    }

}
