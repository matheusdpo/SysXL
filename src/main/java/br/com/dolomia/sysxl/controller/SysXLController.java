package br.com.dolomia.sysxl.controller;

import br.com.dolomia.sysxl.thread.TaskExcel;
import br.com.dolomia.sysxl.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    @FXML
    public void actionConverter() {

        LogUtils.registerLog("Button converted has been pressed");

        Integer monthIndex = this.chcMonth.getSelectionModel().getSelectedIndex();

        Integer yearIndex = this.chcYear.getSelectionModel().getSelectedIndex();

        String monthValue = this.chcMonth.getValue();

        String yearValue = this.chcYear.getValue();

        LogUtils.registerLog("Month: " + monthValue);
        LogUtils.registerLog("Year: " + yearValue);

        if (monthIndex < 1 || yearIndex < 0) {
            AlertScreen.getAlert("ESCOLHA UM VALOR", "Ops...", "Por favor, selecione um valor nos campos \"mês\" e \"ano\"", Alert.AlertType.INFORMATION);
            LogUtils.registerError("User has been chosen an invalid option.");
            return;
        }

        new Thread(new TaskExcel(monthIndex, Integer.parseInt(yearValue)), "TaskExcel").start();

        AlertScreen.getAlertDone(monthValue, yearValue);
    }

    @FXML
    public void openBrowser() {
        PlaySong.play();
        OpenBrowser.init();
    }
}