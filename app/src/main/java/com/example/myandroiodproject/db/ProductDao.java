package com.example.myandroiodproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Insert
    void insertProduct(Product... products);

    @Delete
    void delete(Product product);
}
