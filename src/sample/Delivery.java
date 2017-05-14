package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.lang.String;
import java.sql.DatabaseMetaData;
import java.sql.*;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Delivery{
    private final IntegerProperty id;
    private final StringProperty good;
    private final StringProperty agent;
    private final StringProperty warehouse;
    private final StringProperty type;
    private final IntegerProperty quantity;
    private final StringProperty driver;
    private final StringProperty status;
    private static ObservableList<Delivery> deliveries = FXCollections.observableArrayList();


    public static ObservableList<Delivery> findAll(){
        System.out.println("Delivery.findAll");
        deliveries.clear();
        Database database = Database.getDatabase();
        Connection connection = database.getConnection();
        try {
            //CallableStatement callableStatement = connection.prepareCall("{ ? = call get_deliveries () }");
            //callableStatement.registerOutParameter(1, Types.OTHER);
           // callableStatement.execute();
           // ResultSet rs = (ResultSet) callableStatement.getObject(1);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from GET_DELIVERIES");

            while (rs.next()) {
                System.out.println("Cпасибо, Олег Анатольевич");
                deliveries.add(new Delivery(rs.getInt("id_del"),rs.getString("good_name"),rs.getString("agent_name"),
                        rs.getString("wh_name"), rs.getString("op"), rs.getInt("q"),
                        rs.getString("driver"), rs.getString("status")
                        ));
            } } catch (SQLException e) { System.out.println("SQLException " + e.getMessage());}
        Database.closeAll();
        return deliveries;
    }

    private Delivery(int _id, String _good, String _agent, String _warehouse, String _type, int _quantity, String _driver, String _status){
        id = new SimpleIntegerProperty(_id);
        this.good = new SimpleStringProperty(_good);
        agent = new SimpleStringProperty(_agent);
        warehouse = new SimpleStringProperty(_warehouse);
        type = new SimpleStringProperty(_type);
        quantity = new SimpleIntegerProperty(_quantity);
        driver = new SimpleStringProperty(_driver);
        status = new SimpleStringProperty(_status);
    }


    public String getGood() {
        return good.get();
    }

    public void setGood(String _good) {
        this.good.set(_good);
    }

    public StringProperty goodProperty() {
        return good;
    }



    public String getAgent() {
        return agent.get();
    }

    public void setAgent(String _agent) {
        this.agent.set(_agent);
    }

    public StringProperty agentProperty() {
        return agent;
    }



    public String getWarehouse() {
        return warehouse.get();
    }

    public void setWarehouse(String _warehouse) {
        this.warehouse.set(_warehouse);
    }

    public StringProperty warehouseProperty() {
        return warehouse;
    }



    public String getType() {
        return type.get();
    }

    public void setType(String _type) {
        this.type.set(_type);
    }

    public StringProperty typeProperty() {
        return type;
    }



    public String getDriver() {
        return driver.get();
    }

    public void setDriver(String _driver) {
        this.driver.set(_driver);
    }

    public StringProperty driverProperty() {
        return driver;
    }



    public String getStatus() {
        return status.get();
    }

    public void setStatus(String _status) {
        this.status.set(_status);
    }

    public StringProperty statusProperty() {
        return status;
    }



    public int getId() {
        return id.get();
    }

    public void setId(int _id) {
        this.id.set(_id);
    }

    public IntegerProperty idProperty() {
        return id;
    }



    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int _quantity) {
        this.quantity.set(_quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }



}
