package com.example.leedonggyu.mobile_app_develop.Manage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leedonggyu.mobile_app_develop.CockWhatActivity;
import com.example.leedonggyu.mobile_app_develop.CockWhatDetailActivity;
import com.example.leedonggyu.mobile_app_develop.Manage.MonthAdapter;
import com.example.leedonggyu.mobile_app_develop.Manage.MonthItem;
import com.example.leedonggyu.mobile_app_develop.NaviExpandableListViewAdapter;
import com.example.leedonggyu.mobile_app_develop.R;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoActivity;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoListActivity;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoSiteActivity;

import java.util.ArrayList;

public class ManagementActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar; // 툴바
    private DrawerLayout drawerLayout; // 드로우 레이아웃
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private TextView title_txt;
    private ArrayList<String> parent_list;
    private ArrayList<ArrayList<String>> child_list;
    private ArrayList<String> child_Content;
    private ArrayList<String> child_Content2;
    private ExpandableListView expandableListView;

    GridView monthView;
    MonthAdapter monthViewAdapter;
    TextView monthText;
    int curYear;
    int curMonth;

    private Boolean toggle1;
    private Boolean toggle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        /* 툴바 */
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 툴바를 액션 바에 적용
        getSupportActionBar().setTitle("");
        title_txt = (TextView)findViewById(R.id.title_text);
        title_txt.setText("일정관리");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* 네비게이션 드로우 */
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        expandableListView = (ExpandableListView)findViewById(R.id.navi_list);

        toggle1 = false;
        toggle2 = false;

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

        /* 달력 */
        monthView = (GridView) findViewById(R.id.monthView);
        monthViewAdapter = new MonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();
                Intent intent = new Intent(getApplicationContext(),MemoActivity.class);
                intent.putExtra("year",curYear);
                intent.putExtra("month",curMonth+1);
                intent.putExtra("day",day);
                startActivity(intent);
                Log.d("MainActivity", "Selected : " + day);
            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
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
