package com.example.myandroiodproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM history")
    List<History> getAllHistory();

    @Query("SELECT * FROM history WHERE customer_name == :username ")
    List<History> getHistoryByUsername(String username);

    @Insert
    void insertHistory(History... histories);

    @Delete
    void delete(History history);
}
