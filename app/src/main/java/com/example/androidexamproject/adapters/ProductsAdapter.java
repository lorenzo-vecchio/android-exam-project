package com.example.androidexamproject.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexamproject.controller.ProductModel;
import com.example.androidexamproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private final ArrayList<ProductModel> products;
    private SelectListener listener;

    public ProductsAdapter(ArrayList<ProductModel> products, SelectListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout productCard;
        private final TextView title;
        private final TextView brand;
        private final TextView description;
        private final ImageView thumbnail;
        private final ProgressBar progressBar;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productCard = itemView.findViewById(R.id.productCard);
            title = itemView.findViewById(R.id.title);
            brand = itemView.findViewById(R.id.brand);
            description = itemView.findViewById(R.id.description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(ProductModel product, SelectListener listener) {
            title.setText(product.title);
            brand.setText(product.brand);
            description.setText(product.description);
            Picasso.get()
                    .load(product.thumbnail)
                    .into(thumbnail, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                            thumbnail.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onError(Exception e) {
                            Log.d("Errore", e.getMessage());
                            e.printStackTrace();
                        }
                    });
            productCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(product);
                }
            });
        }
    }
}
