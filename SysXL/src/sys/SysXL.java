package sys;

import java.io.File;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SysXL {
	private static String valueTxt;

	private static final String version = "SysXL - 22.03.1"; /* easy to change and show the version */

	@SuppressWarnings("resource")
	public static void fileTxt(int numberLine) {

		try {
			File text = new File("/home/matheus/SysXL/file.txt");
			/*
			 * file.txt will save important information, such as database information and
			 * the folder where the file will be saved.
			 */

			Scanner reading = new Scanner(text);
			int i;
			for (i = 0; i <= numberLine; i++) {
				valueTxt = reading.nextLine();
				if (i != numberLine) {
					continue;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro Scanner - file.txt not found");
		}

	}

	public static void alertSystem(String title, String header, String content) {

		/* Less code when i call a Warning */

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(version + title);
		alert.setResizable(true);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

	public static String getValueTxt() {
		return valueTxt;
	}

	public static String getVersion() {
		return version;
	}

}
