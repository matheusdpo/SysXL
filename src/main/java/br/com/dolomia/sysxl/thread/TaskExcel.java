package br.com.dolomia.sysxl.thread;

import br.com.dolomia.sysxl.bean.ExcelBean;
import br.com.dolomia.sysxl.database.Datas;
import br.com.dolomia.sysxl.excel.ExcelFileHandler;
import br.com.dolomia.sysxl.utils.AlertScreen;
import br.com.dolomia.sysxl.utils.LogUtils;
import br.com.dolomia.sysxl.utils.PathExcel;
import javafx.scene.control.Alert;

import java.io.IOException;

public class TaskExcel implements Runnable{
    private final Integer monthSelected;
    private final Integer yearSelected;

    public TaskExcel(Integer monthSelected, Integer yearSelected){
        this.monthSelected = monthSelected;
        this.yearSelected = yearSelected;
    }

    @Override
    public void run() {
        ExcelBean excelBean = Datas.getDatas(this.monthSelected, this.yearSelected);

        try {
            ExcelFileHandler.init(excelBean, PathExcel.getPathExcel(this.monthSelected, this.yearSelected));
        } catch (IOException e) {
            AlertScreen.getAlert("ERRO", "ERRO CRIAR ARQUIVO EXCEL", e.getMessage(), Alert.AlertType.ERROR);
            LogUtils.registerError("Error creating excel file -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}