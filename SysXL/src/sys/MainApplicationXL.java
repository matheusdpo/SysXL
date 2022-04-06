package sys;

import static sys.SysXL.getVersion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplicationXL extends Application {
	static SysXL version = new SysXL();

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Layout.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Layout.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(getVersion());
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println(" ____________________________\n" + "/\\                           \\\n"
				+ "\\_| Welcome to SysXL         |\n" + "  |  Version: " + getVersion() + "|\n"
				+ "  |   _______________________|_\n" + "   \\_/_________________________/");
		launch(args);
	}
}
