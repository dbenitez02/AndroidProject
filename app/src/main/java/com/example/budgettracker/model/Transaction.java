package com.example.budgettracker.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;



public class Transaction {

    private long transactionID;
    private float amountSpent;
    private String description;

    public long getTransactionID() {

        return transactionID;
    }
    public void setTransactionID(long id) {
        transactionID = id;
    }

    public void setAmountSpent(float amount) {

        amountSpent = amount;
    }

    public float getAmountSpent() {

        return amountSpent;
    }

    public void setDescription(@NonNull String des) {

        description = des;
    }

    @NonNull
    public String getDescription() {

        return description;
    }

    public String getText() {
        return description + "\n" +
                "Amount spent: " + amountSpent + "\n" +
                "ID: " + transactionID;
    }
}
