package com.example.leedonggyu.mobile_app_develop.info;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leedonggyu.mobile_app_develop.R;
import com.example.leedonggyu.mobile_app_develop.info.CockItem;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<CockItem> cock_list = new ArrayList<>();

    public ArrayList<CockItem> getCock_list() {
        return cock_list;
    }

    @Override
    public int getCount() {
        return cock_list.size();
    }

    @Override
    public CockItem getItem(int i) {
        return cock_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public int getByName(String name){
        for(int i =0;i <cock_list.size();i++){
            if(cock_list.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cock_listview,viewGroup,false);
        }

        ImageView abc_img = (ImageView)view.findViewById(R.id.abc_img);
        ImageView cock_img = (ImageView)view.findViewById(R.id.cock_img);
        TextView cock_name = (TextView)view.findViewById(R.id.cock_name);

        CockItem cockItem = this.getItem(i);

        abc_img.setImageDrawable(cockItem.getABC());
        cock_img.setImageDrawable(cockItem.getCock());
        cock_name.setText(cockItem.getName());

        return view;
    }

    public void addItem(Drawable abc, Drawable cock, String name){
        CockItem newItem = new CockItem();

        newItem.setABC(abc);
        newItem.setCock(cock);
        newItem.setName(name);

        cock_list.add(newItem);
    }

    public void removeItem(String name){
        int i = 0;

        while(i<cock_list.size()){
            if(cock_list.get(i).getName().equals(name)){
                cock_list.remove(i);
                break;
            }
            i++;
        }
    }
}
