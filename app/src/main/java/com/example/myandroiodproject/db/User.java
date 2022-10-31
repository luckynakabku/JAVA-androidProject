package com.example.myandroiodproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_username")
    public String userName;

    @ColumnInfo(name = "user_password")
    public String password;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "user_balance")
    public Double balance;
}
