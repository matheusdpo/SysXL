package br.com.dolomia.sysxl.utils;

public class PathExcel {
    public static String getPathExcel(Integer monthSelected, Integer yearSelected) {
        if (monthSelected > 0 && monthSelected < 10) {
            return GetProperties.getProperty("path") + "0" + monthSelected + "-" + yearSelected + " IIS.xlsx";
        } else {
            return GetProperties.getProperty("path") + monthSelected + "-" + yearSelected + " IIS.xlsx";
        }
    }
}