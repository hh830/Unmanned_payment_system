package org.techtown.home2;

public class Horizontal_data {
    private String name;
    private int resId;
    private String price;

    public Horizontal_data(int res, String name, String price){
        this.resId = res;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getResId() {
        return resId;
    }


}

