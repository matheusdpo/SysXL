package br.com.dolomia.sysxl.utils;

public class SelectDB {
    public static String getSelectDB(Integer monthSelected, Integer yearSelected) {
        if (monthSelected > 0 && monthSelected < 10) {
            return "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = " + yearSelected + " and mes = 0" + monthSelected + " AND b.iis_pai is NULL";
        } else {
            return "SELECT a.cnpj_fornecedor, a.num_nf, a.guia_trafego, b.cod_produto, c.den_item, b.iis_embal, a.dat_exportacao FROM tim_fogo_nf_mestre a INNER JOIN tim_fogo_nf_item_embal b ON b.id = a.id INNER JOIN tim_fogo_item c ON b.cod_produto = c.cod_produto " + "WHERE ano = " + yearSelected + " and mes = " + monthSelected + " AND b.iis_pai is NULL";
        }
    }
}