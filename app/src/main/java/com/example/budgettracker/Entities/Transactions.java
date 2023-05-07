package com.example.budgettracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Users.class, parentColumns = "transactionID",
                        childColumns = "userID", onDelete = CASCADE))
public class Transactions {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionID")
    private long transactionID;

    @NonNull
    @ColumnInfo(name = "amount")
    private float amount;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    //This will be le foreign key.
    @NonNull
    @ColumnInfo(name ="userID")
    private long userID;

    public void setID(long id) {
        userID = id;
    }
}
