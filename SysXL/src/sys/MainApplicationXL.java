package sys;

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
			primaryStage.setTitle(version.getVersion());
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(
				" __________________________\n" + "/\\                         \\\n" + "\\_| WELCOME TO SYSXL       |\n"
						+ "  |                        |\n" + "  | Version: " + version.getVersion() + " |\n"
						+ "  |   _____________________|_\n" + "   \\_/_______________________/\n");
		launch(args);
	}
}
