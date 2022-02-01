package sys;

import java.io.File;
import java.util.Scanner;

public class SysXL {
	private String valueTxt;

	private static final String version = "SysXL - 22.02";

	@SuppressWarnings("resource")
	public void fileTxt(int numberLine) {

		try {
			File text = new File("/home/matheus/Downloads/file.txt");
			Scanner reading = new Scanner(text);
			int i;
			for (i = 0; i <= numberLine; i++) {
				valueTxt = reading.nextLine();
				if (i != numberLine) {
					continue;
				} else {
				}
			}
		} catch (Exception e) {
			System.out.println("Erro Scanner");
		}

	}

	public String getValueTxt() {
		return valueTxt;
	}

	public String getVersion() {
		return version;
	}

}
