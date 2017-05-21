package lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab.datalayer.Agent;
import lab.datalayer.Delivery;
import lab.datalayer.Good;

import static lab.util.CommonUtils.print;

public class MainController extends FXController {
    //DELIVERY
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


    //AGENT
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

    //Good
    @FXML
    private TableView<Good> goodTableView;
    @FXML
    private TableColumn<Good, String> nomenclatureTableColumn;
    @FXML
    private TableColumn<Good, String> measureTableColumn;

    //UI CONTROLS
    @FXML
    private Button refreshButton;

    public MainController() {
    }

    @Override
    public void initialize() {
        print("initialize");

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

        //Good
        nomenclatureTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomenclatureProperty());
        measureTableColumn.setCellValueFactory(cellData -> cellData.getValue().measureProperty());

        postDeliveryTableView();
        postAgentTableView();
        postGoodTableView();
    }

    public void postDeliveryTableView() {
        print("postDeliveryTableView");
        deliveryTableView.setItems(Delivery.findAll());
    }

    public void postAgentTableView() {
        print("postAgentTableView");
        agentTableView.setItems(Agent.findAll());
    }

    public void postGoodTableView() {
        print("postGoodTableView");
        goodTableView.setItems(Good.findAll());
    }

    @FXML
    public void handleRefreshAll() {
        refreshButton.disarm();
        postGoodTableView();
        postAgentTableView();
        postDeliveryTableView();
    }
}