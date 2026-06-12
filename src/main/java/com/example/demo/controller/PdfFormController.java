package com.example.demo.controller;

import com.example.demo.dto.AnexoVDTO;
import com.example.demo.dto.AnexoViiiDTO;
import com.example.demo.dto.AnexoIXDTO;
import com.example.demo.service.PdfGenerationService;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pdf")
public class PdfFormController {

    private final PdfGenerationService pdfService;
    private final ObjectMapper objectMapper;

    public PdfFormController(PdfGenerationService pdfService, ObjectMapper objectMapper) {
        this.pdfService = pdfService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/gerar/{numeroAnexo}")
    public ResponseEntity<byte[]> gerarDocumento(
            @PathVariable String numeroAnexo,
            @RequestBody Map<String, Object> formData) {

        try {
            Context context = new Context();
            context.setVariable("baseUrl", "http://localhost:10000");
            String templateName = "";

            // Switch statement routes the data to the correct DTO and Template
            switch (numeroAnexo.toLowerCase()) {
                case "viii":
                    // 1. Convert the generic Map into our specific DTO
                    AnexoViiiDTO dtoViii = objectMapper.convertValue(formData, AnexoViiiDTO.class);
                    
                    // 2. Load the DTO fields into the Thymeleaf Context
                    context.setVariable("faculdade", dtoViii.getFaculdade());
                    context.setVariable("edital_num", dtoViii.getEdital_num());
                    context.setVariable("edital_ano", dtoViii.getEdital_ano());
                    context.setVariable("edital_tempo", dtoViii.getEdital_tempo());
                    context.setVariable("candidato", dtoViii.getCandidato());
                    context.setVariable("statusConformidade", dtoViii.getStatusConformidade());
                    context.setVariable("justificativa", dtoViii.getJustificativa());
                    
                    // Utilize the DTO helper methods for the date
                    context.setVariable("dia", dtoViii.getDia());
                    context.setVariable("mes", dtoViii.getMes());
                    context.setVariable("ano", dtoViii.getAno());

                    context.setVariable("presidente_name", dtoViii.getPresidente_name());
                    context.setVariable("presidente_rg", dtoViii.getPresidente_rg());
                    context.setVariable("prof_name", dtoViii.getProf_name());
                    context.setVariable("prof_rg", dtoViii.getProf_rg());
                    context.setVariable("tecnico_name", dtoViii.getTecnico_name());
                    context.setVariable("tecnico_rg", dtoViii.getTecnico_rg());
                    
                    templateName = "edital-VIII";
                    break;
                    
                case "v":
                        AnexoVDTO dtoV = objectMapper.convertValue(formData, AnexoVDTO.class);
                        
                        context.setVariable("faculdade", dtoV.getFaculdade());
                        context.setVariable("edital_num", dtoV.getEdital_num());
                        context.setVariable("edital_ano", dtoV.getEdital_ano());
                        context.setVariable("docente_nome", dtoV.getDocente_nome());
                        context.setVariable("docente_rg", dtoV.getDocente_rg());
                        
                        context.setVariable("dia", dtoV.getDia());
                        context.setVariable("mes", dtoV.getMes());
                        context.setVariable("ano", dtoV.getAno());
    
                        // Pass the arrays for the tables
                        context.setVariable("candidato_nome", dtoV.getCandidato_nome());
                        context.setVariable("candidato_rg", dtoV.getCandidato_rg());
                        context.setVariable("aulas_fatec", dtoV.getAulas_fatec());
                        context.setVariable("contrato", dtoV.getContrato());
                        context.setVariable("pontos", dtoV.getPontos());
                        context.setVariable("indeferido_rg", dtoV.getIndeferido_rg());
    
                        templateName = "edital-V";
                        break;
                    
                case "ix":
                    // Setup logic for Anexo IX...
                        AnexoIXDTO dtoIX = objectMapper.convertValue(formData, AnexoIXDTO.class);

                        context.setVariable("faculdade", dtoIX.getFaculdade());
                        context.setVariable("edital_num", dtoIX.getEdital_num());
                        context.setVariable("edital_ano", dtoIX.getEdital_ano());
                        context.setVariable("docente_name", dtoIX.getDocente_name());
                        context.setVariable("docente_rg", dtoIX.getDocente_rg());
                        context.setVariable("data", dtoIX.getData());
                        context.setVariable("coordenador_name", dtoIX.getCoordenador_name());
                        context.setVariable("coordenador_rg", dtoIX.getCoordenador_rg());

                        context.setVariable("dia", dtoIX.getDia());
                        context.setVariable("mes", dtoIX.getMes());
                        context.setVariable("ano", dtoIX.getAno());
                        
                        templateName = "edital-IX";
                        break;
                case "iii":
                    // proximo anexo...
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Call the same OpenHTMLtoPDF service you built earlier
            byte[] pdfBytes = pdfService.generatePdfFromContext(templateName, context);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Anexo_" + numeroAnexo.toUpperCase() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}