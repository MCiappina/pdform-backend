package com.example.demo.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AnexoIXDTO {

    private String faculdade;
    private String edital_num;
    private String edital_ano;
    
    private String docente_name;
    private String docente_rg;
    private String data;
    private String coordenador_name;
    private String coordenador_rg;

    // Getters and Setters
    public String getFaculdade() { return faculdade; }
    public void setFaculdade(String faculdade) { this.faculdade = faculdade; }

    public String getEdital_num() { return edital_num; }
    public void setEdital_num(String edital_num) { this.edital_num = edital_num; }

    public String getEdital_ano() { return edital_ano; }
    public void setEdital_ano(String edital_ano) { this.edital_ano = edital_ano; }

    public String getDocente_name() { return docente_name; }
    public void setDocente_name(String docente_name) { this.docente_name = docente_name; }

    public String getDocente_rg() { return docente_rg; }
    public void setDocente_rg(String docente_rg) { this.docente_rg = docente_rg; }

    public String getData() { return data; }
    public void setData_final(String data) { this.data = data; }

    public String getCoordenador_name() { return coordenador_name; }
    public void setCoordenador_name(String coordenador_name) { this.coordenador_name = coordenador_name;}

    public String getCoordenador_rg() { return coordenador_rg; }
    public void setCoordenador_rg(String coordenador_rg) { this.coordenador_rg = coordenador_rg; }

    // --- Metodos helper para a data ---
    public String getDia() {
        if (data == null || data.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(data).getDayOfMonth());
    }

    public String getMes() {
        if (data == null || data.isEmpty()) return "";
        LocalDate date = LocalDate.parse(data);
        return date.format(DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")));
    }

    public String getAno() {
        if (data == null || data.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(data).getYear());
    }
}