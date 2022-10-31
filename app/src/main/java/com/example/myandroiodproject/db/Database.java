package com.example.myandroiodproject.db;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Product.class , User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract ProductDao productDao();

    public abstract UserDao userDao();

    private  static Database INSTANCE;

    public static Database getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class,"DATA").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
