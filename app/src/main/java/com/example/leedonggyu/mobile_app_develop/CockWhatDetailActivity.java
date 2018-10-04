package com.example.leedonggyu.mobile_app_develop;

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
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leedonggyu.mobile_app_develop.Manage.ManagementActivity;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoActivity;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoListActivity;
import com.example.leedonggyu.mobile_app_develop.info.CockInfoSiteActivity;

import java.util.ArrayList;

public class CockWhatDetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private TextView title_txt;
    private ImageButton back_btn;

    private Button title_btn;
    private TextView context_txt;

    private Intent intent;

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
        setContentView(R.layout.activity_cock_what_detail);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        intent = getIntent();
        String category = intent.getStringExtra("category");

        title_txt = (TextView)findViewById(R.id.title_text);
        title_txt.setText(category);
        back_btn = (ImageButton)findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        title_btn = (Button)findViewById(R.id.cock_title);
        title_btn.setText(category);

        context_txt = (TextView)findViewById(R.id.cock_text);
        setContextView(category);

    }

    public void setContextView(String category){
        if(category.equals("칵테일의 정의")){
            context_txt.setText("칵테일(Cocktail)이란, 일반적으로 알코올 음료에 또 다른 술을 섞거나 과즙류나 탄산음료 또는 향료 등의 부재료를 혼합하여 맛, 향기, 색채의 조화를 살린 음료를 말한다. 우리가 마시고 있는 음료는 크게 알코올 음료와 비알코올 음료로 나눌 수 있는데, 마시는 방법에는 여러 가지가 있지만 크게 두 가지로 나눌 수 있다. 하나는 음료 그 자체만을 마시는 스트레이트 드링크(Straight Drink)고, 다른 하나는 여러 가지를 섞어서 마시는 믹스드 드링크(Mixed Drink)다. 칵테일은 믹스드 드링크라고 보면 된다.\n" +
                    "\n" +
                    "즉, 칵테일이란 술과 술을 혼합하고 청량음료나 과일즙, 각종 향을 배합하여 여러 가지 과일로 장식을 해 맛과 향을 음미하며 마실 수 있도록 만든 것을 말한다. 물론 알코올을 전혀 함유하고 있지 않은 청량음료나 과일즙 또는 주스와 주스를 섞어 마시는 것도 칵테일이라 할 수 있다. 칵테일의 종류는 무수히 많으며, 만드는 방법이나 재료를 섞는 비율이 같아도 베이스나 부재료를 바꾸면 또 다른 맛의 칵테일이 된다. 사용된 베이스나 만드는 방법에 따라 분류가 되기도 하고, 부재료로 쓰이는 과일이나 향미에 따라 이름이 붙기도 한다.\n" +
                    "[네이버 지식백과] 칵테일의 정의 (분위기에 맞게 고르는 66가지 칵테일 수첩, 2011. 1. 26., 우듬지)");
        }
        else if(category.equals("칵테일의 분류")){
            context_txt.setText("칵테일을 마시는 때와 그 장소에 따라 분류하면 다음과 같다.\n" +
                    "\n" +
                    "① 애피타이저 칵테일(appetizer cocktail):애피타이저란 식욕증진이라는 뜻이며, 식사 전에 한두 잔 마시는 칵테일이다. 단맛과 쓴맛이 각각 나도록 만드는데, 단맛을 위해서는 체리를, 쓴맛을 위해서는 올리브를 장식해서 낸다. 어느 것이나 다 술과 같이 먹어도 좋다.\n" +
                    "\n" +
                    "② 크랩 칵테일(crab cocktail):정찬의 오르되브르 또는 수프 대신 내놓는 것으로, 먹는 칵테일이다. 신선한 어패류와 채소에 칵테일 소스(브랜디·비터스·토마토케첩을 섞은 것)를 얹은 것으로, 샴페인 글라스·슈림프스 글라스 등에 담아 낸다. 크랩 칵테일·로열 클로버 등이 이에 속한다.\n" +
                    "\n" +
                    "③ 비포 디너 칵테일(before dinner cocktail):식사 전의 칵테일로서 상쾌한 맛을 내는데, 마티니 미디엄 칵테일·맨해튼 미디엄 칵테일 등이 있다.\n" +
                    "\n" +
                    "④ 애프터 디너 칵테일(after dinner cocktail):식후의 칵테일로, 먹은 음식물의 소화를 촉진시킬 수 있는 리큐어를 쓴다. 브랜디 칵테일·알렉산더 칵테일 등 단맛나는 것이 많다.\n" +
                    "\n" +
                    "⑤ 서퍼 칵테일(supper cocktail):만찬 때 마시는 것이지만 일명 비포 미드나잇 칵테일(before midnight cocktail)이라고도 하며, 그 경우에는 단맛나는 양주를 쓴다.\n" +
                    "\n" +
                    "⑥ 샴페인 칵테일(champagne cocktail):연회석상에 내는 것으로, 그 많은 것이 낱낱이 글라스마다에서 만들어져 제공된다. 그러나 복잡한 여러 가지 종류의 양주를 배합해야 하는 경우에는 1번에 큰 셰이커에서 흔들어 만들어 글라스에 따르고 샴페인을 넣어 샴페인 글라스로 마신다.\n" +
                    "[네이버 지식백과] 칵테일의 분류 (두산백과)");
        }
        else if(category.equals("칵테일의 역사")){
            context_txt.setText("칵테일의 역사는 술의 탄생과 거의 동시에 시작됐다고 할 수 있다. 원시의 인간들은 술을 만들어 마시는 과정에서 과실주에 물이나 과즙을 섞어, 독하고 거친 맛을 제거해 왔던 것으로 짐작된다. 기원전부터 이집트에서는 맥주에 꿀을 섞어 마셨고, 로마에서는 와인을 생수에 섞어 마시기도 했다. 이는 중세기까지 이어져 왔다. 1658년, 인도 주재 영국인은 펀치(Punch)를 고안해 냈다. 이 펀치는 인도어로 ‘다섯’을 의미하는데 술, 설탕, 라임(과일), 스파이스(주스), 물 등의 다섯 가지의 재료를 사용해 만들었기 때문이다.\n" +
                    "\n" +
                    "이러한 혼합 음료가 칵테일(Cocktail)이라는 이름으로 불린 것은 18세기 중엽쯤으로, 1748년 영국의 책자 〈스퀘어 레시피(The Squire Recipes)〉에 칵테일이라는 단어가 처음으로 나온다. 문헌에 의하면 칵테일은, 1870년대에 독일의 기계공학자 카를 린데(Carl Linde)가 암모니아 압축에 의한 인공 냉동기를 발명한 후 여러 가지 모양의 글라스가 일반화되면서부터 만들어졌다고 볼 수 있는데, 미국을 시초로 하여 칵테일이 계승 발전되어 온 것이라는 게 통설이다. 그 후 1800년대 후기와 1900년대 초반에 걸쳐 칵테일은 서서히 대중화되기 시작했고, 이러한 양상들은 여성들이 부엌에서 벗어나 외식을 즐기기 시작하면서 그 열기를 더해 갔다.\n" +
                    "\n" +
                    "좀 더 본격화된 것은 미국의 금주법이 시행되는 동안(1920~1933년) 일반인들 사이에서 당국의 눈을 피하기 위하여 주스류나 크림, 탄산수 등을 혼합해 시각적으로 술이 아닌 것처럼 보이는 칵테일을 만들어 마시면서부터 더욱 널리 보급되었다. 후에 금주법이 해제되자 칵테일은 전성기를 맞이했다. 칵테일이 우리나라에 들어온 것은 그 연대가 확실치는 않으나, 구한말 미국 대사관이 설치된 이후로 여겨진다. 우리나라에 칵테일이 대중화된 것은 8·15 광복 이후로, 당시 더욱 많은 리큐어(혼합주)가 등장하였다. 새로운 술들이 시장에 나올 때마다 보다 더 훌륭한 칵테일이 창작되어 왔으며 이는 앞으로도 계속될 것이다.\n" +
                    "[네이버 지식백과] 칵테일의 역사 (분위기에 맞게 고르는 66가지 칵테일 수첩, 2011. 1. 26., 우듬지)");
        }
        else if(category.equals("칵테일의 어원")){
            context_txt.setText("칵테일(Cocktail)은 직역하면 ‘수탉 꼬리’가 된다. 유래는 정확하지 않으나 여러 가지 설이 있다.\n" +
                    "\n" +
                    "· 첫 번째 이야기\n" +
                    "오랜 옛날, 멕시코의 유카탄 반도에 있는 캄페체(Campeche)라는 항구도시에 영국 배가 입항했는데, 상륙한 선원들이 어떤 바에 들어서니 카운터 안에서 한 소년이 껍질을 벗긴 나뭇가지를 이용해 혼합주를 만들고 있었다. 당시 영국인들은 술을 스트레이트로만 마셨기 때문에 한 선원이 소년에게 그 혼합주에 대해 묻자, 소년은 “Cora De Gallo”라고 대답했다.\n" +
                    "\n" +
                    "“Cora De Gallo”는 스페인어로 수탉의 꼬리를 의미하는데, 당시 그 소년은 자기가 들고 있는 나뭇가지의 모양이 수탉 꼬리처럼 생겼다고 생각되어 그렇게 말한 것이었다. 그러나 영국 선원들은 이것을 소년이 만들고 있는 혼합주를 일컫는 말로 착각한 것이다. 이후 혼합주를 ‘Tail of Cock’라 부르게 되었고, 이 말은 ‘Cocktail’로 줄여서 불리게 되었다고 한다.\n" +
                    "\n" +
                    "· 두 번째 이야기\n" +
                    "옛날 스페인이 뉴멕시코를 정복했을 때 그 지방에는 아즈테크족이 살고 있었는데, 7~11세기경 아즈테크족이 자리 잡기 이전에 그 지역은 돌대크족이 지배하고 있었다. 당시 돌대크족의 한 귀족이 진귀한 혼성주를 만들어 어여쁜 자신의 딸 ‘콕톨’과 함께 왕에게 바쳤다. 왕은 크게 만족하여 즉시 그 혼성주의 이름을 귀족의 딸 이름을 따서 ‘콕톨’이라 칭했고, 후에 그것이 ‘Cocktail’이라고 불리기 시작하였다고 한다.\n" +
                    "\n" +
                    "· 세 번째 이야기\n" +
                    "18세기 말 서인도제도 아이티(Haiti)섬 동부의 도미니카공화국 산토 도밍고(Santo Domingo)에 반란이 일어나자, 이를 피해 미국 중남부 루이지애나(Louisiana)주의 뉴올리언스(New Orleans)로 이주해 온 안토니 페이차우(Antoine Peychau)라는 사람이 로열가(Royal Street) 437번지에 약국을 열었다. 그는 혼합 물약을 계량하는 데 에그컵을 썼는데, 당시 아이티에서 쓰던 프랑스어로 그것을 ‘Codquetier’라고 불렀다. 혼합된 음료를 프랑스에서는 ‘Coquetel’이라 불렀기 때문인데, 그 후 약용으로서의 의미는 잊어버리고 그 명칭도 ‘Cockstail’이라 부르게 되었다고 전해진다.\n" +
                    "\n" +
                    "· 네 번째 이야기\n" +
                    "미국의 유명한 술의 고장 켄터키(Kentucky)에서는 투계가 유행하였다. 이때 돈을 걸고 닭싸움을 시키던 한 사람이 돈을 잃게 되자, 화가 난 나머지 마시던 여러 종류의 술을 섞어서 싸움에 진 닭의 꽁지 깃털을 뽑아 술잔에 넣어 마셨다. 그때 옆에 있던 사람들이 “Cock’s tail!” 하며 크게 웃자 그것을 본 주위 사람들이 모든 술을 섞고는 닭의 꼬리로 장식하여 승부의 희비를 나누었고, 이후 혼합한 술을 ‘Cockstail’이라 부르게 되었다고 전해진다.\n" +
                    "[네이버 지식백과] 칵테일의 어원 (분위기에 맞게 고르는 66가지 칵테일 수첩, 2011. 1. 26., 우듬지)");
        }
        else{
            context_txt.setText("바텐더는 각종 술에 향신료, 과일, 크림 등을 적당한 비율로 혼합하여 고객의 취향에 맞는 칵테일 또는 기타 음료를 만든다. 칵테일에 들어갈 재료를 준비하며 와인, 생맥주, 병맥주 및 칵테일용 과일, 증류수, 소다수 등의 재고 여부를 체크한다. 칵테일이나 과일펀치 등을 만들기 위하여 증류수, 소다수, 칵테일용 음료 등과 꼬냑, 위스키, 진, 보드카 등의 술을 칵테일 방법에 따라 적당히 혼합하고, 레몬조각, 버찌, 올리브 등을 꼬치에 꽂아 잔 위에 걸쳐 장식하여 제공한다. 춤, 마술, 쉐이커를 흔드는 등 칵테일 제조 방법을 시각적으로 연출하여 다양한 볼거리를 제공하기도 한다. 매일 판매된 음료와 술을 일일판매기록부에 기록, 정리하고 월말에는 재고조사를 한다.\n" +
                    "[네이버 지식백과] 바텐더 (한국직업능력개발원 커리어넷 직업정보)");
        }
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
