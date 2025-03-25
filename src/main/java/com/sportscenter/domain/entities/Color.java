package com.sportscenter.domain.entities;

public class Color {
    private int id;
    private String name;
    private String hexCode;

    public Color() {}

    public Color(int id) {
        this.id = id;
    }

    public Color(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHexCode() {
        return hexCode;
    }
    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    @Override
    public String toString() {
        return "Color [id=" + id + ", name=" + name + ", hexCode=" + hexCode + "]";
    }
}