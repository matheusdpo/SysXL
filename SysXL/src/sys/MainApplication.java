package sys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
	static SysXL version = new SysXL();

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Layout2.fxml"));
			
			System.out.println("Layout.fxml loaded");
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("Layout.css").toExternalForm());
			System.out.println("Layout.css loaded");
			primaryStage.setScene(scene);
			primaryStage.setTitle(version.getVersion());
			primaryStage.setResizable(false);
			primaryStage.show();
			System.out.println("SysXL loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(version.getVersion() + " has been started");
	//	Thread.sleep(2000);
System.out.println(" __________________________\n"
		+ "/\\                         \\\n"
		+ "\\_| WELCOME TO SYSXL       |\n"
		+ "  |                        |\n"
		+ "  | Version: "+version.getVersion()+" |\n"
		+ "  |   _____________________|_\n"
		+ "   \\_/_______________________/\n");
		launch(args);
		
	}
}
