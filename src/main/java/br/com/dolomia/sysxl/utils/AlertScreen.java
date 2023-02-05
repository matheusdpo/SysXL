package br.com.dolomia.sysxl.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class AlertScreen {

    public static void getAlert(String title, String header, String content, AlertType alertType) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(GetProperties.getProperty("version") + " - " + title);
            alert.setResizable(false);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.show();
        });
    }

    public static void getAlertDone(String month, String year) {
        String path = GetProperties.getProperty("path");

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONCLUÍDO");
            alert.setHeaderText("IIS " + month + "/" + year + " exportado com sucesso!");
            alert.setContentText("Seu arquivo foi exportado na pasta:\n" + path);
            ButtonType btn1 = new ButtonType("Abrir pasta");
            ButtonType btn2 = new ButtonType("Sair");

            alert.getButtonTypes().setAll(btn1, btn2);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == btn1) {
                try {
                    File directory = new File(path);
                    Desktop.getDesktop().open(directory);
                } catch (IOException e) {
                    AlertScreen.getAlert("ERRO", "ERRO AO ABRIR PASTA -> " + path, e.getMessage(), Alert.AlertType.ERROR);
                    LogUtils.registerError("Error creating excel file -> " + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        });
    }
}