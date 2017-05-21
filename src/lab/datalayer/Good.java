package lab.datalayer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.lang.String;
import java.sql.*;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Good {
    private final StringProperty id;
    private final StringProperty nomenclature;
    private final StringProperty measure;

    private static ObservableList<Good> goods = FXCollections.observableArrayList();


    public static ObservableList<Good> findAll() {
        System.out.println("Good.findAll");
        goods.clear();
        Database database = Database.getDatabase();
        try {
            Statement statement = database.getStatement();
            ResultSet rs = statement.executeQuery("select * from GET_GOODS");
            while (rs.next()) {
                System.out.println("Cпасибо, Олег Анатольевич");
                goods.add(new Good(rs.getString("id_goods"), rs.getString("nomenclature"), rs.getString("measure")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
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