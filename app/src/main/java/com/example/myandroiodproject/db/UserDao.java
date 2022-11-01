package com.example.myandroiodproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAllUser();

    @Query("SELECT * FROM users WHERE user_username == :username ")
    List<User> checkUsernameExist(String username);

    @Query("SELECT * FROM users WHERE user_username == :username and user_password == :password")
    List<User> checkLogin(String username,String password);

    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);
}
