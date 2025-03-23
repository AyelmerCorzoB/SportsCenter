package com.skeletonhexa.application.usecase.report;

import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Report;
import com.skeletonhexa.domain.model.ReportType;
import com.skeletonhexa.domain.repository.ReportRepository;

public class ReportUseCase {
    private final ReportRepository repository;

    public ReportUseCase(ReportRepository repository){
        this.repository = repository;
    }

    public void registerReport(ReportType type, Date generationDate, int userId, String filePath){
        Report report = new Report(type, generationDate, userId, filePath);
        repository.save(report);
    }

    public Report getReport(int id){
        return repository.searchById(id);
    }

    public List<Report> ListReports(){
        return repository.listAll();
    }

    public void updateCategry(int id, ReportType type, Date generationDate, int userId, String filePath){
        Report report = new Report(id, type, generationDate, userId, filePath);
        repository.update(report);
    }

    public void deleteReport(int id){
        repository.delete(id);
    }
}