package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

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
    private final StringProperty deliveryTime;

    public Delivery(int _id, String _good, String _agent, String _warehouse, String _type,
                    int _quantity, String _driver, String _status, Timestamp _deliveryTime) {
        id = new SimpleIntegerProperty(_id);
        good = new SimpleStringProperty(_good);
        agent = new SimpleStringProperty(_agent);
        warehouse = new SimpleStringProperty(_warehouse);
        type = new SimpleStringProperty(_type);
        quantity = new SimpleIntegerProperty(_quantity);
        driver = new SimpleStringProperty(_driver);
        status = new SimpleStringProperty(_status);
        deliveryTime = new SimpleStringProperty(_deliveryTime == null ? null : _deliveryTime.toString());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getGood() {
        return good.get();
    }

    public StringProperty goodProperty() {
        return good;
    }

    public String getAgent() {
        return agent.get();
    }

    public StringProperty agentProperty() {
        return agent;
    }

    public String getWarehouse() {
        return warehouse.get();
    }

    public StringProperty warehouseProperty() {
        return warehouse;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public String getDriver() {
        return driver.get();
    }

    public StringProperty driverProperty() {
        return driver;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getDeliveryTime() {
        return deliveryTime.get();
    }

    public StringProperty deliveryTimeProperty() {
        return deliveryTime;
    }
}