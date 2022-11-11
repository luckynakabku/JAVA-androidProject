package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.User;

import org.w3c.dom.Text;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AddCoinActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 3000;

    User user;
    TextView head;
    TextView coin10;
    TextView price10coin;
    TextView coin100;
    TextView price100coin;
    TextView coin500;
    TextView price500coin;
    TextView Coin1000;
    TextView price1000coin;
    TextView waitingText;
    Button back;
    Button buy10;
    Button buy100;
    Button buy500;
    Button buy1000;
    TextView coinText;

    ImageView menu;

    boolean finish;
    //------------------------------------------- Smart Contact ------------------------------------------------------
    Web3j web3 = Web3j.build(new HttpService("https://goerli.infura.io/v3/27650c6f025041a6a28d0daae4498512"));
    Credentials credentials = Credentials.create("74e30019b667e39c13242c3193caa326b06015ff197780c6d54e2502ed990cc0");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();
    A a = A.load("0xa33DC29ad71Da03d98162bfaB3342921bE6D07E8", web3, credentials, contractGasProvider);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);

        user = checkUsername(MarketplaceActivity.loginUsername);
        head = findViewById(R.id.choiceText);
        coin10 = findViewById(R.id.coin10);
        price10coin = findViewById(R.id.price10coin);
        coin100 = findViewById(R.id.coin100);
        price100coin = findViewById(R.id.price100coin);
        coin500 = findViewById(R.id.coin500);
        price500coin = findViewById(R.id.price500coin);
        Coin1000 = findViewById(R.id.Coin1000);
        price1000coin = findViewById(R.id.price1000coin);
        waitingText = findViewById(R.id.waitingText);

        back = findViewById(R.id.addCoinBack);
        buy10 = findViewById(R.id.buyCoin10);
        buy100 = findViewById(R.id.buyCoin100);
        buy500 = findViewById(R.id.buyCoin500);
        buy1000 = findViewById(R.id.buyCoin1000);

        coinText = findViewById(R.id.coinText);

        TextView title = findViewById(R.id.title);
        title.setText("Add coin");

        finish = false;

        showUI();

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(AddCoinActivity.this,MarketplaceActivity.class);
                newIntent.putExtra("Username",MarketplaceActivity.loginUsername);
                startActivity(newIntent);
            }
        });

        buy10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideUI();
                a.store(new BigInteger("123")).flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionReceipt>() {
                    @Override
                    public void accept(TransactionReceipt transactionReceipt) throws Exception {
                        Log.i("vac", "accept: ");
                        user.balance += 10.0;
                        updateUserToDatabase(user);
                        finish = true;
                    }
                });
            }
        });
        buy100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideUI();
                a.store(new BigInteger("123")).flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionReceipt>() {
                    @Override
                    public void accept(TransactionReceipt transactionReceipt) throws Exception {
                        Log.i("vac", "accept: ");
                        user.balance += 100.0;
                        updateUserToDatabase(user);
                        finish = true;
                    }
                });
            }
        });

        buy500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideUI();
                a.store(new BigInteger("123")).flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionReceipt>() {
                    @Override
                    public void accept(TransactionReceipt transactionReceipt) throws Exception {
                        Log.i("vac", "accept: ");
                        user.balance += 500.0;
                        updateUserToDatabase(user);
                        finish = true;
                    }
                });
            }
        });

        buy1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideUI();
                a.store(new BigInteger("123")).flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionReceipt>() {
                    @Override
                    public void accept(TransactionReceipt transactionReceipt) throws Exception {
                        Log.i("vac", "accept: ");
                        user.balance += 1000.0;
                        updateUserToDatabase(user);
                        finish = true;
                    }
                });
            }
        });

    }
    public void showUI(){
        head.setVisibility(View.VISIBLE);
        coin10.setVisibility(View.VISIBLE);
        price10coin.setVisibility(View.VISIBLE);
        coin100.setVisibility(View.VISIBLE);
        price100coin.setVisibility(View.VISIBLE);
        coin500.setVisibility(View.VISIBLE);
        price500coin.setVisibility(View.VISIBLE);
        Coin1000.setVisibility(View.VISIBLE);
        price1000coin.setVisibility(View.VISIBLE);
        buy10.setVisibility(View.VISIBLE);
        buy100.setVisibility(View.VISIBLE);
        buy500.setVisibility(View.VISIBLE);
        buy1000.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        waitingText.setVisibility(View.INVISIBLE);
        coinText.setText(user.balance.toString());
    }

    public void hideUI(){
        head.setVisibility(View.INVISIBLE);
        coin10.setVisibility(View.INVISIBLE);
        price10coin.setVisibility(View.INVISIBLE);
        coin100.setVisibility(View.INVISIBLE);
        price100coin.setVisibility(View.INVISIBLE);
        coin500.setVisibility(View.INVISIBLE);
        price500coin.setVisibility(View.INVISIBLE);
        Coin1000.setVisibility(View.INVISIBLE);
        price1000coin.setVisibility(View.INVISIBLE);
        buy10.setVisibility(View.INVISIBLE);
        buy100.setVisibility(View.INVISIBLE);
        buy500.setVisibility(View.INVISIBLE);
        buy1000.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        waitingText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                if(finish){
                    Toast.makeText(AddCoinActivity.this,"ซื้อเหรียญสำเร็จ",Toast.LENGTH_SHORT).show();
                    showUI();
                    finish = false;
                }
            }
            }, delay);
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
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