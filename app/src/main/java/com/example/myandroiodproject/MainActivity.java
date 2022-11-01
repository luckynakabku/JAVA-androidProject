package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.User;
import com.example.myandroiodproject.db.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView alert = findViewById(R.id.registerAlertText);
        alert.setVisibility(View.INVISIBLE);

        List<User> userList = getUserListFromDatabase();
    }

    public void goToRegister(View view){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    public List<User> getUserListFromDatabase(){
        Database database = Database.getDbInstance(this.getApplicationContext());

        UserDao userDao = database.userDao();
        List<User> userList = userDao.getAllUser();

        return userList;
    }
}