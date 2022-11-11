package com.example.myandroiodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //Intent intent = getIntent();
        Product product = checkProduct(productAdapter.selectedProduct);

        User user = checkUsername(MarketplaceActivity.loginUsername);


        ImageView image = findViewById(R.id.productPic);
        TextView productName = findViewById(R.id.productName);
        TextView productDetail = findViewById(R.id.productDetail);
        TextView productPrice = findViewById(R.id.productPrice);
        Button buyButton = findViewById(R.id.buyButton);
        TextView alert = findViewById(R.id.alertText);


        TextView title = findViewById(R.id.title);
        title.setText("Product detail");
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        if(user.balance < product.price){
            buyButton.setVisibility(View.INVISIBLE);
        }else{
            alert.setVisibility(View.INVISIBLE);
        }
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.balance -= product.price;
                updateUserToDatabase(user);

                Intent newIntent = new Intent(ProductDetailActivity.this,MarketplaceActivity.class);
                newIntent.putExtra("Username",MarketplaceActivity.loginUsername);
                startActivity(newIntent);
                Toast.makeText(getApplicationContext(),"ซื้อสำเร็จ",Toast.LENGTH_SHORT).show();
                addHistory();
            }
        });

        String imagePath = product.image.replace("R.drawable.","");
        int drawable = getResources().getIdentifier(imagePath,"drawable",getOpPackageName());
        image.setImageResource(drawable);

        productName.setText(product.name);
        productDetail.setText(product.detail);
        productPrice.setText("ราคา : " + product.price + " เหรียญ");

        Button backButton = findViewById(R.id.productDetailBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(ProductDetailActivity.this,MarketplaceActivity.class);
                newIntent.putExtra("Username",MarketplaceActivity.loginUsername);
                startActivity(newIntent);
            }
        });
    }
    public Product checkProduct(int id){
        Database database = Database.getDbInstance(getApplicationContext());
        List<Product> productList = database.productDao().getProductByID(id);
        if (productList.isEmpty()){
            return null;
        }else{
            return productList.get(0);
        }
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

    public void addHistory(){
        Database database = Database.getDbInstance(this.getApplicationContext());
        Product product = checkProduct(productAdapter.selectedProduct);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        String dateTime = localDateTime.format(dateTimeFormatter);
        History history = new History();
        history.customerUsername = MarketplaceActivity.loginUsername;
        history.productName = product.name;
        history.date = dateTime;
        database.historyDao().insertHistory(history);
    }
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.addCoin:
                Intent addCoinIntent = new Intent(ProductDetailActivity.this,AddCoinActivity.class);
                startActivity(addCoinIntent);
                return true;
            case R.id.history:
                Toast.makeText(this,"History",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logOut:
                Toast.makeText(this,"ออกจากระบบเรียบร้อย",Toast.LENGTH_SHORT).show();
                Intent logOutIntent = new Intent(ProductDetailActivity.this,MainActivity.class);
                startActivity(logOutIntent);
                return true;
            default:
                return false;
        }
    }
    public void updateUserToDatabase(User user){
        Database database = Database.getDbInstance(this.getApplicationContext());
        database.userDao().updateUser(user);
    }
}