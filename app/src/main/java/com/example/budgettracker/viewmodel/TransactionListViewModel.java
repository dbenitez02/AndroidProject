package com.example.budgettracker.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.repo.TransactionRepository;
import java.util.List;

public class TransactionListViewModel extends AndroidViewModel {

    private final TransactionRepository transactionRepo;

    public TransactionListViewModel(Application application) {
        super(application);
        transactionRepo = TransactionRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Transaction>> getTransactions() {

        return transactionRepo.getTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepo.addTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepo.deleteTransaction(transaction);
    }
}
