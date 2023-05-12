package com.example.budgettracker.viewmodel;

import android.app.Application;
import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.repo.TransactionRepository;
import java.util.List;

public class TransactionListViewModel {

    private TransactionRepository transactionRepo;

    public TransactionListViewModel(Application application) {
        transactionRepo = TransactionRepository.getInstance(application.getApplicationContext());
    }

    public List<Transaction> getTransactions() {

        return transactionRepo.getTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepo.addTransaction(transaction);
    }
}
