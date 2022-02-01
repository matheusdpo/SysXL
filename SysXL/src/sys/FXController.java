package sys;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class FXController implements Initializable {

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

	private int line = 1;

	private int percent = 0;

	static SysXL version = new SysXL();

	ObservableList<String> SelectMonth = FXCollections.observableArrayList("Escolha", "Janeiro", "Fevereiro", "Março",
			"Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

	ObservableList<String> SelectYear = FXCollections.observableArrayList("Escolha", "2017", "2018", "2019", "2020",
			"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Adding ChoiceBox Values...");
		chcMonth.getItems().addAll(SelectMonth);
		chcYear.getItems().addAll(SelectYear);
		chcMonth.setValue("Escolha");
		chcYear.setValue("Escolha");
		System.out.println("ChoiceBox Values added.\n");
	}

	public void actionDonate() throws Exception {
		URI link = new URI("https://linktr.ee/matheusdpo_");
		Desktop.getDesktop().browse(link);
	}

	public void actionHelp() throws Exception {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SysXL " + version.getVersion() + " - Help");
		alert.setResizable(true);
		alert.setHeaderText("ATENÇÃO");
		alert.setContentText("Para editar login, senha, IP ou Diretorio:"
				+ "\n1. Vá em C:\\SysXL\\ e abra o arquivo 'file.txt';" + "\n2. Edite os parâmetros que deseja.\n"
				+ "\nObservações:" + "\nCada linha está reservada com um parametro:"
				+ "\n1º Linha: url/IP para acesso ao banco de dados;" + "\n2º Linha: usuario do banco de dados;"
				+ "\n3º Linha: senha do banco de dados;" + "\n4º Linha: Diretorio onde será salvo.");
		alert.show();
	}

	public void actionConverter() throws Exception {

		SysXL callingUser = new SysXL();
		callingUser.fileTxt(1);
		String userCalled = callingUser.getValueTxt();
		String user = userCalled; // DB user;
		System.out.println("Your username = " + user);

		SysXL callingPw = new SysXL();
		callingPw.fileTxt(2);
		String userPw = callingPw.getValueTxt();
		String passwd = userPw; // DB password;
		System.out.println("Your password = " + passwd);

		SysXL callingIP = new SysXL();
		callingIP.fileTxt(0);
		String ipCalled = callingIP.getValueTxt();
		String url = ipCalled; // JDBC + IPv4 DB.
		System.out.println("Your IP / url = " + url);

		SysXL callingPath = new SysXL();
		callingPath.fileTxt(3);
		String pathCalled = callingPath.getValueTxt();
		System.out.println("Your Path = " + pathCalled);

		/*
		 * Algorithm to create Sysfogo report.xls
		 */
		int monthQuery = chcMonth.getSelectionModel().getSelectedIndex();
		/*
		 * Field "mês" (month) in my select is necessary to add zero before the month
		 * (1-12) That's why I selected the index in the ChoiceBox Month instead the
		 * Value
		 */

		String yearQuery = chcYear.getValue(); // get the year in the ChoiceBox

		String select = ""; // start the select query

		String path = "";

		/*
		 * Start the Folder that the file will be saved. It may be different later when
		 * I add the feature to choose where it'll be saved.
		 */

		/*
		 * Change the SELECT Query
		 */
		if (monthQuery < 10 && monthQuery >= 1) {
			String selectTST = "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, "
					+ "b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b "
					+ "ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = "
					+ yearQuery + " and mes = " + "0" + monthQuery + " AND b.iis_pai is NULL";
			select = selectTST;
			System.out.println("if/else for select runned");
		} else {
			String selectTST = "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, "
					+ "b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b "
					+ "ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = "
					+ yearQuery + " and mes = " + monthQuery + " AND b.iis_pai is NULL";
			select = selectTST;
			System.out.println("if/else for select runned");
		}

		/*
		 * Change the Folder and the name of the File
		 */
		if (monthQuery < 10 && monthQuery >= 1) {
			String pathTST = pathCalled + "0" + monthQuery + "-" + yearQuery + " IIS.xls";
			path = pathTST;
			System.out.println("if/else for folder and file name runned");
			System.out.println("Your file will be saved in = " + path);
		} else {
			String pathTST = pathCalled + monthQuery + "-" + yearQuery + " IIS.xls";
			path = pathTST;
			System.out.println("if/else for folder and file name runned");
			System.out.println("Your file will be saved in = " + path);
		}

		/*
		 * Starting POI Apache API Creating Excel File
		 */
		System.out.println("Excel file has been created.\n");
		HSSFWorkbook sysheet = new HSSFWorkbook();
		HSSFSheet sheet = sysheet.createSheet("Sysfogo"); // Title
		HSSFRow header = sheet.createRow((short) 0); // Line
		System.out.println("Excel file has been created.\n");

		/*
		 * Column and Header/Title
		 */
		System.out.println("Adding the Header/Title.\n");
		header.createCell(0).setCellValue("cnpj_fornecedor");
		header.createCell(1).setCellValue("num_nf");
		header.createCell(2).setCellValue("guia_trafego");
		header.createCell(3).setCellValue("cod_produto");
		header.createCell(4).setCellValue("den_item");
		header.createCell(5).setCellValue("iis_embal");
		header.createCell(6).setCellValue("dat_exportacao");

		/*
		 * Trying to connect to MysqlDB
		 */
		System.out.println("Trying to connecto to the DB");
		System.out.println(url);
		System.out.println(user);
		System.out.println(passwd);
		try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
			System.out.println("Connected!\n");
			/*
			 * Trying to run a Query
			 */
			System.out.println("Trying to run a Query");
			try (PreparedStatement stmt = conn.prepareStatement(select)) {
				ResultSet data = stmt.executeQuery();
				/*
				 * Copying data from DB to Excel
				 */

				System.out.println("Selected executed\n");

				/*
				 * While there are files, add one more line
				 */
				System.out.println("Copying data from DB to Excel");
				while (data.next()) {
					/*
					 * Getting the DB Data and putting into Excel
					 */
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

					/*
					 * Show progress
					 */
					if (this.percent > 100) {
						continue;
					} else if (this.percent == 100) {
						System.out.println("Loading files: 100%\n");
					} else {
						System.out.println("Loading files: " + this.percent + "%\n");
					}
				}

				/*
				 * Finishing the file
				 */
				System.out.println("Done!!!\n");
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle(version.getVersion() + " - Concluído");
				alert.setHeaderText("Exportação concluída com sucesso =)");
				alert.setContentText("Seu arquivo foi exportado para o diretorio:\n" + path);
				alert.show();
			} catch (SQLException e) {
				System.out.println("SELECT failed!");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(version.getVersion() + " - Erro de Banco de Dados");
				alert.setHeaderText("Ops... temos um erro =(");
				alert.setContentText("A Select do banco de dados pode estar errada.\n"
						+ "Verifique se você selecionou as informações corretas!");
				alert.show();
			}
		} catch (SQLException e1) {
			System.out.println("Connection failed!");
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(version.getVersion() + " - Erro de Conexão");
			alert.setHeaderText("Ops... temos um erro =(");
			alert.setContentText("Não foi possivel estabelecer uma conexão no banco de dados.");
			alert.show();
		}

	}
}
