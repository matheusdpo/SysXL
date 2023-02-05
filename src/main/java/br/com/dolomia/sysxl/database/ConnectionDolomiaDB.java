package br.com.dolomia.sysxl.database;

import br.com.dolomia.sysxl.utils.GetProperties;
import br.com.dolomia.sysxl.utils.SelectDB;

import java.sql.*;
import java.util.Properties;

public class ConnectionDolomiaDB {

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(GetProperties.getProperty("url"), GetProperties.getProperty("user"), GetProperties.getProperty("passwd"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement getPreparedStatement(Integer monthSelected, Integer yearSelected) {
        try {
            return getConnection().prepareStatement(SelectDB.getSelectDB(monthSelected, yearSelected));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResultSet(Integer monthSelected, Integer yearSelected) {
        try {
            return getPreparedStatement(monthSelected, yearSelected).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}