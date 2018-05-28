package com.fontys.capitaselecta.domain;

/**
 *
 * @author Alex
 */
public class Expenditure {
    private long id;
    private String date;
    private String description;
    private String type;
    private double spent;
    private char currency;

    public Expenditure() { }

    public Expenditure(long id, String date, String description, String type, double spent, char currency) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.type = type;
        this.spent = spent;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }
}