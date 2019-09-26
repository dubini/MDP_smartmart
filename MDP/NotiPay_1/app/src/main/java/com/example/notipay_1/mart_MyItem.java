package com.example.notipay_1;
import android.graphics.drawable.Drawable;

public class mart_MyItem {

    private String name;
    private int icon,price, account;

    public mart_MyItem(String name, int Price, int Account, int icon) {
        this.name = name;
        this.price = Price;
        this.account = Account;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
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
}