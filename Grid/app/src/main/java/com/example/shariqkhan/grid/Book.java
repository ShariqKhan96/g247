package com.example.shariqkhan.grid;

/**
 * Created by ShariqKhan on 8/29/2017.
 */

public class Book {

    private String author;
    private int image;

    public Book(int image, String author) {
        this.image = image;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImage() {
        return image;
    }
}
