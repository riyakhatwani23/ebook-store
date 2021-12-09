package com.example.javafxproject;

public class BookModel {
    String bookName,author;
    int id,price;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookModel(String bookName, String author, int price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    public BookModel(String bookName, int price, int id) {
        this.bookName = bookName;
        this.price = price;
        this.id=id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BookModel(String bookName, String author, int id, int price) {
        this.bookName = bookName;
        this.author = author;
        this.id = id;
        this.price = price;
    }
}
