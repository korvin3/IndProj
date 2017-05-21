package lab.datalayer;

import lab.exception.DatabaseError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class Warehouse {
    private String id;
    private String name;
    private String town;

    public static List<Warehouse> findAll() {
        try {
            ResultSet rs = Database.getInstance().getStatement()
                    .executeQuery("select * from WAREHOUSE");
            List<Warehouse> warehouseList = new ArrayList<>();
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

    public Warehouse(String id, String name, String town) {
        this.id = id;
        this.name = name;
        this.town = town;
    }
}