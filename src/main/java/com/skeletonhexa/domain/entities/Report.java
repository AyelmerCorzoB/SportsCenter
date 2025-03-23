package com.skeletonhexa.domain.entities;

import java.util.Date;

import com.skeletonhexa.domain.model.ReportType;

public class Report {

    private int id;
    private ReportType type;
    private Date generationDate;
    private int userId;
    private String filePath;

    public Report() {
    }

    public Report(ReportType type, Date generationDate, int userId, String filePath) {
        this.type = type;
        this.generationDate = generationDate;
        this.userId = userId;
        this.filePath = filePath;
    }

    public Report(int id, ReportType type, Date generationDate, int userId, String filePath) {
        this.id = id;
        this.type = type;
        this.generationDate = generationDate;
        this.userId = userId;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
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

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", type=" + type +
                ", generationDate=" + generationDate +
                ", userId=" + userId +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    
}