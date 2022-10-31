package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView alert = findViewById(R.id.registerAlertText);
        alert.setVisibility(View.INVISIBLE);
    }
    public void backToLogin(View view){
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }
}