package com.example.myandroiodproject.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private  static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"PRODUCT").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
