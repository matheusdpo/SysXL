package br.com.dolomia.sysxl.utils;

import javafx.scene.control.Alert;

public class Screen {


    public static void warning(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(GetProperties.getProperty("version") + " - " + title);
        alert.setResizable(true);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static void confirmation(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(GetProperties.getProperty("version") + " - " + title);
        alert.setResizable(true);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static void none(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(GetProperties.getProperty("version") + " - " + title);
        alert.setResizable(true);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static void error(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(GetProperties.getProperty("version") + " - " + title);
        alert.setResizable(true);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static void information(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(GetProperties.getProperty("version") + " - " + title);
        alert.setResizable(true);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
