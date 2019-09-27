package com.example.nerly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TastyList extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, back;
    private FloatingActionButton floatingActionButton;
    private DatabaseReference mDatabase;
    private TextView title1, title2, title3, title4, title5, title6;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasty_list);

        title1 = (TextView) findViewById(R.id.boothName);
        title2 = (TextView) findViewById(R.id.boothName2);
        title3 = (TextView) findViewById(R.id.boothName3);
        title4 = (TextView) findViewById(R.id.boothName4);
        title5 = (TextView) findViewById(R.id.boothName5);
        title6 = (TextView) findViewById(R.id.boothName6);

        imageView = (ImageView) findViewById(R.id.leaf1);
        imageView2 = (ImageView) findViewById(R.id.leaf2);
        imageView3 = (ImageView) findViewById(R.id.leaf3);
        imageView4 = (ImageView) findViewById(R.id.leaf4);
        imageView5 = (ImageView) findViewById(R.id.leaf5);
        imageView6 = (ImageView) findViewById(R.id.leaf6);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title1.getText().toString());

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title2.getText().toString());

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView3.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title3.getText().toString());

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView4.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title4.getText().toString());

            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView5.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title5.getText().toString());

            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView6.setImageResource(R.drawable.leaves_green);
                //firebase 연결
                addToDatabase(title6.getText().toString());

            }
        });

        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TastyList.this, TastyBooth.class);
                startActivity(intent);
            }
        });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TastyList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        // getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(TastyList.this);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent = new Intent(TastyList.this, Reservation.class);
                startActivity(intent);
                break;
            case R.id.home:
                Intent intent3 = new Intent(TastyList.this, MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.shopping:
                Intent intent2 = new Intent(TastyList.this, Shopping.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

    private void addToDatabase(String booth_name){
        if(i<7){
            i++;
            DBLikeItem likeItem = new DBLikeItem(booth_name);
            mDatabase.child("nerly").child("like").child("booth"+i).setValue(likeItem);
        }else{
            Toast.makeText(this, "더이상 찜할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
