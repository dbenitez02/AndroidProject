package com.example.budgettracker.model;

public class Transaction {

    private long transactionID;
    private float amountSpent;
    private String description;

    public void setID(long id) {
        transactionID = id;
    }
    public long getID() {
        return transactionID;
    }

    public void setAmountSpent(float amount) {
        amountSpent = amount;
    }

    public float getAmountSpent() {
        return amountSpent;
    }

    public void setDescription(String des) {
        description = des;
    }

    public String getDescription() {
        return description;
    }
}
