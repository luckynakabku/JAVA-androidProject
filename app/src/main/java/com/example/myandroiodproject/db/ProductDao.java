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

    @Query("SELECT * FROM products WHERE id == :id ")
    List<Product> getProductByID(int id);

    @Query("SELECT * FROM products WHERE product_name == :name ")
    List<Product> getProductByName(String name);

    @Insert
    void insertProduct(Product... products);

    @Delete
    void delete(Product product);
}
