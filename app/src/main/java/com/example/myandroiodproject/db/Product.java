package com.example.myandroiodproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_detail")
    public String detail;

    @ColumnInfo(name = "product_price")
    public Double price;

    @ColumnInfo(name = "product_image")
    public String image;

}
