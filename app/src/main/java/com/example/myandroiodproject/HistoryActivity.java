package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.myandroiodproject.db.History;
import com.example.myandroiodproject.db.Product;
import com.example.myandroiodproject.db.User;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    public User user = checkUsername(MarketplaceActivity.loginUsername);
    private historyAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initRecyclerView();
        loadHistoryList();

        TextView title = findViewById(R.id.title);
        title.setText("Purchase history");

        Button back = findViewById(R.id.historyBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(HistoryActivity.this,MarketplaceActivity.class);
                newIntent.putExtra("Username",MarketplaceActivity.loginUsername);
                startActivity(newIntent);
            }
        });
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.historyRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        historyAdapter = new historyAdapter(this);
        recyclerView.setAdapter(historyAdapter);
    }
    private void loadHistoryList(){
        Database database = Database.getDbInstance(this.getApplicationContext());
        List<History> historyList = database.historyDao().getHistoryByUsername(user.userName);
        historyAdapter.setHistoryList(historyList);
    }

    public User checkUsername(String name){
        Database database = Database.getDbInstance(this);
        List<User> userList = database.userDao().checkUsernameExist(name);
        if (userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.addCoin:
                Intent addCoinIntent = new Intent(HistoryActivity.this,AddCoinActivity.class);
                startActivity(addCoinIntent);
                return true;
            case R.id.history:
                return true;
            case R.id.logOut:
                Toast.makeText(this,"ออกจากระบบเรียบร้อย",Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(logOutIntent);
                return true;
            default:
                return false;
        }
    }
}