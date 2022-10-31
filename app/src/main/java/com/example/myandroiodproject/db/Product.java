package com.example.myandroiodproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_price")
    public String price;
}
