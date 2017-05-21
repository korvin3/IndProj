package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.datalayer.Database;
import lab.datalayer.Warehouse;
import lab.exception.DatabaseError;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class WarehouseService {
    public static ObservableList<Warehouse> findAll() {
        try {
            ResultSet rs = Database.getInstance().getStatement()
                    .executeQuery("select * from WAREHOUSE");
            ObservableList<Warehouse> warehouseList = FXCollections.observableArrayList();
            while (rs.next()) {
                warehouseList.add(new Warehouse(
                        rs.getString("ID_WH"),
                        rs.getString("NAME"),
                        rs.getString("TOWN")
                ));
            }
            return warehouseList;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static int findGoodsQuantity(String whId, String goodId) {
        return 0;
    }
}
