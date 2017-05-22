package lab;

import lab.datalayer.Database;
import lab.exception.DatabaseError;
import lab.util.CommonUtils;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Kolesnik on 22.05.2017.
 */
public class GoodsReportGenerator {
    private static class TableRow {
        private String nomenclature;
        private String name;
        private Integer quantity;
        private Integer reserve;

        public TableRow(String nomenclature, String name, Integer quantity, Integer reserve) {
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
            List<TableRow> rows = new ArrayList<>();
            while (rs.next()) {
                rows.add(new TableRow(
                        rs.getString("NOMENCLATURE").trim(),
                        rs.getString("NAME").trim(),
                        rs.getInt("QUANTITY"),
                        rs.getInt("RESERVED")
                ));
            }
            CommonUtils.print(rows.size());

            StringBuilder csv = new StringBuilder("NOMENCLATURE,NAME,QUANTITY,RESERVED\n");
            for (TableRow row : rows) {
                csv
                        .append("\"").append(row.nomenclature).append("\",")
                        .append("\"").append(row.name).append("\",")
                        .append(row.quantity).append(",")
                        .append(row.reserve).append("\n");
            }

            FileOutputStream fileOutputStream = new FileOutputStream("result.csv");
            fileOutputStream.write(csv.toString().getBytes());
            fileOutputStream.close();

            CommonUtils.print("done");

        } catch (Exception e) {
            throw new DatabaseError(e);
        }
    }
}
