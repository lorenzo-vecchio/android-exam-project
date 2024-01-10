package com.example.androidexamproject.controller;

import java.util.ArrayList;

public class ProductModel {
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;

    public ProductModel(
            int id,
            String title,
            String description,
            int price,
            double discountPercentage,
            double rating,
            int stock,
            String brand,
            String category,
            String thumbnail,
            ArrayList<String> images
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }
}
