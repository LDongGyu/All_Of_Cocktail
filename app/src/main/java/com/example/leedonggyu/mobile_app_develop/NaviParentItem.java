package com.example.leedonggyu.mobile_app_develop;

import android.graphics.drawable.Drawable;

public class NaviParentItem {

    private String category;
    private Drawable downImg;

    public NaviParentItem(String name){
        category = name;
    }

    public Drawable getDownImg() {
        return downImg;
    }
    public void setDownImg(Drawable img){
        downImg = img;
    }

    public String getCategory() {
        return category;
    }

    public  void setCategory(String txt){
        category = txt;
    }
}
