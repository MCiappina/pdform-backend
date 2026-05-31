package com.example.demo.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class AnexoViiiDTO {
    private String faculdade;
    private String edital_num;
    private String edital_ano;
    private String edital_tempo; // DETERMINADO or INDETERMINADO
    private String candidato;
    private String statusConformidade; // CONFORME or INCONFORME
    private String justificativa;
    private String data; // Frontend sends "YYYY-MM-DD"
    private String presidente_name;
    private String presidente_rg;
    private String prof_name;
    private String prof_rg;
    private String tecnico_name;
    private String tecnico_rg;

    public String getFaculdade() {
        return faculdade;
    }
    public String getEdital_num() {
        return edital_num;
    }
    public String getEdital_ano() {
        return edital_ano;
    }
    public String getEdital_tempo() {
        return edital_tempo;
    }
    public String getCandidato() {
        return candidato;
    }
    public String getStatusConformidade() {
        return statusConformidade;
    }
    public String getJustificativa() {
        return justificativa;
    }
    public String getData() {
        return data;
    }
    public String getPresidente_name() {
        return presidente_name;
    }
    public String getPresidente_rg() {
        return presidente_rg;
    }
    public String getProf_name() {
        return prof_name;
    }
    public String getProf_rg() {
        return prof_rg;
    }

    public String getTecnico_name() {
        return tecnico_name;
    }
    public String getTecnico_rg() {
        return tecnico_rg;
    }
    public String getDia() {
        if (data == null || data.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(this.data).getDayOfMonth());
    }

    public String getMes() {
        if (data == null || data.isEmpty()) return "";
        Locale ptBr = new Locale("pt", "BR");
        return LocalDate.parse(this.data).getMonth().getDisplayName(TextStyle.FULL, ptBr);
    }

    public String getAno() {
        if (data == null || data.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(this.data).getYear()).substring(2);
    }
}