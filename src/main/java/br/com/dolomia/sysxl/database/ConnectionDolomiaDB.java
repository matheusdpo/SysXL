package br.com.dolomia.sysxl.database;

import br.com.dolomia.sysxl.utils.GetProperties;
import br.com.dolomia.sysxl.utils.AlertScreen;
import br.com.dolomia.sysxl.utils.LogUtils;
import br.com.dolomia.sysxl.utils.SelectDB;
import javafx.scene.control.Alert;

import java.sql.*;

public class ConnectionDolomiaDB {

    private static Connection getConnection() {
        try {
            LogUtils.registerLog("Url: " + GetProperties.getProperty("url"));
            LogUtils.registerLog("User: " + GetProperties.getProperty("user"));
            LogUtils.registerLog("Password: " + GetProperties.getProperty("passwd"));
            return DriverManager.getConnection(GetProperties.getProperty("url"), GetProperties.getProperty("user"), GetProperties.getProperty("passwd"));
        } catch (SQLException e) {
            AlertScreen.getAlert("ERRO", "ERRO AO SE CONECTAR COM O BANCO DE DADOS", e.getMessage(), Alert.AlertType.ERROR);
            LogUtils.registerError("Error connecting to DB -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement getPreparedStatement(Integer monthSelected, Integer yearSelected) {
        LogUtils.registerLog("====================[DOLOMIADB -> CONNECTED!]====================");

        try {
            return getConnection().prepareStatement(SelectDB.getSelectDB(monthSelected, yearSelected));
        } catch (SQLException e) {
            AlertScreen.getAlert("ERRO", "ERRO AO EXECUTAR QUERY (SELECT) NO BANCO DE DADOS", e.getMessage(), Alert.AlertType.ERROR);
            LogUtils.registerError("Error executing query -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResultSet(Integer monthSelected, Integer yearSelected) {
        try {
            return getPreparedStatement(monthSelected, yearSelected).executeQuery();
        } catch (SQLException e) {
            AlertScreen.getAlert("ERRO", "ERRO AO EXECUTAR QUERY (SELECT) NO BANCO DE DADOS", e.getMessage(), Alert.AlertType.ERROR);
            LogUtils.registerError("Error executing query -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}