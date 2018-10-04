package com.example.leedonggyu.mobile_app_develop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NaviExpandableListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> parent_list = null;
    private ArrayList<ArrayList<String>> child_list = null;
    private LayoutInflater inflater = null;
    private ViewHolder holder = null;

    public NaviExpandableListViewAdapter(Context context, ArrayList<String> DataList, ArrayList<ArrayList<String>> childList){
        super();
        this.inflater = LayoutInflater.from(context);
        this.parent_list = DataList;
        this.child_list = childList;
    }

    @Override
    public int getGroupCount() {
        return parent_list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child_list.get(i).size();
    }

    @Override
    public String getGroup(int i) {
        return parent_list.get(i);
    }

    @Override
    public String getChild(int i, int i1) {
        return child_list.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View v = view;

        if(v == null){
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.navi_parent_listview,viewGroup,false);
            holder.parent_txt = (TextView)v.findViewById(R.id.parent_txt);
//            holder.img_btn = (ImageButton)v.findViewById(R.id.parant_down_img);
            v.setTag(holder);
        }
        else{
            holder = (ViewHolder)v.getTag();
        }

        if(b){
            holder.setImg_btn(R.drawable.up);
        }
        else{
            holder.setImg_btn(R.drawable.down);
        }

        holder.parent_txt.setText(getGroup(i));

        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View v = view;

        if(v==null){
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.navi_child_listview,null);
            holder.child_txt = (TextView)v.findViewById(R.id.child_txt);
            v.setTag(holder);
        }
        else{
            holder = (ViewHolder)v.getTag();
        }
        holder.child_txt.setText(getChild(i,i1));
        Log.i("child_txt ",String.valueOf(holder.child_txt.getText()));

        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}

class ViewHolder{
    public TextView parent_txt;
    public ImageButton img_btn;
    public TextView child_txt;

    public void setImg_btn(int img){
 //       img_btn.setImageResource(img);
    }
    public TextView getChild_txt(){
        return child_txt;
    }
}