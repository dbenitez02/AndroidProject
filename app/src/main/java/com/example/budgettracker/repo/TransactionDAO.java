package com.example.budgettracker.repo;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.budgettracker.model.Transaction;
import java.util.List;

@Dao
public interface TransactionDAO {
    @Query("SELECT * FROM 'Transaction' WHERE id = :id")
    LiveData<Transaction> getTransaction(long id);

    @Query("SELECT * FROM `Transaction` ORDER BY id COLLATE NOCASE")
    LiveData<List<Transaction>> getTransactions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);
}


