package com.example.budgettracker.repo;

import android.content.Context;
import com.example.budgettracker.model.User;
import com.example.budgettracker.model.Transaction;
import java.util.*;

public class MainRepository {
    private static MainRepository mainRepo;
    private final List<User> userList;
    private final HashMap<Long, List<Transaction>> transactionMap;


    public static MainRepository getInstance(Context context) {
        if (mainRepo == null) {
            mainRepo = new MainRepository(context);
        }

        return mainRepo;
    }

    private MainRepository(Context context) {
        transactionMap = new HashMap<>();
        userList = new ArrayList<>();

        addStarterData();
    }

    private void addStarterData() {
        // Lets add a new user
        User user = new User(1);
        user.setId(1);
        user.setBudget(500);
        user.setEmail("billy.bronco@email.com");
        user.setPassword("test");
        user.setName("Billy Bronco");
        addUser(user);

        // Lets give that user a couple of transactions
        Transaction transaction = new Transaction();
        transaction.setID(1);
        transaction.setAmountSpent(20);
        transaction.setDescription("Dinner");
        addTransaction(transaction);

        transaction = new Transaction();
        transaction.setID(2);
        transaction.setAmountSpent(50);
        transaction.setDescription("Internet bill");
        addTransaction(transaction);

        transaction = new Transaction();
        transaction.setID(3);
        transaction.setAmountSpent(60);
        transaction.setDescription("Video games");
        addTransaction(transaction);

    }

    public void addUser(User user) {
        userList.add(user);
        List<Transaction> transactionList = new ArrayList<>();
        transactionMap.put(user.getUserId(), transactionList);
    }


    public void addTransaction(Transaction transaction) {
        List<Transaction> transactionList = transactionMap.get(transaction.getID());
        if(transactionList != null) {
            transactionList.add(transaction);
        }
    }

    public List<Transaction> getTransactions(long userID) {
        return transactionMap.get(userID);
    }
}
