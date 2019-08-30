package com.example.notipay_1;

public class MyItem {

    private String name;
    private int price;
    private int account;
    private int icon;

    public MyItem(String name, int price, int account, int icon) {
        this.name = name;
        this.price = price;
        this.account = account;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}