package com.example.myandroiodproject.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private  static ProductDatabase INSTANCE;

    public static ProductDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class,"PRODUCT").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
