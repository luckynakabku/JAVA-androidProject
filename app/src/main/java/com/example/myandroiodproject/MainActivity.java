package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView alert = findViewById(R.id.registerAlertText);
        alert.setVisibility(View.INVISIBLE);
    }

    public void goToRegister(View view){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }
}