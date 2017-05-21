package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.exception.DatabaseError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static lab.util.CommonUtils.print;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Agent {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty town;
    private final StringProperty phone;
    private final IntegerProperty pay;

    public static ObservableList<Agent> findAll() {
        print("Agent.findAll");
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        Database database = Database.getInstance();
        try {
            Statement statement = database.getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_AGENTS");
            while (rs.next()) {
                print("Cпасибо, Олег Анатольевич");
                agents.add(new Agent(rs.getString("id_ag"), rs.getString("name_ag"), rs.getString("town"),
                        rs.getString("phone"), rs.getInt("pay")));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
        return agents;
    }

    private Agent(String _id, String _name, String _town, String _phone, int _pay) {
        id = new SimpleStringProperty(_id);
        name = new SimpleStringProperty(_name);
        town = new SimpleStringProperty(_town);
        phone = new SimpleStringProperty(_phone);
        pay = new SimpleIntegerProperty(_pay);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String _name) {
        this.name.set(_name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getTown() {
        return town.get();
    }

    public void setTown(String _town) {
        this.town.set(_town);
    }

    public StringProperty townProperty() {
        return town;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String _phone) {
        this.phone.set(_phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String _id) {
        this.id.set(_id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public int getPay() {
        return pay.get();
    }

    public void setPay(int _pay) {
        this.pay.set(_pay);
    }

    public IntegerProperty payProperty() {
        return pay;
    }
}

