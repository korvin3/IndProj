package lab.datalayer;

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
public class Good {
    private final StringProperty id;
    private final StringProperty nomenclature;
    private final StringProperty measure;

    public static ObservableList<Good> findAll() {
        print("Good.findAll");
        ObservableList<Good> goods = FXCollections.observableArrayList();
        Database database = Database.getInstance();
        try {
            Statement statement = database.getStatement();
            ResultSet rs = statement.executeQuery("select * from GET_GOODS");
            while (rs.next()) {
                print("Cпасибо, Олег Анатольевич");
                goods.add(new Good(rs.getString("id_goods"), rs.getString("nomenclature"), rs.getString("measure")));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
        return goods;
    }

    private Good(String _id, String _nomenclature, String _measure) {
        id = new SimpleStringProperty(_id);
        nomenclature = new SimpleStringProperty(_nomenclature);
        measure = new SimpleStringProperty(_measure);
    }


    public String getNomenclature() {
        return nomenclature.get();
    }

    public void setNomenclature(String _nomenclature) {
        this.nomenclature.set(_nomenclature);
    }

    public StringProperty nomenclatureProperty() {
        return nomenclature;
    }


    public String getMeasure() {
        return measure.get();
    }

    public void setMeasure(String _measure) {
        this.measure.set(_measure);
    }

    public StringProperty measureProperty() {
        return measure;
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
}