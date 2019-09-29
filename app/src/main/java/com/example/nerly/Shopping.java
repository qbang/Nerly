package com.example.nerly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Shopping extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ListView shoppingList;
    private ShoppingAdpater shoppingAdpater;
    private ImageView back;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("nerly");
    private Array shopping, shopping2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(Shopping.this);

        shoppingList = (ListView) findViewById(R.id.resList2);
        shoppingAdpater = new ShoppingAdpater();
        shoppingList.setAdapter(shoppingAdpater);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot messageData : dataSnapshot.getChildren()){
                    JSONObject jsonObject = new JSONObject((Map) messageData.getValue());

                    try {
                        Iterator i = jsonObject.keys();
                        while(i.hasNext()){
                            String booth = i.next().toString();
                            JSONObject dataObject = (JSONObject) jsonObject.get(booth);
                            JSONArray  items = (JSONArray) dataObject.get("item");
                            JSONArray  prices = (JSONArray) dataObject.get("price");
                            for(int index = 0 ; index < items.length();index++){
                                String boothName = booth;
                                String itemName = (String) items.get(index);
                                String itempPrice = (String) prices.get(index);
                                shoppingAdpater.addItem(boothName,itemName,itempPrice,ContextCompat.getDrawable(Shopping.this, R.drawable.flower_tea));
                            }

                        }

                    }catch(JSONException e) {
                        e.printStackTrace();
                    }
                    //try 블럭
                    shoppingAdpater.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent3 = new Intent(Shopping.this, Reservation.class);
                startActivity(intent3);
                break;
            case R.id.home:
                Intent intent =new Intent(Shopping.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.shopping:
                break;
        }
        return true;
    }
}
