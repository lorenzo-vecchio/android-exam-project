package com.example.androidexamproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.androidexamproject.adapters.ProductsAdapter;
import com.example.androidexamproject.controller.ProductModel;
import com.example.androidexamproject.controller.ProductsAsyncResponse;
import com.example.androidexamproject.controller.Store;

import java.util.ArrayList;


public class Products extends Fragment {

    public Products() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Store().getProducts(new ProductsAsyncResponse() {
            @Override
            public void onSuccess(ArrayList<ProductModel> products) {
                ProgressBar progressBar = view.findViewById(R.id.progressBar);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                ProductsAdapter productsAdapter = new ProductsAdapter(products);
                recyclerView.setAdapter(productsAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Errore", "ERROREEEEEEEEE");
            }
        });

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }
}