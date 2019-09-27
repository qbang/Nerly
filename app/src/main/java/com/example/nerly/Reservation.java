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
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class Reservation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ListView reservationList;
    private ReservationAdpater reservationAdpater;
    private ImageView back;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("nerly/shopping");
    private String boothLocation, title, waitNum, waitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(Reservation.this);

        reservationList = (ListView) findViewById(R.id.resList);
        reservationAdpater = new ReservationAdpater();
        reservationList.setAdapter(reservationAdpater);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reservationAdpater.addItem("달꽃","A001","004번", "15분", ContextCompat.getDrawable(Reservation.this, R.drawable.close));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot messageData : dataSnapshot.getChildren()){
                    JSONObject jsonObject = new JSONObject((Map) messageData.getValue());
                    try {
                        // 부스 목록과 일치하는게 있으면 가져오게 하고싶다!!!!!!
                        JSONObject dataObject1 = (JSONObject) jsonObject.get("달꽃");
                        boothLocation = (String) dataObject1.get("boothLocation");
                        title = (String) dataObject1.get("title");
                        waitNum = (String) dataObject1.get("waitNum");
                        waitTime = (String) dataObject1.get("waitTime");


                    }catch(JSONException e) {
                        e.printStackTrace();
                    }
                    //try 블럭
                    reservationAdpater.notifyDataSetChanged();
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
                break;
            case R.id.home:
                Intent intent =new Intent(Reservation.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.shopping:
                Intent intent2 =new Intent(Reservation.this, Shopping.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}
