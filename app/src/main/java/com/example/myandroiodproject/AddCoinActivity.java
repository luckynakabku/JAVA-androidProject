package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.User;

import java.util.List;

public class AddCoinActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);

        User user = checkUsername(MarketplaceActivity.loginUsername);

        TextView coinText = findViewById(R.id.coinText);
        coinText.setText(user.balance.toString());

        TextView title = findViewById(R.id.title);
        title.setText("Add coin");

        Button add10coin = findViewById(R.id.buyCoin10);
        add10coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.balance += 10.0;
                updateUserToDatabase(user);
                coinText.setText(user.balance.toString());
            }
        });

        Button add100coin = findViewById(R.id.buyCoin100);
        add100coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.balance += 100.0;
                updateUserToDatabase(user);
                coinText.setText(user.balance.toString());
            }
        });

        Button add500coin = findViewById(R.id.buyCoin500);
        add500coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.balance += 500.0;
                updateUserToDatabase(user);
                coinText.setText(user.balance.toString());
            }
        });

        Button add1000coin = findViewById(R.id.buyCoin1000);
        add1000coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.balance += 1000.0;
                updateUserToDatabase(user);
                coinText.setText(user.balance.toString());
            }
        });

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        Button back = findViewById(R.id.addCoinBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(AddCoinActivity.this,MarketplaceActivity.class);
                newIntent.putExtra("Username",MarketplaceActivity.loginUsername);
                startActivity(newIntent);
            }
        });
    }
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
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

    public void updateUserToDatabase(User user){
        Database database = Database.getDbInstance(this.getApplicationContext());
        database.userDao().updateUser(user);
    }


    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.addCoin:
                return true;
            case R.id.history:
                Intent historyIntent = new Intent(AddCoinActivity.this,HistoryActivity.class);
                startActivity(historyIntent);
                return true;
            case R.id.logOut:
                Toast.makeText(this,"ออกจากระบบเรียบร้อย",Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(AddCoinActivity.this,MainActivity.class);
                startActivity(logOutIntent);
                return true;
            default:
                return false;
        }
    }
}