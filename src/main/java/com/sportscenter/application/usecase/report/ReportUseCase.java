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

    public List<Report> getReportsByType() {
        return repository.listAll();
    }

    // public List<Report> getReportsByUser(int userId) {
    //     return repository.findByUserId(userId);
    // }

    public void updateReportFilePath(int id, String newFilePath) {
        Report report = repository.searchById(id);
        report.setFilePath(newFilePath);
        repository.update(report);
    }

    public void deleteReport(int id) {
        repository.delete(id);
    }
}