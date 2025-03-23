package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Report;

public interface ReportRepository {
    void save(Report report);
    Report searchById(int id);
    List<Report> listAll();
    void update(Report report);
    void delete(int id);
}
