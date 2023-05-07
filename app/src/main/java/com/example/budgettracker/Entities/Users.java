package com.example.budgettracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userID")
    public long _userID;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "budget")
    public float budget;

    // No, I would not store the password like this, this is for presentation purposes.
    // Proper storage of passwords is to create a hash and store that hash into the database.
    @ColumnInfo(name ="password")
    public String password;
}
