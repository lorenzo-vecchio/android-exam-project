package com.example.androidexamproject.controller;

import com.example.androidexamproject.controller.ProductModel;

import java.util.ArrayList;

public interface ProductsAsyncResponse {
    void onSuccess(ArrayList<ProductModel> products);
    void onFailure(Exception e);
}
