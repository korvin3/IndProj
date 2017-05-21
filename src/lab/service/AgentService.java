package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.datalayer.Agent;
import lab.datalayer.Database;
import lab.exception.DatabaseError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static lab.util.CommonUtils.print;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class AgentService {
    public static ObservableList<Agent> findAll() {
        print("Agent.findAll");
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        try {
            Statement statement = Database.getInstance().getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_AGENTS");
            while (rs.next()) {
                print("Cпасибо, Олег Анатольевич");
                agents.add(new Agent(rs.getString("id_ag"), rs.getString("name_ag"),
                        rs.getString("town"), rs.getString("phone"),
                        rs.getInt("pay")));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
        return agents;
    }
}