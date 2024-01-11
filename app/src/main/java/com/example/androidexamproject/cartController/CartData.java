package com.example.androidexamproject.cartController;

import com.example.androidexamproject.controller.ProductModel;

import java.util.ArrayList;

public class CartData {
    private static CartData instance;
    private ArrayList<ProductModel> cartItems;
    private CartData() {
        cartItems = new ArrayList<>();
    }
    public static synchronized CartData getCartInstance() {
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
    public void removeProduct(ProductModel product) {
        for (ProductModel item : cartItems) {
            if(item.id == product.id) {
                cartItems.remove(item);
            }
        }
    }
    public boolean checkIfProductInside(ProductModel product) {
        for (ProductModel item : cartItems) {
            if(item.id == product.id) {
                return true;
            }
        }
        return false;
    }
    public double getTotal() {
        double total = 0;
        for (ProductModel item : cartItems) {
            total += item.price;
        }
        return total;
    }
    public double getDiscountedTotal() {
        double total = 0;
        for(ProductModel item : cartItems) {
            double discountedPrice = (double) item.price / 100 * (100 - item.discountPercentage);
            total += discountedPrice;
        }
        return total;
    }
}
