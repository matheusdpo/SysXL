package br.com.dolomia.sysxl.excel;

import br.com.dolomia.sysxl.bean.ExcelBean;
import br.com.dolomia.sysxl.utils.LogUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileHandler {

    public static void init(ExcelBean excelBean, String path) throws IOException {
        LogUtils.registerLog("Creating Excel File");
        LogUtils.registerLog("Path -> " + path);

        XSSFWorkbook workbook = null;
        FileOutputStream fos;
        try {
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sysfogo");

            for (int i = 0; i < excelBean.getCnpj_fornecedor().size(); i++) {
                XSSFRow row = sheet.createRow(i);

                if (i == 0) {
                    row.createCell(0).setCellValue("cnpj_fornecedor");
                    row.createCell(1).setCellValue("num_nf");
                    row.createCell(2).setCellValue("guia_trafego");
                    row.createCell(3).setCellValue("cod_produto");
                    row.createCell(4).setCellValue("den_item");
                    row.createCell(5).setCellValue("iis_embal");
                    row.createCell(6).setCellValue("dat_exportacao");
                    continue;
                }
                row.createCell(0).setCellValue(excelBean.getCnpj_fornecedor().get(i - 1));
                row.createCell(1).setCellValue(excelBean.getNum_nf().get(i - 1));
                row.createCell(2).setCellValue(excelBean.getGuia_trafego().get(i - 1));
                row.createCell(3).setCellValue(excelBean.getCod_produto().get(i - 1));
                row.createCell(4).setCellValue(excelBean.getDen_item().get(i - 1));
                row.createCell(5).setCellValue(excelBean.getIis_embal().get(i - 1));
                row.createCell(6).setCellValue(excelBean.getDat_exportacao().get(i - 1));
            }
        } finally {
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
            workbook.close();
            LogUtils.registerLog("Excel has been created successfully");
        }
    }
}