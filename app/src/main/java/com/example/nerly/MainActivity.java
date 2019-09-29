package com.example.nerly;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<MainItem> itemList = new ArrayList<>();
    private MainItemAdpater itemAdpater;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("nerly");
    private String name1, name2, name3, name4, name5, name6, name7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(MainActivity.this);

        TextView textView = (TextView) findViewById(R.id.plus);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TastyList.class);
                startActivity(intent);
            }
        });

        TextView textView2 = (TextView) findViewById(R.id.plus2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TastyList.class);
                startActivity(intent);
            }
        });

        TextView textView3 = (TextView) findViewById(R.id.plus3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TastyList.class);
                startActivity(intent);
            }
        });

        TextView textView4 = (TextView) findViewById(R.id.plus4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TastyList.class);
                startActivity(intent);
            }
        });

        callTasty();
        callArt();
        callWedding();
        callDog();

        MainItem a = new MainItem("친환경유기농 무역박람회 2019", R.drawable.organic);
        itemList.add(a);

        MainItem b = new MainItem("한국국제사인디자인전", R.drawable.ink);
        itemList.add(b);

        MainItem e = new MainItem("서울 펫쇼", R.drawable.welsh);
        itemList.add(e);

        MainItem c = new MainItem("허니문 박람회", R.drawable.honeymoon);
        itemList.add(c);

        MainItem d = new MainItem("서울웨딩페어", R.drawable.bouquet);
        itemList.add(d);

        // picked Item
        recyclerView = findViewById(R.id.idRecyclerViewHorizontalList);
        itemAdpater = new MainItemAdpater(itemList, getApplicationContext());
        final LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(itemAdpater);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot messageData : dataSnapshot.getChildren()){
                    JSONObject jsonObject = new JSONObject((Map) messageData.getValue());
                    try {
                        Iterator i = jsonObject.keys();
                        int tempIndex = 0;
                        while(i.hasNext()){
                            if(tempIndex == 1){

                            }
                            String booth = i.next().toString();
                            String resName = "coffee";
                            String packName = "com.example.nerly"; // 패키지명
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            JSONObject dataObject1 = (JSONObject) jsonObject.get(booth);
                            name1 = (String) dataObject1.get("name");
                            MainItem z = new MainItem(name1, resID);
                            itemList.add(z);
                            tempIndex += 1;
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    itemAdpater.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent = new Intent(MainActivity.this, Reservation.class);
                startActivity(intent);
                break;
            case R.id.home:
                break;
            case R.id.shopping:
                Intent intent2 = new Intent(MainActivity.this, Shopping.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        // ViewPager에 들어갈 Fragment들을 담을 리스트
        private ArrayList<Fragment> fragments = new ArrayList<>();

        // 필수 생성자
        FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        // List에 Fragment를 담을 함수
        void addItem(Fragment fragment) {
            fragments.add(fragment);
        }
    }

    public void callTasty(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.coffee);
        list.add(R.drawable.baguette);
        list.add(R.drawable.tea);
        list.add(R.drawable.organic);

        ArrayList<String> title = new ArrayList<>();
        title.add("제 18회 서울카페쇼");
        title.add("서울발효식문화전 2019");
        title.add("세계명차품평대회");
        title.add("친환경유기농 무역박람회 2019");

        ViewPager viewPager = findViewById(R.id.viewPager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < list.size(); i++) {
            MainFragment mainFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("textRes", title.get(i));
            bundle.putInt("imgRes", list.get(i));
            mainFragment.setArguments(bundle);
            fragmentAdapter.addItem(mainFragment);
        }
        fragmentAdapter.notifyDataSetChanged();
    }

    public void callArt(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.artfair);
        list.add(R.drawable.doll);
        list.add(R.drawable.ink);
        list.add(R.drawable.design);

        ArrayList<String> title = new ArrayList<>();
        title.add("한국국제아트페어");
        title.add("프로젝트돌");
        title.add("한국국제사인디자인전");
        title.add("서울디자인페스티벌");

        ViewPager viewPager = findViewById(R.id.viewPager4);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < list.size(); i++) {
            MainFragment mainFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("textRes", title.get(i));
            bundle.putInt("imgRes", list.get(i));
            mainFragment.setArguments(bundle);
            fragmentAdapter.addItem(mainFragment);
        }
        fragmentAdapter.notifyDataSetChanged();
    }

    public void callWedding(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.honeymoon);
        list.add(R.drawable.bouquet);
        list.add(R.drawable.wedding_market);
        list.add(R.drawable.wedding_fall);

        ArrayList<String> title = new ArrayList<>();
        title.add("허니문 박람회");
        title.add("서울웨딩페어");
        title.add("2019 웨딩전시회 '웨딩마켓'");
        title.add("제 52회 추계 웨덱스코리아 웨딩박람회");

        ViewPager viewPager = findViewById(R.id.viewPager2);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < list.size(); i++) {
            MainFragment mainFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("textRes", title.get(i));
            bundle.putInt("imgRes", list.get(i));
            mainFragment.setArguments(bundle);
            fragmentAdapter.addItem(mainFragment);
        }
        fragmentAdapter.notifyDataSetChanged();
    }

    public void callDog(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.welsh);
        list.add(R.drawable.snake);
        list.add(R.drawable.puppy_snack);

        ArrayList<String> title = new ArrayList<>();
        title.add("서울 펫쇼");
        title.add("희귀애완동물 박람회");
        title.add("친환경 애견스낵 박람회");

        ViewPager viewPager = findViewById(R.id.viewPager3);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < list.size(); i++) {
            MainFragment mainFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("textRes", title.get(i));
            bundle.putInt("imgRes", list.get(i));
            mainFragment.setArguments(bundle);
            fragmentAdapter.addItem(mainFragment);
        }
        fragmentAdapter.notifyDataSetChanged();
    }
}