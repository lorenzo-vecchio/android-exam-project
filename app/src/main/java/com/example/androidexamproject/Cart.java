package com.example.androidexamproject;

import static com.example.androidexamproject.cartController.CartData.getCartInstance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidexamproject.adapters.ProductsAdapter;
import com.example.androidexamproject.adapters.SelectListener;
import com.example.androidexamproject.cartController.CartData;
import com.example.androidexamproject.controller.ProductModel;

public class Cart extends Fragment {

    public Cart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ProductsAdapter productsAdapter = new ProductsAdapter(getCartInstance().getCartItems(), new SelectListener() {
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

        TextView total = view.findViewById(R.id.ogPrice);
        TextView dsTotal = view.findViewById(R.id.discountedPrice);
        double tot = CartData.getCartInstance().getTotal();
        double dsTot = CartData.getCartInstance().getDiscountedTotal();
        total.setText("Totale: " + tot + "$");
        dsTotal.setText("Totale scontato: " + dsTot + "$");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}