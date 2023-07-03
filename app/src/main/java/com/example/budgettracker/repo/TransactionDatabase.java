package com.example.budgettracker.repo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgettracker.model.Transaction;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {

    private static TransactionDatabase instance;
    public abstract TransactionDAO transactionDAO();

    // Getting the instance for the database
    public static synchronized TransactionDatabase getInstance(Context context) {
        // Check if there is an instance
        if(instance == null) {

            //  Creating a database builder and passing database class and name
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                TransactionDatabase.class,
                            "transaction_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }

        return instance;
    }

    // Creating a callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // Async task class to perform task in the background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(TransactionDatabase instance) {
            TransactionDAO transactionDAO = instance.transactionDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
