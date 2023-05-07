package com.example.budgettracker.ViewModel;

import android.app.Application;
import com.example.budgettracker.model.User;
import com.example.budgettracker.repo.MainRepository;
import java.util.List;

public class UserViewModel {
    private MainRepository mainRepository;

    public UserViewModel(Application application) {
        mainRepository = MainRepository.getInstance(application.getApplicationContext());

    }
}
