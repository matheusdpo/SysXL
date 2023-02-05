package br.com.dolomia.sysxl.main;

import br.com.dolomia.sysxl.utils.GetProperties;
import br.com.dolomia.sysxl.utils.LogUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SysXLApplication extends Application {
    @Override
    public void start(Stage stage) {
        LogUtils.registerLog("The application has been started.");
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("Layout.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(GetProperties.getProperty("version"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}