package com.example.budgettracker.DAO;

import androidx.room.*;

import com.example.budgettracker.Entities.Transactions;
import com.example.budgettracker.model.Transaction;


import java.util.List;

@Dao
public interface TransactionDAO {
    @Query("SELECT * FROM Transactions WHERE transactionID = :id")
    Transactions getTransaction(long id);

    @Query("SElECT * FROM Transactions WHERE userID = :userID ORDER BY userID")
    List<Transaction> getTransactions(long userID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addTransaction(Transaction transaction);

    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

}
