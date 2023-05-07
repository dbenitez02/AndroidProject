package com.example.budgettracker.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.budgettracker.DAO.TransactionDAO;
import com.example.budgettracker.DAO.UserDAO;
import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.model.User;

@Database(entities = {Transaction.class, User.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {

    public abstract TransactionDAO transactionDAO();
    public abstract UserDAO userDAO();
}
