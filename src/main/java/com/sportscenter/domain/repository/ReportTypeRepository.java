package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.ReportType;

public interface ReportTypeRepository {
    void save(ReportType reportType);
    ReportType findById(int id);
    List<ReportType> findAll();
    void update(ReportType reportType);
    void delete(int id);
}