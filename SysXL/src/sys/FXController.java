package sys;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class FXController implements Initializable {

	/*
	 * FXML variables.
	 */
	@FXML
	private Button btnConverter;

	@FXML
	private Hyperlink hlDonate;

	@FXML
	private Hyperlink hlHelp;

	@FXML
	private ChoiceBox<String> chcMonth;

	@FXML
	private ChoiceBox<String> chcYear;

	@FXML
	private Label lblMes;

	@FXML
	private Label lblAno;

	@FXML
	private Label lblAnoAutomatico;

	@FXML
	private Label lblDolomiaSysfogo;

	/*
	 * common variables.
	 */

	static SysXL version = new SysXL();

	private int line = 1; // excel lines.

	private int percent = 0; // control of the import.

	Integer year = Calendar.getInstance().get(Calendar.YEAR); // current year.

	Integer year2 = 2017; // first year of the Sysfogo system.

	ObservableList<String> SelectMonth = FXCollections.observableArrayList("Escolha", "Janeiro", "Fevereiro", "Março",
			"Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"); // months

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chcMonth.setValue("Escolha");
		chcYear.setValue("Escolha");

		ObservableList<String> SelectYear = FXCollections.observableArrayList();
		while (year2 <= year) {
			chcYear.getItems().add(year2.toString()); /* year will load automatically. */
			year2++;
		}
		chcMonth.getItems().addAll(SelectMonth);
		chcYear.getItems().addAll(SelectYear);
		lblAnoAutomatico.setText(year.toString());
	}

	public void actionDonate() throws Exception {
		URI link = new URI("https://linktr.ee/matheusdpo_");
		Desktop.getDesktop().browse(link);
	}

	public void actionHelp() throws Exception {
		SysXL actionHelp = new SysXL();
		actionHelp.alertSystem(" - Help", "ATENÇÃO!",
				"Para editar login, senha, IP ou Diretorio:"
						+ "\n1. Vá em C:\\Arquivo de Programa\\SysXL\\ e abra o arquivo 'file.txt';"
						+ "\n2. Edite os parâmetros que deseja.\n" + "\nObservações:"
						+ "\nCada linha está reservada com um parametro:"
						+ "\n1º Linha: url/IP para acesso ao banco de dados;" + "\n2º Linha: usuario do banco de dados;"
						+ "\n3º Linha: senha do banco de dados;" + "\n4º Linha: Diretorio onde será salvo.");
	}

	public void actionConverter() throws IOException, InterruptedException {

		/* get username from file.txt */
		SysXL callingUser = new SysXL();
		callingUser.fileTxt(1);
		String userCalled = callingUser.getValueTxt();
		String user = userCalled;

		/* get password from file.txt */
		SysXL callingPw = new SysXL();
		callingPw.fileTxt(2);
		String userPw = callingPw.getValueTxt();
		String passwd = userPw;

		/* get IP from file.txt */
		SysXL callingIP = new SysXL();
		callingIP.fileTxt(0);
		String ipCalled = callingIP.getValueTxt();
		String url = ipCalled;

		/* get the directory from the file.txt */
		SysXL callingPath = new SysXL();
		callingPath.fileTxt(3);
		String pathCalled = callingPath.getValueTxt();

		int monthQuery = chcMonth.getSelectionModel().getSelectedIndex(); /* get month selected */

		String yearQuery = chcYear.getValue(); /* get year selected */

		String select = ""; /* query */

		String path = ""; /* directory */

		/* set selected based on your choice */
		if (monthQuery < 10 && monthQuery >= 1) {
			String selectTST = "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, "
					+ "b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b "
					+ "ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = "
					+ yearQuery + " and mes = " + "0" + monthQuery + " AND b.iis_pai is NULL";
			select = selectTST;
		} else {
			String selectTST = "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, "
					+ "b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b "
					+ "ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = "
					+ yearQuery + " and mes = " + monthQuery + " AND b.iis_pai is NULL";
			select = selectTST;
		}

		/* set the name of the archive */
		if (monthQuery < 10 && monthQuery >= 1) {
			String pathTST = pathCalled + "0" + monthQuery + "-" + yearQuery + " IIS.xlsx";
			path = pathTST;
		} else {
			String pathTST = pathCalled + monthQuery + "-" + yearQuery + " IIS.xlsx";
			path = pathTST;
		}

		/* Apache POI started */
		HSSFWorkbook sysheet = new HSSFWorkbook();
		HSSFSheet sheet = sysheet.createSheet("Sysfogo"); // Title
		HSSFRow header = sheet.createRow((short) 0); // Line

		/* add title in the first column (0) */
		header.createCell(0).setCellValue("cnpj_fornecedor");
		header.createCell(1).setCellValue("num_nf");
		header.createCell(2).setCellValue("guia_trafego");
		header.createCell(3).setCellValue("cod_produto");
		header.createCell(4).setCellValue("den_item");
		header.createCell(5).setCellValue("iis_embal");
		header.createCell(6).setCellValue("dat_exportacao");

		try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
			System.out.println("          __   _,--=\"=--,_   __\n" + "         /  \\.\"    .-.    \"./  \\\n"
					+ "        /  ,/  _   : :   _  \\/` \\\n" + "        \\  `| /o\\  :_:  /o\\ |\\__/\n"
					+ "         `-'| :=\"~` _ `~\"=: |\n" + "            \\`     (_)     `/\n"
					+ "     .-\"-.   \\      |      /   .-\"-.\n" + ".---{     }--|  /,.-'-.,\\  |--{     }---.\n"
					+ " )  (_)_)_)  \\_/`~-===-~`\\_/  (_(_(_)  (\n" + "(         CONNECTED INTO MYSQL          )\n"
					+ " )                                     (\n" + "'---------------------------------------'\n"
					+ "\n");

			try (PreparedStatement stmt = conn.prepareStatement(select)) {
				ResultSet data = stmt.executeQuery();

				while (data.next()) {

					HSSFRow rowDB = sheet.createRow((short) this.line);
					String CNPJ = data.getString("a.cnpj_fornecedor");
					String numNF = data.getString("a.num_nf");
					String guiaTrafego = data.getString("a.guia_trafego");
					String codProduto = data.getString("b.cod_produto");
					String denItem = data.getString("c.den_item");
					String IISEmbal = data.getString("b.iis_embal");
					String datExportacao = data.getString("a.dat_exportacao");
					rowDB.createCell(0).setCellValue(CNPJ);
					rowDB.createCell(1).setCellValue(numNF);
					rowDB.createCell(2).setCellValue(guiaTrafego);
					rowDB.createCell(3).setCellValue(codProduto);
					rowDB.createCell(4).setCellValue(denItem);
					rowDB.createCell(5).setCellValue(IISEmbal);
					rowDB.createCell(6).setCellValue(datExportacao);

					FileOutputStream fileOut = new FileOutputStream(path);
					sysheet.write(fileOut);
					fileOut.close();
					sysheet.close();

					this.line++;
					this.percent++;

					if (this.percent > 100) {
						continue;
					} else if (this.percent == 100) {
						System.out.println("Loading files: 100%");
					} else {
						System.out.println("Loading files: " + this.percent + "%");
					}
				}

				System.out.println("Done!!!\n");
				SysXL done = new SysXL();
				done.alertSystem(" - Concluído", "Exportação concluída com sucesso =)",
						"Seu arquivo foi exportado para o diretorio:\n\n");
				System.out.println("             .--,       .--,\n" + "            ( (  \\.---./  ) )\n"
						+ "             '.__/o   o\\__.'\n" + "                {=  ^  =}\n"
						+ "                 >  -  <\n" + " ____________.\"\"`-------`\"\".____________\n"
						+ "/                                       \\\n" + "\\ Thanks for using " + version.getVersion()
						+ "        /\n" + "/                                       \\\n"
						+ "\\_______________________________________/\n" + "               ___)( )(___\n"
						+ "              (((__) (__)))");
			} catch (SQLException e) {
				System.out.println("                            _ ._  _ , _ ._\n"
						+ "                          (_ ' ( `  )_  .__)\n"
						+ "                        ( (  (    )   `)  ) _)\n"
						+ "                       (__ (_   (_ . _) _) ,__)\n"
						+ "                           `~~`\\ ' . /`~~`\n"
						+ "                           ,::: ;   ; :::,\n"
						+ "                          ':::::::::::::::'\n"
						+ "     _________________________/_ __ \\________________________\n"
						+ "    |                                                         |\n"
						+ "    |                       SELECTED FAILED!                  |\n"
						+ "    |_________________________________________________________|\n" + "\n" + "\n" + "");

				SysXL error1 = new SysXL();
				error1.alertSystem(" - Erro de Banco de Dados", "Ops... temos um erro =(",
						"A Select do banco de dados pode estar errada.\n\n"
								+ "Verifique se você selecionou as informações corretas!");
			}
		} catch (SQLException e1) {
			System.out.println("                            _ ._  _ , _ ._\n"
					+ "                          (_ ' ( `  )_  .__)\n"
					+ "                        ( (  (    )   `)  ) _)\n"
					+ "                       (__ (_   (_ . _) _) ,__)\n"
					+ "                           `~~`\\ ' . /`~~`\n" + "                           ,::: ;   ; :::,\n"
					+ "                          ':::::::::::::::'\n"
					+ "     _________________________/_ __ \\________________________\n"
					+ "    |                                                         |\n"
					+ "    |                       CONNECTION FAILED                 |\n"
					+ "    |_________________________________________________________|\n" + "\n" + "\n" + "");
			e1.printStackTrace();
			SysXL error2 = new SysXL();
			error2.alertSystem(" - Erro de Conexão", "Ops... temos um erro =(",
					"Não foi possivel estabelecer uma conexão no banco de dados.");
		}

	}
}
