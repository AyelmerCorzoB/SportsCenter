package com.sportscenter.application.usecase.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sportscenter.domain.entities.Report;
import com.sportscenter.domain.repository.ReportRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public void generatePdfReport(int reportTypeId, int userId, String filePath) {
        List<Report> reports = repository.listAll();
        
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            
            document.add(new Paragraph("Reporte de Ventas"));
            document.add(new Paragraph("----------------------------------------"));
            
            for (Report report : reports) {
                document.add(new Paragraph("ID: " + report.getId()));
                document.add(new Paragraph("Tipo: " + report.getReportTypeId()));
                document.add(new Paragraph("Fecha: " + report.getGenerationDate()));
                document.add(new Paragraph("Usuario: " + report.getUserId()));
                document.add(new Paragraph("Ruta: " + report.getFilePath()));
                document.add(new Paragraph("----------------------------------------"));
            }
            
            document.close();
            System.out.println("Reporte PDF generado correctamente en: " + filePath);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void generarReporteVentas(String filePath) {
        generatePdfReport(1, 0, filePath);
    }

    public void generarReporteProductosMasVendidos(String filePath) {
        generatePdfReport(2, 0, filePath);
    }

    public void generarReporteIngresos(String filePath) {
        generatePdfReport(3, 0, filePath);
    }
}
