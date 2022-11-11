package com.example.myandroiodproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.History;
import com.example.myandroiodproject.db.Product;
import com.example.myandroiodproject.db.User;

import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.MyViewHolder> {

    public User user = checkUsername(MarketplaceActivity.loginUsername);
    private Context context;
    private List<History> historyList;
    public historyAdapter(Context context){
        this.context = context;
    }
    public void setHistoryList(List<History> historyList){
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    public User checkUsername(String name){
        Database database = Database.getDbInstance(context);
        List<User> userList = database.userDao().checkUsernameExist(name);
        if (userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }

    @NonNull
    @Override
    public historyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.history_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyAdapter.MyViewHolder holder, int position) {

        Product product = checkProduct(this.historyList.get(position).productName);
        holder.time.setText(this.historyList.get(position).date);
        holder.productName.setText(this.historyList.get(position).productName);
        holder.price.setText(product.price.toString());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView productName;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            productName = itemView.findViewById(R.id.historyProductName);
            price = itemView.findViewById(R.id.price);
        }
    }
    public Product checkProduct(String name){
        Database database = Database.getDbInstance(context);
        List<Product> productList = database.productDao().getProductByName(name);
        if (productList.isEmpty()){
            return null;
        }else{
            return productList.get(0);
        }
    }

}
