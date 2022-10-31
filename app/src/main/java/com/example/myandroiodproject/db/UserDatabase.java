package com.example.myandroiodproject.db;

import android.content.Context;
import android.service.autofill.UserData;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private  static UserDatabase INSTANCE;

    public static UserDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,"USER").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
