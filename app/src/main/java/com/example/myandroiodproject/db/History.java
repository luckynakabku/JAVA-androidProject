package com.example.myandroiodproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "time")
    public String date;

    @ColumnInfo(name = "customer_name")
    public String customerUsername;

    @ColumnInfo(name = "product_name")
    public String productName;



}
