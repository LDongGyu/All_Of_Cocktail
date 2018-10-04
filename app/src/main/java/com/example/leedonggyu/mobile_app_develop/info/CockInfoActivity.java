package com.example.leedonggyu.mobile_app_develop.info;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leedonggyu.mobile_app_develop.CockWhatActivity;
import com.example.leedonggyu.mobile_app_develop.CockWhatDetailActivity;
import com.example.leedonggyu.mobile_app_develop.Manage.ManagementActivity;
import com.example.leedonggyu.mobile_app_develop.NaviExpandableListViewAdapter;
import com.example.leedonggyu.mobile_app_develop.R;

import java.util.ArrayList;

public class CockInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private TextView title_txt;

    private Button site_btn;
    private Button list_btn;

    private ExpandableListView expandableListView;

    private ArrayList<String> parent_list;
    private ArrayList<ArrayList<String>> child_list;
    private ArrayList<String> child_Content;
    private ArrayList<String> child_Content2;

    private TextView name_text;
    private Switch alarm_switch;
    private Boolean toggle1;
    private Boolean toggle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cock_info);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        title_txt = (TextView)findViewById(R.id.title_text);
        title_txt.setText("관련 정보");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        expandableListView = (ExpandableListView)findViewById(R.id.navi_list);

        toggle1 = false;
        toggle2 = false;

        name_text = (TextView)findViewById(R.id.name_text);
        alarm_switch = (Switch)findViewById(R.id.alarm_switch);
        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getApplicationContext(),String.valueOf(b),Toast.LENGTH_SHORT).show();
            }
        });

        parent_list = new ArrayList<>();
        child_list = new ArrayList<ArrayList<String>>();
        child_Content = new ArrayList<>();
        child_Content2 = new ArrayList<>();

        parent_list.add("칵테일?");
        parent_list.add("국가별 칵테일");
        parent_list.add("칵테일 제조법");
        parent_list.add("맛집");
        parent_list.add("일정관리");
        parent_list.add("관련정보");

        child_Content.add("칵테일의 정의");
        child_Content.add("칵테일의 분류");
        child_Content.add("칵테일의 역사");
        child_Content.add("칵테일의 어원");
        child_Content.add("칵테일을 직업으로?");

        child_list.add(child_Content);
        child_list.add(new ArrayList<String>());
        child_list.add(new ArrayList<String>());
        child_list.add(new ArrayList<String>());
        child_list.add(new ArrayList<String>());


        child_Content2.add("칵테일 정보 사이트");
        child_Content2.add("칵테일 리스트 보기");
        child_list.add(child_Content2);
        NaviExpandableListViewAdapter adapter = new NaviExpandableListViewAdapter(this,parent_list,child_list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if(i == 0 && toggle1 == true){
                    Intent intent = new Intent(getApplicationContext(),CockWhatActivity.class);
                    toggle1 = false;
                    startActivity(intent);
                }
                else if(i==0 && toggle1 == false){
                    toggle1 = true;
                }
                if(i==4){
                    Intent intent = new Intent(getApplicationContext(),ManagementActivity.class);
                    startActivity(intent);
                }
                if(i==5&&toggle2==true){
                    Intent intent = new Intent(getApplicationContext(),CockInfoActivity.class);
                    toggle2 = false;
                    startActivity(intent);
                }
                else if(i==5 && toggle2 == false){
                    toggle2 = true;
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if(i == 0 && i1 == 0) {
                    Toast.makeText(getApplicationContext(),"칵테일의 정의", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockWhatDetailActivity.class);
                    intent.putExtra("category","칵테일의 정의");
                    startActivity(intent);
                }
                else if(i==0 && i1 == 1){
                    Toast.makeText(getApplicationContext(),"칵테일의 분류", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockWhatDetailActivity.class);
                    intent.putExtra("category","칵테일의 분류");
                    startActivity(intent);
                }
                else if(i==0 && i1 == 2){
                    Toast.makeText(getApplicationContext(),"칵테일의 역사", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockWhatDetailActivity.class);
                    intent.putExtra("category","칵테일의 역사");
                    startActivity(intent);
                }
                else if(i==0 && i1 == 3){
                    Toast.makeText(getApplicationContext(),"칵테일의 어원", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockWhatDetailActivity.class);
                    intent.putExtra("category","칵테일의 어원");
                    startActivity(intent);
                }
                else if(i==0 && i1 == 4){
                    Toast.makeText(getApplicationContext(),"칵테일을 직업으로?", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockWhatDetailActivity.class);
                    intent.putExtra("category","칵테일을 직업으로?");
                    startActivity(intent);
                }
                else if(i==5 && i1 == 0){
                    Toast.makeText(getApplicationContext(),"칵테일 정보 사이트", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockInfoSiteActivity.class);
                    startActivity(intent);
                }
                else if(i==5 && i1 == 1){
                    Toast.makeText(getApplicationContext(),"칵테일 리스트", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CockInfoListActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
            }
        });
        site_btn = (Button)findViewById(R.id.cock_site);
        site_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CockInfoSiteActivity.class);
                startActivity(intent);
            }
        });

        list_btn = (Button)findViewById(R.id.cock_list);
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CockInfoListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cock_what) {
            Toast.makeText(getApplicationContext(), "칵테일?", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cock_nation){
            Toast.makeText(getApplicationContext(), "국가별 칵테일", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cock_recipt){
            Toast.makeText(getApplicationContext(), "칵테일 제조법", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cock_place){
            Toast.makeText(getApplicationContext(), "맛집", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cock_manage){
            Toast.makeText(getApplicationContext(), "일정 관리", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cock_info){
            Toast.makeText(getApplicationContext(), "관련정보", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "기타", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
