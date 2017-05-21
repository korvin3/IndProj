package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Agent {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty town;
    private final StringProperty phone;
    private final IntegerProperty pay;

    public Agent(String _id, String _name, String _town, String _phone, int _pay) {
        id = new SimpleStringProperty(_id);
        name = new SimpleStringProperty(_name);
        town = new SimpleStringProperty(_town);
        phone = new SimpleStringProperty(_phone);
        pay = new SimpleIntegerProperty(_pay);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public int getPay() {
        return pay.get();
    }

    public IntegerProperty payProperty() {
        return pay;
    }

    @Override
    public String toString() {
        return getName();
    }
}

