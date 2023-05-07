package com.example.budgettracker.ViewModel;

import android.app.Application;
import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.repo.MainRepository;
import java.util.List;

public class TransactionViewModel {
    private MainRepository mainRepository;

    public TransactionViewModel(Application application) {
        mainRepository = MainRepository.getInstance(application.getApplicationContext());
    }

    public List<Transaction> getTransactions(long userID) {
        return mainRepository.getTransactions(userID);
    }
}
