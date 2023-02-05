package br.com.dolomia.sysxl.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelBean {
    private List<String> cnpj_fornecedor;
    private List<String> num_nf;
    private List<String> guia_trafego;
    private List<String> cod_produto;
    private List<String> dat_exportacao;
    private List<String> den_item;
    private List<String> iis_embal;
}