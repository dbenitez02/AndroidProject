package com.example.budgettracker.repo;

import android.content.Context;
import com.example.budgettracker.model.Transaction;
import java.util.*;

public class TransactionRepository {
    private static TransactionRepository mTransactionRepo;
    private final List<Transaction> mTransactionList;

    public static TransactionRepository getInstance(Context context) {
        if (mTransactionRepo == null) {
            mTransactionRepo = new TransactionRepository(context);
        }

        return mTransactionRepo;
    }

    private TransactionRepository(Context context) {
        mTransactionList = new ArrayList<>();

        addStarterData();
    }

    private void addStarterData() {

        Transaction transaction = new Transaction();
        transaction.setTransactionID(1);
        transaction.setAmountSpent(50);
        transaction.setDescription("Video games");
        addTransaction(transaction);

        transaction = new Transaction();
        transaction.setTransactionID(2);
        transaction.setAmountSpent(35);
        transaction.setDescription("Gas");
        addTransaction(transaction);

        transaction = new Transaction();
        transaction.setTransactionID(3);
        transaction.setAmountSpent(85);
        transaction.setDescription("Groceries");
        addTransaction(transaction);
    }

    public void addTransaction(Transaction transaction) {
        mTransactionList.add(transaction);
    }

    public Transaction getTransaction(long transactionID) {
        for (Transaction transaction : mTransactionList) {
            if (transaction.getTransactionID() == transactionID) {
                return transaction;
            }
        }

        return null;
    }

    public List<Transaction> getTransactions() {
        return mTransactionList;
    }


}
