package com.sportscenter.application.usecase.report;

import com.sportscenter.domain.entities.Report;
import com.sportscenter.domain.repository.ReportRepository;
import java.util.List;

public class ReportUseCase {
    private final ReportRepository repository;

    public ReportUseCase(ReportRepository repository) {
        this.repository = repository;
    }

    public void generateReport(int reportTypeId, int userId,
            String filePath, String parameters) {
        Report report = new Report();
        report.setReportTypeId(reportTypeId);
        report.setUserId(userId);
        report.setFilePath(filePath);
        report.setParameters(parameters);
        repository.save(report);
    }

    public Report getReportById(int id) {
        return repository.searchById(id);
    }

    public List<Report> getAllReports() {
        return repository.listAll();
    }

    public void updateReport(Report report) {
        repository.update(report);
    }

    public void deleteReport(int id) {
        repository.delete(id);
    }

    // Método auxiliar para convertir nombres de tipo a IDs
    public int getTypeIdByName(String typeName) {
        switch (typeName.toUpperCase()) {
            case "SALES":
                return 1;
            case "INVENTORY":
                return 2;
            case "CUSTOMERS":
                return 3;
            default:
                return 0;
        }
    }

    public Object listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }
}