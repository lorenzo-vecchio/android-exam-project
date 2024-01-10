package com.example.androidexamproject.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidexamproject.controller.AppController;
import com.example.androidexamproject.controller.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Store {
    String urlProducts = "https://dummyjson.com/products/";

    public void getProducts(final ProductsAsyncResponse callBack) {
        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                urlProducts,
                null,
                response -> {
                    try {
                        JSONArray productsJsonArray = response.getJSONArray("products");
                        for (int i = 0; i < productsJsonArray.length(); i++) {
                            JSONObject productJson = productsJsonArray.getJSONObject(i);
                            int id = productJson.getInt("id");
                            String title = productJson.getString("title");
                            String description = productJson.getString("description");
                            int price = productJson.getInt("price");
                            double discountPercentage = productJson.getDouble("discountPercentage");
                            double rating = productJson.getDouble("rating");
                            int stock = productJson.getInt("stock");
                            String brand = productJson.getString("brand");
                            String category = productJson.getString("category");
                            String thumbnail = productJson.getString("thumbnail");
                            ArrayList<String> images = new ArrayList<>();
                            JSONArray imagesJson = productJson.getJSONArray("images");
                            for (int a = 0; a < imagesJson.length(); a++) {
                                String image = imagesJson.getString(a);
                                images.add(image);
                            }
                            ProductModel product = new ProductModel(
                                    id,
                                    title,
                                    description,
                                    price,
                                    discountPercentage,
                                    rating,
                                    stock,
                                    brand,
                                    category,
                                    thumbnail,
                                    images
                            );
                            products.add(product);
                        }
                        callBack.onSuccess(products);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callBack.onFailure(e);
                    }
                },
                error -> {
                    Log.d("Main", "Error");
                }
        );

        AppController.getInstance().addToRequestQueue(request);
    }
}
