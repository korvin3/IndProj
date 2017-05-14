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

    @FXML
    private TableView<Agent> agentTableView;
    @FXML
    private TableColumn<Agent, String> nameTableColumn;
    @FXML
    private TableColumn<Agent, String> townTableColumn;
    @FXML
    private TableColumn<Agent, String> phoneTableColumn;
    @FXML
    private TableColumn<Agent, Integer> payTableColumn;

    public Controller(){}

    @FXML
    public void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        System.out.println("initialize");

        //Delivery
        goodTableColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        typeTableColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        driverTableColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        statusTableColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Agent
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        townTableColumn.setCellValueFactory(cellData -> cellData.getValue().townProperty());
        phoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        payTableColumn.setCellValueFactory(cellData -> cellData.getValue().payProperty().asObject());


        postDeliveryTableView();
        postAgentTableView();
    }

    public void postDeliveryTableView(){
        System.out.println("postDeliveryTableView");
        deliveryTableView.setItems(Delivery.findAll());
    }

    public void postAgentTableView(){
        System.out.println("postAgentTableView");
        agentTableView.setItems(Agent.findAll());
    }



}
