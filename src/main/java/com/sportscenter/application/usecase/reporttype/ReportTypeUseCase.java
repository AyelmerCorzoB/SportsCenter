package com.sportscenter.application.usecase.reporttype;

import com.sportscenter.domain.entities.ReportType;
import com.sportscenter.domain.repository.ReportTypeRepository;
import java.util.List;

public class ReportTypeUseCase {
    private final ReportTypeRepository repository;

    public ReportTypeUseCase(ReportTypeRepository repository) {
        this.repository = repository;
    }

    public void registerReportType(String typeName, String description) {
        ReportType reportType = new ReportType();
        reportType.setTypeName(typeName);
        reportType.setDescription(description);
        repository.save(reportType);
    }

    public ReportType getReportTypeById(int id) {
        return repository.findById(id);
    }

    public List<ReportType> getAllReportTypes() {
        return repository.findAll();
    }

    public void updateReportType(int id, String typeName, String description) {
        ReportType reportType = new ReportType();
        reportType.setId(id);
        reportType.setTypeName(typeName);
        reportType.setDescription(description);
        repository.update(reportType);
    }

    public void deleteReportType(int id) {
        repository.delete(id);
    }
}