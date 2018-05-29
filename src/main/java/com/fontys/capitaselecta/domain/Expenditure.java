package com.fontys.capitaselecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alex
 */
@Entity
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private String description;
    private String type;
    private double spent;
    private String currency;
    private long debtID;

    public Expenditure() { }

    public Expenditure(String date, String description, String type, double spent, String currency) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getDebtID() {
        return debtID;
    }

    public void setDebtID(long debtID) {
        this.debtID = debtID;
    }
}