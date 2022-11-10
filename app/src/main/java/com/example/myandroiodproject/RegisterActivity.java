package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.User;
import com.example.myandroiodproject.db.UserDao;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView alert = findViewById(R.id.registerAlertText);
        alert.setVisibility(View.INVISIBLE);

        EditText usernameEditText = findViewById(R.id.usernameRegister);
        EditText nameEditText = findViewById(R.id.nameRegister);
        EditText passwordEditText = findViewById(R.id.passwordRegister);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordRegister);
        Button registerBtn = findViewById(R.id.registerSubmitButton);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String name = nameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if(username.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    alert.setText("กรุณาใส่ข้อมูลให้ครบถ้วน");
                    alert.setTextColor(Color.parseColor("#e51c23"));
                    alert.setVisibility(View.VISIBLE);
                }else{
                    if(!password.equals(confirmPassword)){
                        alert.setText("ยืนยันรหัสผ่านไม่ถูกต้อง");
                        alert.setTextColor(Color.parseColor("#e51c23"));
                        alert.setVisibility(View.VISIBLE);
                    }else {
                        if(checkUsername(username)){
                            User user = new User();
                            user.userName = username;
                            user.name = name;
                            user.password = password;
                            user.balance = 0.0;
                            addUserToDatabase(user);
                            alert.setText("สมัครสมาชิกสำเร็จ");
                            alert.setTextColor(Color.parseColor("#00FF66"));
                            alert.setVisibility(View.VISIBLE);
                            usernameEditText.setText("");
                            nameEditText.setText("");
                            passwordEditText.setText("");
                            confirmPasswordEditText.setText("");

                        }else{
                            alert.setText("Username นี้มีคนใช้แล้ว");
                            alert.setTextColor(Color.parseColor("#e51c23"));
                            alert.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

    }
    public void backToLogin(View view){
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }

    public void addUserToDatabase(User user){
        Database database = Database.getDbInstance(this.getApplicationContext());
        database.userDao().insertUser(user);
    }

    public boolean checkUsername(String name){
        Database database = Database.getDbInstance(this.getApplicationContext());
        List<User> userList = database.userDao().checkUsernameExist(name);
        if (userList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

}