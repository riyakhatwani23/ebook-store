package com.example.javafxproject;

public class viewOrderModel {
    String name,address,phone, bname;
    int bid,price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public viewOrderModel(String name, String address, String phone, String bname, int bid, int price) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.bname = bname;
        this.bid = bid;
        this.price = price;
    }
}
