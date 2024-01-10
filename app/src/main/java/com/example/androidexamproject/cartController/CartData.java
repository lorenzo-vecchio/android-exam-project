package com.example.androidexamproject.cartController;

import com.example.androidexamproject.controller.ProductModel;

import java.util.ArrayList;

public class CartData {
    private static CartData instance;
    private ArrayList<ProductModel> cartItems;
    private CartData() {
        cartItems = new ArrayList<>();
    }
    public static synchronized CartData getInstance() {
        if (instance == null) {
            instance = new CartData();
        }
        return instance;
    }
    public ArrayList<ProductModel> getCartItems() {
        return cartItems;
    }
    public void addProduct(ProductModel product) {
        cartItems.add(product);
    }
    public void removeProduct(int id) {
        cartItems.remove(id);
    }
    public void removeProduct(ProductModel product) {
        cartItems.remove(product);
    }
}
