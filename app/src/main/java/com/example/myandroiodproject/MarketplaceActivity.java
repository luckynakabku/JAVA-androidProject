package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.User;

import org.w3c.dom.Text;

import java.util.List;

public class MarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        TextView textView = findViewById(R.id.nameView);

        Intent intent = getIntent();
        User user = checkUsername(intent.getStringExtra("Username"));

        textView.setText(user.name);
    }


    public User checkUsername(String name){
        Database database = Database.getDbInstance(this.getApplicationContext());
        List<User> userList = database.userDao().checkUsernameExist(name);
        if (userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }
}