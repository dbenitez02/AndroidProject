package com.example.budgettracker.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.budgettracker.model.Transaction;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDAO transactionDAO();
}
