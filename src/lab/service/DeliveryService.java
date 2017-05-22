package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.DeliveryStatus;
import lab.DeliveryType;
import lab.datalayer.Database;
import lab.datalayer.Delivery;
import lab.exception.DatabaseError;

import java.sql.*;
import java.time.LocalDate;

import static lab.util.CommonUtils.print;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class DeliveryService {
    public static ObservableList<Delivery> findAll() {
        print("Delivery.findAll");
        ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
        try {
            Statement statement = Database.getInstance().getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_DELIVERIES");
            while (rs.next()) {
                print("Cпасибо, Олег Анатольевич");
                String operationType = rs.getString("TYPEOP");
                DeliveryType deliveryType = DeliveryType.fromValue(operationType);
                operationType = deliveryType.toString();

                String stat = rs.getString("status");
                DeliveryStatus deliveryStatus = DeliveryStatus.fromValue(stat);
                stat = deliveryStatus.toString();

                deliveries.add(new Delivery(rs.getInt("id"),
                        rs.getString("NOMENCLATURE").trim(),
                        rs.getString("NAME_AG").trim(),
                        rs.getString("name").trim(),
                        operationType,
                        rs.getInt("QUANTITY"),
                        rs.getString("driver").trim(),
                        stat,
                        rs.getTimestamp("ARR_DATE")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return deliveries;
    }

    //создание новой доставки
    public static void createNewDelivery(String _good, String _agent, String _warehouse, String _type,
                                         int _quantity, String _driver, int _price, String pre_time) {
        try {
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement("{call checkout(?, ?, ?, ?, ?, ?, ?, ?)}");
            ps.setString(1, _warehouse);
            ps.setString(2, _good);
            ps.setString(3, _agent);
            ps.setString(4, _type);
            ps.setInt(5, _quantity);
            ps.setString(6, _driver);
            ps.setString(7, pre_time);
            ps.setInt(8, _price);
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static void markAsDelivered(Delivery delivery) {
        try {
            String query = "{call delivered(?)}";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, delivery.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static void cancelDelivery(Delivery delivery) {
        try {
            String query = "{call cancel_delivery(?)}";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, delivery.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static ObservableList<Delivery> find(DeliveryStatus status, LocalDate dateFrom, LocalDate dateTo) {
        ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
        try {
            String query = "select * from get_deliveries_by_status(?,?,?)";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setTimestamp(1, Timestamp.valueOf(dateFrom.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(dateTo.atStartOfDay()));
            ps.setString(3, status.value());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("NOMENCLATURE") == null) {
                    return deliveries;
                }
                deliveries.add(new Delivery(rs.getInt("ID"),
                        rs.getString("NOMENCLATURE").trim(),
                        rs.getString("NAME_AG").trim(),
                        rs.getString("NAME").trim(),
                        null,
                        rs.getInt("quantity"),
                        rs.getString("DRIVER").trim(),
                        status.toString(),
                        rs.getTimestamp("ARR_DATE")));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
        return deliveries;
    }
}
