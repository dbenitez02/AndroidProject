package com.example.budgettracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgettracker.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE userID = :id")
    User getUser(long id);

    @Query("SELECT * FROM users ORDER BY userID COLLATE NOCASE")
    List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
