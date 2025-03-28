package com.sportscenter.application.usecase.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sportscenter.domain.entities.*;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PDFReportGenerator {
    
    private final Connection connection;
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10);

    public PDFReportGenerator(Connection connection) {
        this.connection = connection;
    }

    public void generateFullReport(String filePath) throws Exception {
        Document document = new Document(PageSize.A4.rotate()); // Horizontal para más espacio
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        
        document.open();
        
        // Encabezado del reporte
        addHeader(document);
        
        // Reporte de Ventas (SaleDetail)
        generateSaleDetailsReport(document);
        document.newPage();
        
        // Reporte de Proveedores (Supplier)
        generateSuppliersReport(document);
        document.newPage();
        
        // Reporte de Usuarios (User)
        generateUsersReport(document);
        
        // Pie de página
        addFooter(document);
        
        document.close();
    }
    
    private void addHeader(Document document) throws DocumentException {
        Paragraph header = new Paragraph();
        header.setAlignment(Element.ALIGN_CENTER);
        
        // Logo (opcional)
        // Image logo = Image.getInstance("path/to/logo.png");
        // logo.scaleToFit(100, 100);
        // header.add(logo);
        
        header.add(new Paragraph("SPORTSCENTER - REPORTE GENERAL", TITLE_FONT));
        header.add(new Paragraph("Generado el: " + java.time.LocalDate.now(), NORMAL_FONT));
        header.add(new Paragraph(" ")); // Espacio
        
        document.add(header);
    }
    
    private void addFooter(Document document) throws DocumentException {
        Paragraph footer = new Paragraph();
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.add(new Paragraph(" "));
        footer.add(new Paragraph("© 2023 SportsCenter - Todos los derechos reservados", 
            new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC)));
        
        document.add(footer);
    }
    
    private void generateSaleDetailsReport(Document document) throws Exception {
        Paragraph title = new Paragraph("DETALLES DE VENTAS", SUBTITLE_FONT);
        title.setSpacingAfter(20f);
        document.add(title);
        
        List<SaleDetail> sales = getSaleDetails();
        PdfPTable table = new PdfPTable(6); // 6 columnas
        table.setWidthPercentage(100);
        
        // Encabezados
        addTableHeader(table, "ID", "ID Venta", "ID Producto", "Cantidad", "Precio Unit.", "Subtotal");
        
        // Datos
        for (SaleDetail sale : sales) {
            table.addCell(createCell(String.valueOf(sale.getId())));
            table.addCell(createCell(String.valueOf(sale.getSaleId())));
            table.addCell(createCell(String.valueOf(sale.getProductId())));
            table.addCell(createCell(String.valueOf(sale.getQuantity())));
            table.addCell(createCell(String.format("$%.2f", sale.getUnitPrice())));
            table.addCell(createCell(String.format("$%.2f", sale.getSubtotal())));
        }
        
        document.add(table);
        
        // Resumen
        double totalVentas = sales.stream().mapToDouble(SaleDetail::getSubtotal).sum();
        Paragraph summary = new Paragraph();
        summary.add(new Paragraph(" "));
        summary.add(new Paragraph(String.format("Total de ventas: $%.2f", totalVentas), 
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        document.add(summary);
    }
    
    private void generateSuppliersReport(Document document) throws Exception {
        Paragraph title = new Paragraph("PROVEEDORES", SUBTITLE_FONT);
        title.setSpacingAfter(20f);
        document.add(title);
        
        List<Supplier> suppliers = getSuppliers();
        PdfPTable table = new PdfPTable(6); // 6 columnas
        table.setWidthPercentage(100);
        
        // Encabezados
        addTableHeader(table, "ID", "Nombre", "Teléfono", "Email", "Dirección", "RUC");
        
        // Datos
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Supplier supplier : suppliers) {
            table.addCell(createCell(String.valueOf(supplier.getId())));
            table.addCell(createCell(supplier.getName()));
            table.addCell(createCell(supplier.getPhone()));
            table.addCell(createCell(supplier.getEmail()));
            table.addCell(createCell(supplier.getAddress()));
            table.addCell(createCell(supplier.getTaxId()));
        }
        
        document.add(table);
        
        // Resumen
        Paragraph summary = new Paragraph();
        summary.add(new Paragraph(" "));
        summary.add(new Paragraph(String.format("Total de proveedores: %d", suppliers.size()), 
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        document.add(summary);
    }
    
    private void generateUsersReport(Document document) throws Exception {
        Paragraph title = new Paragraph("USUARIOS", SUBTITLE_FONT);
        title.setSpacingAfter(20f);
        document.add(title);
        
        List<User> users = getUsers();
        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100);
        
        // Encabezados
        addTableHeader(table, "ID", "Usuario", "Rol", "Activo", "Último Login");
        
        // Datos
        for (User user : users) {
            table.addCell(createCell(String.valueOf(user.getId())));
            table.addCell(createCell(user.getUsername()));
            table.addCell(createCell(user.getRole()));
            table.addCell(createCell(user.isActive() ? "Sí" : "No"));
            table.addCell(createCell(user.getLast_login() != null ? 
                user.getLast_login().toString() : "Nunca"));
        }
        
        document.add(table);
        
        // Resumen
        long activeUsers = users.stream().filter(User::isActive).count();
        Paragraph summary = new Paragraph();
        summary.add(new Paragraph(" "));
        summary.add(new Paragraph(String.format("Total usuarios: %d (%d activos)", 
            users.size(), activeUsers), 
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        document.add(summary);
    }
    
    // Métodos auxiliares
    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, HEADER_FONT));
            cell.setBackgroundColor(new BaseColor(200, 200, 200));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
    
    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content, NORMAL_FONT));
        cell.setPadding(5);
        return cell;
    }
    
    // Métodos para obtener datos de la base de datos
    private List<SaleDetail> getSaleDetails() throws Exception {
        List<SaleDetail> sales = new ArrayList<>();
        String sql = "SELECT * FROM sale_details";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                SaleDetail sale = new SaleDetail(
                    rs.getInt("sale_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price")
                );
                sale.setId(rs.getInt("id"));
                sale.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                sales.add(sale);
            }
        }
        return sales;
    }
    
    private List<Supplier> getSuppliers() throws Exception {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("tax_id")
                );
                supplier.setId(rs.getInt("id"));
                supplier.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                supplier.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
                supplier.setCreatedBy(rs.getInt("created_by"));
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }
    
    private List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    "", // No incluir password por seguridad
                    rs.getString("role")
                );
                user.setId(rs.getInt("id"));
                user.setActive(rs.getBoolean("active"));
                user.setCreated_at(rs.getTimestamp("created_at"));
                user.setLast_login(rs.getTimestamp("last_login"));
                users.add(user);
            }
        }
        return users;
    }
}