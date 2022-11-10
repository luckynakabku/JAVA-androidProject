package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        EditText usernameEditText = findViewById(R.id.usernameLogin);
        EditText passwordEditText = findViewById(R.id.passwordLogin);
        Button loginButton = findViewById(R.id.loginSubmitButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    alert.setText("กรูณากรอกข้อมูลไม่ถูกต้อง");
                    alert.setVisibility(View.VISIBLE);
                }else{
                    if(checkLogin(username,password)!=null){
                        Intent intent = new Intent(MainActivity.this,MarketplaceActivity.class);
                        intent.putExtra("Username",username);
                        startActivity(intent);
                    }else{
                        alert.setText("Username หรือ Password ผิด!");
                        alert.setVisibility(View.VISIBLE);
                        passwordEditText.setText("");
                    }
                }
            }
        });
    }

    public void goToRegister(View view){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    public User checkLogin(String username,String password){
        Database database = Database.getDbInstance(this.getApplicationContext());
        List<User> userList = database.userDao().checkLogin(username,password);
        if(userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }

}