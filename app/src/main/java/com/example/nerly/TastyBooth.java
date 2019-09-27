package com.example.nerly;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class TastyBooth extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, TastyBoothFragment1.OnFragmentInteractionListener, TastyBoothFragment2.OnFragmentInteractionListener{
    private ViewPager viewPager;
    private TabLayout tablayout;
    private TastyBoothPagerAdpater adapter;
    private FloatingActionButton floatingActionButton;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //search 레이아웃 살펴보기
        setContentView(R.layout.activity_tasty_booth);

        tablayout = (TabLayout) findViewById(R.id.tabLayout);
        tablayout.addTab(tablayout.newTab().setText("1F"));
        tablayout.addTab(tablayout.newTab().setText("2F"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new TastyBoothPagerAdpater(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(TastyBooth.this);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView stampNum = (TextView) findViewById(R.id.stampNum);

        Intent intent = getIntent();
        int num = intent.getIntExtra("stampNum", 0);
        stampNum.setText(String.valueOf(num));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent = new Intent(TastyBooth.this, Reservation.class);
                startActivity(intent);
                break;
            case R.id.home:
                Intent intent2 =new Intent(TastyBooth.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.shopping:
                Intent intent3 = new Intent(TastyBooth.this, Shopping.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
