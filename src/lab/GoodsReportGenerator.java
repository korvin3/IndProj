package lab;

import lab.datalayer.Database;
import lab.exception.DatabaseError;
import lab.util.CommonUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Kolesnik on 22.05.2017.
 */
public class GoodsReportGenerator {
    private static class Row {
        private String nomenclature;
        private String name;
        private Integer quantity;
        private String reserve;

        public Row(String nomenclature, String name, Integer quantity, String reserve) {
            this.nomenclature = nomenclature;
            this.name = name;
            this.quantity = quantity;
            this.reserve = reserve;
        }
    }

    public static void generate() {
        try {
            String query = "select * from VIEW_RESERVED";
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Row> rows = new ArrayList<>();
            while (rs.next()) {
                rows.add(new Row(
                        rs.getString("NOMENCLATURE,"),
                        rs.getString("NAME,"),
                        rs.getInt("QUANTITY,"),
                        rs.getString("RESERVED")
                ));
            }
            CommonUtils.print(rows.size());
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }
}
