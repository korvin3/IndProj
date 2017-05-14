package sample;
import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * Created by Korvin on 13.05.2017.
 */
public class Database {
    private static Database database;
    private static Connection connection ;
    private static Statement statement;

    private Database(){

    }

    public static Database getDatabase(){
        if(database==null){
            database= new Database();
        }
        return database;
    }

    public Connection getConnection(){
        if(connection==null) {
            String driver = "org.firebirdsql.jdbc.FBDriver";
            String url = "jdbc:firebirdsql:class.mmcs.sfedu.ru/3050:/fbdata/38/zinchenko.fdb ?encoding=UNICODE_FSS";
            String user = "IT38";
            String password = "it38";

            try {
                Class.forName(driver);
                System.out.println("OK");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connect");
            } catch (ClassNotFoundException e) {
                System.out.println("Firebird JDBC driver not found");
            } catch (SQLException e) {
                System.out.println("SQLException " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
            }
        }
        return connection;
    }

    public Statement getStatement(){
        if (statement == null) {
            if (connection == null) connection = getConnection();
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                System.out.println("SQLException " + e.getMessage());
            }
        }
        return statement;
    }

    public static void closeAll() {
        if (statement != null)
            try { statement.close(); System.out.println("Statement closed"); }
            catch (SQLException ignore) { }
        if (connection != null)
            try { connection.close(); System.out.println("Connection closed");}
            catch (SQLException ignore) { }
    }
}
