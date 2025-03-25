package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Report {
    private int id;
    private int reportTypeId;
    private LocalDate generationDate;
    private int userId;
    private String filePath;
    private String parameters;
    private LocalDateTime createdAt;

    public Report() {}

    public Report(int id) {
        this.id = id;
    }

    public Report(int reportTypeId, LocalDate generationDate, int userId, String filePath) {
        this.reportTypeId = reportTypeId;
        this.generationDate = generationDate;
        this.userId = userId;
        this.filePath = filePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReportTypeId() {
        return reportTypeId;
    }
    public void setReportTypeId(int reportTypeId) {
        this.reportTypeId = reportTypeId;
    }
    public LocalDate getGenerationDate() {
        return generationDate;
    }
    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getParameters() {
        return parameters;
    }
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", reportTypeId=" + reportTypeId + ", generationDate=" + generationDate + "]";
    }
}