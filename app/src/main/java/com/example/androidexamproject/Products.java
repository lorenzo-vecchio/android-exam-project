package com.example.androidexamproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.androidexamproject.adapters.ProductsAdapter;
import com.example.androidexamproject.adapters.SelectListener;
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
                TextView loadingText = view.findViewById(R.id.loadingText);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ProductsAdapter productsAdapter = new ProductsAdapter(products, new SelectListener() {
                    @Override
                    public void onItemClicked(ProductModel product) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.flFragment, new Product(product), null)
                                .addToBackStack(null)
                                .setReorderingAllowed(true)
                                .commit();
                    }
                });
                recyclerView.setAdapter(productsAdapter);
                progressBar.setVisibility(View.GONE);
                loadingText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Errore", e.getMessage());
                e.printStackTrace();
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