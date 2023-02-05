package br.com.dolomia.sysxl.database;

import br.com.dolomia.sysxl.bean.ExcelBean;
import br.com.dolomia.sysxl.utils.AlertScreen;
import br.com.dolomia.sysxl.utils.LogUtils;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Datas {

    public static ExcelBean getDatas(Integer monthSelected, Integer yearSelected) {

        LogUtils.registerLog("Capturing datas.");

        ExcelBean excelBean = new ExcelBean();

        List<String> listCNPJ = new ArrayList<>();
        List<String> listNumNF = new ArrayList<>();
        List<String> listGuiaTrafego = new ArrayList<>();
        List<String> listCodProduto = new ArrayList<>();
        List<String> listDenItem = new ArrayList<>();
        List<String> listIISEmbal = new ArrayList<>();
        List<String> listDatExportacao = new ArrayList<>();

        try {
            ResultSet data = ConnectionDolomiaDB.getResultSet(monthSelected, yearSelected);

            while (data.next()) {
                listCNPJ.add(data.getString("a.cnpj_fornecedor"));
                listNumNF.add(data.getString("a.num_nf"));
                listGuiaTrafego.add(data.getString("a.guia_trafego"));
                listCodProduto.add(data.getString("a.cod_produto"));
                listDenItem.add(data.getString("a.den_item"));
                listIISEmbal.add(data.getString("a.iis_embal"));
                listDatExportacao.add(data.getString("a.dat_exportacao"));
            }

            excelBean.setCnpj_fornecedor(listCNPJ);
            excelBean.setCod_produto(listCodProduto);
            excelBean.setDat_exportacao(listDatExportacao);
            excelBean.setDen_item(listDenItem);
            excelBean.setIis_embal(listIISEmbal);
            excelBean.setGuia_trafego(listGuiaTrafego);
            excelBean.setNum_nf(listNumNF);

        } catch (SQLException e) {
            AlertScreen.getAlert("ERRO", "ERRO AO CAPTURAR DADOS NO BANCO DE DADOS", e.getMessage(), Alert.AlertType.ERROR);
            LogUtils.registerError("Error capturing datas -> " + e.getMessage());
            throw new RuntimeException(e);
        }
        return excelBean;
    }
}
