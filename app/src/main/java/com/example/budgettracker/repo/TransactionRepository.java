package com.example.budgettracker.repo;

import androidx.lifecycle.LiveData;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import com.example.budgettracker.model.Transaction;
import java.util.*;

public class TransactionRepository {

    private static TransactionRepository mTransactionRepo;
    private final TransactionDAO mTransactionDAO;
    private LiveData<List<Transaction>> allTransactions;

    /**
     * USELESS?
     * @param context
     * @return
     */
    public static TransactionRepository getInstance(Context context) {
        if (mTransactionRepo == null) {
            mTransactionRepo = new TransactionRepository(context);
        }

        return mTransactionRepo;
    }

    // Constructor for variables.
    private TransactionRepository(Context context) {
        TransactionDatabase database = TransactionDatabase.getInstance(context);
        mTransactionDAO = database.transactionDAO();

        allTransactions = mTransactionDAO.getTransactions();

    }


    public void addTransaction(Transaction transaction) {
        mTransactionDAO.addTransaction(transaction);
    }

//    public LiveData<Transaction> getTransaction(long transactionID) {
//        return mTransactionDAO.getTransaction(transactionID);
//    }


    public void deleteTransaction(Transaction transaction) {
        mTransactionDAO.deleteTransaction(transaction);
    }

    //
    // THIS WILL REPLACE METHODS ABOVE FOR ASYNC TASKS DEFINED BELOW
    //

    // Add atransaction to the database
    public void insert(Transaction transaction) {
        new AddTransactionAsyncTask(mTransactionDAO).execute(transaction);
    }

    // Update a transaction from the database
    public void update(Transaction transaction) {
        new UpdateTransactionAsyncTask(mTransactionDAO).execute(transaction);
    }

    // Delete a transaction from the database
    public void delete(Transaction transaction) {
        new DeleteTransactionAsyncTask(mTransactionDAO).execute(transaction);
    }

    // This method reads all transactions.
    public LiveData<List<Transaction>> getTransactions() {
        return mTransactionDAO.getTransactions();
    }

    // Async task method to insert new transaction.
    private static class AddTransactionAsyncTask extends AsyncTask<Transaction,Void,Void> {
        private TransactionDAO mTransactionDAO;

        private AddTransactionAsyncTask(TransactionDAO dao) {
            mTransactionDAO = dao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            mTransactionDAO.addTransaction(transactions[0]);
            return null;
        }
    }

    // Async task method to update a transaction
    private static class UpdateTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {
        private TransactionDAO mTransactionDAO;

        private UpdateTransactionAsyncTask(TransactionDAO dao) {
            mTransactionDAO = dao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            // This line will update transaction in dao.
            mTransactionDAO.updateTransaction(transactions[0]);
            return null;
        }
    }

    // Async task update method to delete a transaction
    private static class DeleteTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {
        private TransactionDAO mTransactionDAO;

        private DeleteTransactionAsyncTask(TransactionDAO dao) {
            mTransactionDAO = dao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            // This line will delete a transaction in dao.
            mTransactionDAO.deleteTransaction(transactions[0]);
            return null;
        }
    }


}
