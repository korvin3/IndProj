package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static lab.util.CommonUtils.print;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Delivery {
    private final IntegerProperty id;
    private final StringProperty good;
    private final StringProperty agent;
    private final StringProperty warehouse;
    private final StringProperty type;
    private final IntegerProperty quantity;
    private final StringProperty driver;
    private final StringProperty status;

    public static ObservableList<Delivery> findAll() {
        print("Delivery.findAll");
        ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
        try {
            Statement statement = Database.getInstance().getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_DELIVERIES");
            while (rs.next()) {
                System.out.println("Cпасибо, Олег Анатольевич");
                String type_op = rs.getString("TYPEOP");
                if (type_op.equals("A")) type_op = "На склад";
                else type_op = "Со склада";
                String stat = rs.getString("status");
                if (stat.equals("S")) stat = "В пути";
                else if (stat.equals("C")) stat = "Отказ";
                else stat = "Доставлено";

                deliveries.add(new Delivery(rs.getInt("id"), rs.getString("NOMENCLATURE"), rs.getString("NAME_AG"),
                        rs.getString("name"), type_op, rs.getInt("QUANTITY"),
                        rs.getString("driver"), stat
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return deliveries;
    }

    private Delivery(int _id, String _good, String _agent, String _warehouse, String _type, int _quantity, String _driver, String _status) {
        id = new SimpleIntegerProperty(_id);
        this.good = new SimpleStringProperty(_good);
        agent = new SimpleStringProperty(_agent);
        warehouse = new SimpleStringProperty(_warehouse);
        type = new SimpleStringProperty(_type);
        quantity = new SimpleIntegerProperty(_quantity);
        driver = new SimpleStringProperty(_driver);
        status = new SimpleStringProperty(_status);
    }


    //создание новой доставки
    public static int Create(String _good, String _agent, String _warehouse, String _type, int _quantity,
                             String _driver, int _price, String pre_time) {
        try {
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement("{call checkout(?, ?, ?, ?, ?, ?, ?, ?)}");
            ps.setString(1, _warehouse);
            ps.setString(2, _good);
            ps.setString(3, _agent);
            ps.setString(4, _type);
            ps.setInt(5, _quantity);
            ps.setString(6, _driver);
            ps.setString(7, pre_time);
            ps.setInt(8, _price);
            ps.execute();
        } catch (SQLException e) {
            print("SQLException " + e.getMessage());
            return 1;
        }
        return 0;
    }


    //закрытие доставки через изменение статуса на доставлено("D") или возврат("C")
    public int Delivered(String _status) {
        String query;
        if (_status == "D") query = "call delivered(?)";
        else query = "call cancel_delivery(?)";
        try {
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, this.id.toString());
            ps.execute();
        } catch (SQLException e) {
            print("SQLException " + e.getMessage());
            return 1;
        }
        return 0;
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