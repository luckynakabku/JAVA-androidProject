package com.example.myandroiodproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroiodproject.db.Database;
import com.example.myandroiodproject.db.Product;
import com.example.myandroiodproject.db.User;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {

    public static int selectedProduct;
    private Context context;
    private List<Product> productList;
    public productAdapter(Context context){
        this.context = context;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public productAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.market_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.MyViewHolder holder, int position) {
        holder.productName.setText(this.productList.get(position).name);
        holder.productPrice.setText("ราคา : "+ this.productList.get(position).price + " เหรียญ");
        String imagePath = this.productList.get(position).image.replace("R.drawable.","");
        int drawable = context.getResources().getIdentifier(imagePath,"drawable",context.getOpPackageName());
        holder.image.setImageResource(drawable);
        int productIndex = this.productList.get(position).id;


        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetailActivity.class);
                selectedProduct = productIndex;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView productName,productPrice;
        Button buyButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.marketProductName);
            productPrice = itemView.findViewById(R.id.marketProductPrice);
            buyButton = itemView.findViewById(R.id.gotoProductDetail);
        }
    }
}
