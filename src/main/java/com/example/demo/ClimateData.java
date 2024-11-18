package com.example.demo;

public class ClimateData {
    private String year;
    private String month;
    private String type; // precipitation or temperature
    private double value;

    public ClimateData(String year, String month, String type, double value) {
        this.year = year;
        this.month = month;
        this.type = type;
        this.value = value;
    }

    // Getters y Setters
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
