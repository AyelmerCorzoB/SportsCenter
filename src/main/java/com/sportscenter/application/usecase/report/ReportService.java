package com.sportscenter.application.usecase.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sportscenter.domain.entities.Report;
import com.sportscenter.domain.entities.Sale;
import com.sportscenter.domain.entities.SaleDetail;
import com.sportscenter.domain.repository.ReportRepository;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.domain.repository.SaleDetailRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private final ReportRepository reportRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public ReportService(ReportRepository reportRepository, SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {
        this.reportRepository = reportRepository;
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    public void generatePdfReport(int reportTypeId, int userId, String filePath) {
        List<Report> reports = reportRepository.listAll();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph title = new Paragraph("Reporte de Ventas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

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
            System.err.println("Error al generar el reporte PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void generatePdfReportVentas(String filePath) {
        List<Sale> ventas = saleRepository.findAll();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph title = new Paragraph("Reporte de Ventas con Detalles",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            Paragraph fechaReporte = new Paragraph("Generado el: " + LocalDate.now());
            fechaReporte.setAlignment(Element.ALIGN_RIGHT);
            document.add(fechaReporte);

            document.add(Chunk.NEWLINE);

            for (Sale sale : ventas) {
                document.add(
                        new Paragraph("Venta #" + sale.getId(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                document.add(new Paragraph("Fecha: " + sale.getSaleDate()));
                document.add(new Paragraph("Cliente ID: " + sale.getCustomerId()));
                document.add(new Paragraph("Método de Pago ID: " + sale.getPaymentMethodId()));
                document.add(new Paragraph("Descuento: $" + sale.getDiscount()));
                document.add(new Paragraph("Total: $" + sale.getTotal()));
                document.add(new Paragraph("Usuario ID: " + sale.getUserId()));

                document.add(Chunk.NEWLINE);

                List<SaleDetail> detalles = saleDetailRepository.findBySaleId(sale.getId());

                if (!detalles.isEmpty()) {
                    Paragraph detallesHeader = new Paragraph("Detalles de la Venta:",
                            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                    document.add(detallesHeader);

                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    String[] headers = { "Producto ID", "Cantidad", "Precio Unitario", "Subtotal" };
                    for (String header : headers) {
                        PdfPCell cell = new PdfPCell(
                                new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

                    for (SaleDetail detalle : detalles) {
                        table.addCell(String.valueOf(detalle.getProductId()));
                        table.addCell(String.valueOf(detalle.getQuantity()));
                        table.addCell("$" + detalle.getUnitPrice());
                        table.addCell("$" + (detalle.getQuantity() * detalle.getUnitPrice()));
                    }

                    document.add(table);
                }

                document.add(new Paragraph("----------------------------------------"));
                document.add(Chunk.NEWLINE);
            }

            document.close();
            System.out.println("Reporte de ventas generado correctamente en: " + filePath);
        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar el reporte de ventas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void generarPdfReporteIngresos(String filePath) {
        List<Sale> ventas = saleRepository.findAll();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph title = new Paragraph("Reporte de Ingresos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);
            
            Paragraph fechaReporte = new Paragraph("Generado el: " + LocalDate.now());
            fechaReporte.setAlignment(Element.ALIGN_RIGHT);
            document.add(fechaReporte);
            
            document.add(Chunk.NEWLINE);
            
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            String[] headers = {"ID Venta", "Fecha", "Descuento", "Total"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            double totalIngresos = 0;
            for (Sale sale : ventas) {
                table.addCell(String.valueOf(sale.getId()));
                table.addCell(sale.getSaleDate().toString());
                table.addCell("$" + sale.getDiscount());
                table.addCell("$" + sale.getTotal());
                totalIngresos += sale.getTotal();
            }

            document.add(table);
            document.add(Chunk.NEWLINE);
            
            Paragraph totalParagraph = new Paragraph("Total Ingresos: $" + totalIngresos, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalParagraph);
            
            document.close();
            System.out.println("Reporte de ingresos generado correctamente en: " + filePath);
        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar el reporte de ingresos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void generarReporteVentas(String filePath) {
        generatePdfReport(1, 0, filePath);
    }

    public void generarReporteProductosMasVendidos(String filePath) {
        generatePdfReportVentas(filePath);
    }

    public void generarReporteIngresos(String filePath) {
        generarPdfReporteIngresos(filePath);
    }
}
