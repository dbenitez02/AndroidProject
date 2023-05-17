package com.example.budgettracker.repo;

import androidx.lifecycle.LiveData;
import android.content.Context;
import androidx.room.Room;
import com.example.budgettracker.model.Transaction;
import java.util.*;

public class TransactionRepository {

    private static TransactionRepository mTransactionRepo;
    private final TransactionDAO mTransactionDAO;

    public static TransactionRepository getInstance(Context context) {
        if (mTransactionRepo == null) {
            mTransactionRepo = new TransactionRepository(context);
        }

        return mTransactionRepo;
    }

    private TransactionRepository(Context context) {
        TransactionDatabase database = Room.databaseBuilder(context, TransactionDatabase.class, "transactions.db")
                                        .allowMainThreadQueries()
                                        .build();
        mTransactionDAO = database.transactionDAO();

        if(mTransactionDAO.getTransactions() == null) {
            addStarterData();
        }
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
        long transactionId = mTransactionDAO.addTransaction(transaction);
        transaction.setTransactionID(transactionId);
    }

    public LiveData<Transaction> getTransaction(long transactionID) {
        return mTransactionDAO.getTransaction(transactionID);
    }

    public LiveData<List<Transaction>> getTransactions() {
        return mTransactionDAO.getTransactions();
    }

    public void deleteTransaction(Transaction transaction) {
        mTransactionDAO.deleteTransaction(transaction);
    }


}
