package com.example.leedonggyu.mobile_app_develop.info;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
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
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leedonggyu.mobile_app_develop.CockWhatActivity;
import com.example.leedonggyu.mobile_app_develop.CockWhatDetailActivity;
import com.example.leedonggyu.mobile_app_develop.Manage.ManagementActivity;
import com.example.leedonggyu.mobile_app_develop.NaviExpandableListViewAdapter;
import com.example.leedonggyu.mobile_app_develop.R;

import java.util.ArrayList;

public class CockInfoListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private TextView title_txt;
    private ImageButton back_btn;

    private ListView star_list;
    private ListView cock_list;

    SharedPreferences star_pre;
    SharedPreferences.Editor editor;

    private ListViewAdapter starAdapter = new ListViewAdapter();
    private ListViewAdapter adapter = new ListViewAdapter();

    private ArrayList<CockItem> star_arr = new ArrayList<>();
    private int star_num;

    private ArrayList<String> parent_list;
    private ArrayList<ArrayList<String>> child_list;
    private ArrayList<String> child_Content;
    private ArrayList<String> child_Content2;

    private TextView name_text;
    private Switch alarm_switch;
    private ExpandableListView expandableListView;

    private Boolean toggle1;
    private Boolean toggle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cock_info_list);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        title_txt = (TextView)findViewById(R.id.title_text);
        title_txt.setText("칵테일 리스트 보기");

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
        NaviExpandableListViewAdapter navi_adapter = new NaviExpandableListViewAdapter(this,parent_list,child_list);
        expandableListView.setAdapter(navi_adapter);
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
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0;i<star_arr.size();i++){
                    Log.i("최종 저장",star_arr.get(i).getName());
                }
                finish();
            }
        });

        star_pre = getSharedPreferences("star",MODE_PRIVATE);
        editor =  star_pre.edit();

        star_list = (ListView)findViewById(R.id.star_list);
        Log.i("pre에서 복사해온 list 크기",String.valueOf(star_arr.size()));
        cock_list = (ListView)findViewById(R.id.cock_list);
        setListData();
        setStarData();

        cock_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CockItem tempItem = adapter.getItem(i);
                int index = -1;
                for(int j = 0; j<star_arr.size();j++){
                    if(star_arr.get(j).getName().equals(tempItem.getName())){
                        index = j;
                    }
                }
               if(index >= 0){
                   star_arr.remove(index);
                   starAdapter.removeItem(tempItem.getName());
                   star_list.setAdapter(starAdapter);
               }
               else{
                   star_arr.add(tempItem);
                   editor.putString(String.valueOf(star_arr.size()-1),tempItem.getName());
                   editor.commit();
                   starAdapter.addItem(null,tempItem.getCock(),tempItem.getName());
                   star_list.setAdapter(starAdapter);
               }
                Log.i("star_arr.size",String.valueOf(star_arr.size()));
               for(int j = 0;j<star_arr.size();j++){
                   Log.i("star_arr",String.valueOf(j)+" "+star_arr.get(j).getName());
               }
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

    private void setStarData(){
        star_num = star_pre.getInt("star_num",0);
        Log.i("쉐얼드프리퍼런스 읽어온 개수",String.valueOf(star_num));

        for(int i=0;i<star_num;i++){
            CockItem temp = new CockItem();
            temp.setName(star_pre.getString(String.valueOf(i),""));
            Log.i("읽어온 이름 : ",String.valueOf(i)+" "+temp.getName());
            temp.setABC(null);
            int numtemp = adapter.getByName(temp.getName());
            temp.setCock(adapter.getCock_list().get(numtemp).getCock());
            star_arr.add(temp);
            starAdapter.addItem(null, star_arr.get(i).getCock(), star_arr.get(i).getName());
        }
        star_list.setAdapter(starAdapter);
    }

    private void setListData(){

        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.a),ContextCompat.getDrawable(getApplicationContext(),R.drawable.alexander),"alexander");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.b),ContextCompat.getDrawable(getApplicationContext(),R.drawable.black_russian),"black russian");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.bloody_mary),"bloody mary");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.c),ContextCompat.getDrawable(getApplicationContext(),R.drawable.champane_cooler),"champane cooler");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.d),ContextCompat.getDrawable(getApplicationContext(),R.drawable.daiquiri),"daiquiri");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.g),ContextCompat.getDrawable(getApplicationContext(),R.drawable.gibson),"gibson");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.gimlet),"gimlet");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.gin_rickey),"gin rickey");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.gin_tonic),"gin tonic");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.grasshopper),"grasshopper");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.h),ContextCompat.getDrawable(getApplicationContext(),R.drawable.harvey_wallbanger),"harvey wallbanger");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.i),ContextCompat.getDrawable(getApplicationContext(),R.drawable.ice_pick),"ice pick");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.j),ContextCompat.getDrawable(getApplicationContext(),R.drawable.joeys_drink),"joeys drink");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.john_collins),"john collins");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.l),ContextCompat.getDrawable(getApplicationContext(),R.drawable.long_island_tea),"long island tea");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.m),ContextCompat.getDrawable(getApplicationContext(),R.drawable.magarita),"magarita");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.manhattan),"manhattan");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.martini),"martini");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.mint_julep),"mint julep");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.p),ContextCompat.getDrawable(getApplicationContext(),R.drawable.pina_colada),"pina colada");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.pousse_cafe),"pousse cafe");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.r),ContextCompat.getDrawable(getApplicationContext(),R.drawable.rob_roy),"rob roy");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.rum_and_cola),"rum and cola");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.s),ContextCompat.getDrawable(getApplicationContext(),R.drawable.screw_driver),"screw driver");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.singapore_sling),"singapore sling");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.stars_and_strips),"stars and strips");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.stinger),"stinger");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.t),ContextCompat.getDrawable(getApplicationContext(),R.drawable.tequila_sunshine),"tequila sunshine");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.tom_collins),"tom collins");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.v),ContextCompat.getDrawable(getApplicationContext(),R.drawable.vodka_gimlet),"vodka gimlet");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.w),ContextCompat.getDrawable(getApplicationContext(),R.drawable.whisky_sour),"whisky sour");
        adapter.addItem(null,ContextCompat.getDrawable(getApplicationContext(),R.drawable.white_russian),"white russian");
        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.z),ContextCompat.getDrawable(getApplicationContext(),R.drawable.zombie),"zombie");

        cock_list.setAdapter(adapter);
    }
    protected void onPause() {
        super.onPause();
        Log.i("star_num",String.valueOf(star_arr.size()));
        editor.putInt("star_num",star_arr.size());
        editor.commit();
    }
}
