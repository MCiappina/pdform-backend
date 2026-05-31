package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AnexoVDTO {

    private String faculdade;
    private String edital_num;
    private String edital_ano;
    
    private String docente_nome;
    private String docente_rg;
    private String data_final;

    // These map exactly to the JS keys being sent in the POST request
    @JsonProperty("candidato_nome[]")
    private List<String> candidato_nome;

    @JsonProperty("candidato_rg[]")
    private List<String> candidato_rg;

    @JsonProperty("aulas_fatec[]")
    private List<String> aulas_fatec;

    @JsonProperty("contrato[]")
    private List<String> contrato;

    @JsonProperty("pontos[]")
    private List<String> pontos;

    @JsonProperty("indeferido_rg[]")
    private List<String> indeferido_rg;

    // Getters and Setters
    public String getFaculdade() { return faculdade; }
    public void setFaculdade(String faculdade) { this.faculdade = faculdade; }

    public String getEdital_num() { return edital_num; }
    public void setEdital_num(String edital_num) { this.edital_num = edital_num; }

    public String getEdital_ano() { return edital_ano; }
    public void setEdital_ano(String edital_ano) { this.edital_ano = edital_ano; }

    public String getDocente_nome() { return docente_nome; }
    public void setDocente_nome(String docente_nome) { this.docente_nome = docente_nome; }

    public String getDocente_rg() { return docente_rg; }
    public void setDocente_rg(String docente_rg) { this.docente_rg = docente_rg; }

    public String getData_final() { return data_final; }
    public void setData_final(String data_final) { this.data_final = data_final; }

    public List<String> getCandidato_nome() { return candidato_nome; }
    public void setCandidato_nome(List<String> candidato_nome) { this.candidato_nome = candidato_nome; }

    public List<String> getCandidato_rg() { return candidato_rg; }
    public void setCandidato_rg(List<String> candidato_rg) { this.candidato_rg = candidato_rg; }

    public List<String> getAulas_fatec() { return aulas_fatec; }
    public void setAulas_fatec(List<String> aulas_fatec) { this.aulas_fatec = aulas_fatec; }

    public List<String> getContrato() { return contrato; }
    public void setContrato(List<String> contrato) { this.contrato = contrato; }

    public List<String> getPontos() { return pontos; }
    public void setPontos(List<String> pontos) { this.pontos = pontos; }

    public List<String> getIndeferido_rg() { return indeferido_rg; }
    public void setIndeferido_rg(List<String> indeferido_rg) { this.indeferido_rg = indeferido_rg; }

    // --- Helper Methods for the Date ---
    public String getDia() {
        if (data_final == null || data_final.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(data_final).getDayOfMonth());
    }

    public String getMes() {
        if (data_final == null || data_final.isEmpty()) return "";
        LocalDate date = LocalDate.parse(data_final);
        return date.format(DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")));
    }

    public String getAno() {
        if (data_final == null || data_final.isEmpty()) return "";
        return String.valueOf(LocalDate.parse(data_final).getYear());
    }
}