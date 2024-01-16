package com.example.androidexamproject;

import static com.example.androidexamproject.cartController.CartData.getCartInstance;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidexamproject.cartController.CartData;
import com.example.androidexamproject.controller.ProductModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product extends Fragment {
    private ProductModel product;
    public Product(ProductModel product) {
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // carousel setup
        ImageCarousel imageCarousel = view.findViewById(R.id.carousel);
        ArrayList<CarouselItem> images = new ArrayList<>();
        for (String url : product.images) {images.add(new CarouselItem(url));}
        imageCarousel.addData(images);

        // texts setup
        TextView title = view.findViewById(R.id.title);
        TextView brand = view.findViewById(R.id.brand);
        TextView ogPrice = view.findViewById(R.id.ogPrice);
        TextView discount = view.findViewById(R.id.discount);
        TextView discountedPrice = view.findViewById(R.id.dsPrice);
        TextView description = view.findViewById(R.id.description);
        title.setText(product.title);
        brand.setText(product.brand);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ogPrice.setText("Original price: " + String.format("%.2f", Double.parseDouble(decimalFormat.format(product.price))) + "$");
        discount.setText("Discount: " + product.discountPercentage + "%");
        discountedPrice.setText("Best price: " + Double.parseDouble(decimalFormat.format((product.price / 100 * (100 - product.discountPercentage)))) + "$");
        description.setText(product.description);

        if(product.discountPercentage == 0.0) {
            discount.setVisibility(View.GONE);
            ogPrice.setVisibility(View.GONE);
        }

        // buttons setup
        FloatingActionButton fab = view.findViewById(R.id.fab);
        FloatingActionButton fabR = view.findViewById(R.id.fabRemove);
        if(CartData.getCartInstance().checkIfProductInside(product)) {
            fab.setVisibility(View.GONE);
            fabR.setVisibility(View.VISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartData.getCartInstance().addProduct(product);
                fab.setVisibility(View.GONE);
                fabR.setVisibility(View.VISIBLE);
            }
        });
        fabR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartData.getCartInstance().removeProduct(product);
                fab.setVisibility(View.VISIBLE);
                fabR.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }
}