package com.example.myapplication;

import java.io.Serializable;

public class Expense implements Serializable {

    private final long id;
    private String name;
    private double amount;
    private String category;
    private long dateMillis;
    private String note;

    public Expense(long id, String name, double amount, String category, long dateMillis, String note) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.dateMillis = dateMillis;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public String getNote() {
        return note;
    }
}
