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
import com.example.myandroiodproject.db.Product;
import com.example.myandroiodproject.db.User;

import org.w3c.dom.Text;

import java.util.List;

public class MarketplaceActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static String loginUsername;
    private productAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        Intent intent = getIntent();

        User user = checkUsername(intent.getStringExtra("Username"));
        loginUsername = intent.getStringExtra("Username");

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        TextView textView = findViewById(R.id.textName);
        textView.setText("ยินดีต้อนรับ คุณ "+user.name);

        TextView balance = findViewById(R.id.balance);
        balance.setText("เหรียญของคุณ : "+ user.balance);

        Button addProduct = findViewById(R.id.addNewProductButton);
        addProduct.setVisibility(View.INVISIBLE);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.name = "เหรียญหลวงพ่อรวย";
                product.detail = "หลวงพ่อรวย เหรียญหลวงพ่อรวย เลื่อนสมณศักดิ์ อยุธยา";
                product.price = 100.0;
                product.image = "amulet_5";
                Database database = Database.getDbInstance(getApplicationContext());
                database.productDao().insertProduct(product);
            }
        });

        initRecyclerView();
        loadProductList();
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

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        productAdapter = new productAdapter(this);
        recyclerView.setAdapter(productAdapter);
    }
    private void loadProductList(){
        Database database = Database.getDbInstance(this.getApplicationContext());
        List<Product> productList = database.productDao().getAllProducts();
        productAdapter.setProductList(productList);
    }

    public void showPopup(View v){
        PopupMenu popup =new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.addCoin:
                Intent addCoinIntent = new Intent(MarketplaceActivity.this,AddCoinActivity.class);
                startActivity(addCoinIntent);
                return true;
            case R.id.history:
                Intent historyIntent = new Intent(MarketplaceActivity.this,HistoryActivity.class);
                startActivity(historyIntent);
                return true;
            case R.id.logOut:
                Toast.makeText(this,"ออกจากระบบเรียบร้อย",Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(MarketplaceActivity.this,MainActivity.class);
                startActivity(logOutIntent);
                return true;
            default:
                return false;
        }
    }
}