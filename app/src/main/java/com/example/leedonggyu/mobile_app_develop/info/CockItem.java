package com.example.leedonggyu.mobile_app_develop.info;

import android.graphics.drawable.Drawable;

public class CockItem {
    private Drawable abc_img;
    private Drawable cock_img;
    private String cock_name;

    public void setABC(Drawable abc){
        abc_img = abc;
    }

    public Drawable getABC(){
        return abc_img;
    }

    public void setCock(Drawable cock){
        cock_img = cock;
    }

    public Drawable getCock(){
        return cock_img;
    }

    public void setName(String name){
        cock_name = name;
    }

    public String getName(){
        return cock_name;
    }
}
