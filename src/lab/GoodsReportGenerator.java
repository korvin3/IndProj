package lab;

import lab.datalayer.Database;
import lab.exception.DatabaseError;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Kolesnik on 22.05.2017.
 */
public class GoodsReportGenerator {

    private static final String delim = ";";

    private static class TableRow {
        private String nomenclature;
        private String name;
        private Integer quantity;
        private Integer reserve;

        private TableRow(String nomenclature, String name, Integer quantity, Integer reserve) {
            this.nomenclature = nomenclature;
            this.name = name;
            this.quantity = quantity;
            this.reserve = reserve;
        }
    }

    public static void generateReport(File output) {
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
            witeToFile(output, rows);
        } catch (Exception e) {
            throw new DatabaseError(e);
        }
    }

    private static void witeToFile(File file, List<TableRow> data) {
        try {
            StringBuilder csv = new StringBuilder();
            csv.append("Товар").append(delim)
                    .append("Склад").append(delim)
                    .append("Оставшееся количество").append(delim)
                    .append("Зарезервировано");
            for (TableRow row : data) {
                csv.append("\n\"").append(row.nomenclature).append("\"").append(delim)
                        .append("\"").append(row.name).append("\"").append(delim)
                        .append(row.quantity).append(delim)
                        .append(row.reserve);
            }

            OutputStream os = new FileOutputStream(file);
            os.write(239); //make result encoding UTF-8 'with BOM'
            os.write(187);
            os.write(191);

            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

            w.print(csv.toString());
            w.flush();
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
