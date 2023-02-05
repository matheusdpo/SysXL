package br.com.dolomia.sysxl.controller;

import br.com.dolomia.sysxl.bean.ExcelBean;
import br.com.dolomia.sysxl.database.Datas;
import br.com.dolomia.sysxl.excel.ExcelFileHandler;
import br.com.dolomia.sysxl.utils.PathExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class SysXLController implements Initializable {
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
    private final Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private final ObservableList<String> SelectMonth = FXCollections.observableArrayList("Escolha", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chcMonth.setValue("Escolha");
        this.chcYear.setValue("Escolha");

        ObservableList<String> SelectYear = FXCollections.observableArrayList();

        for (Integer i = 2017; i <= this.currentYear; i++) {
            this.chcYear.getItems().add(i.toString());
        }

        this.chcMonth.getItems().addAll(this.SelectMonth);
        this.chcYear.getItems().addAll(SelectYear);

        this.lblAnoAutomatico.setText(this.currentYear.toString());
    }
    public void actionConverter() {
        Integer monthSelected = this.chcMonth.getSelectionModel().getSelectedIndex();

        Integer yearSelected = Integer.parseInt(this.chcYear.getValue());

        ExcelBean excelBean = Datas.getDatas(monthSelected, yearSelected);

        ExcelFileHandler.init(excelBean, PathExcel.getPathExcel(monthSelected, yearSelected));
    }
}