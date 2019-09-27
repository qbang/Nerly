package com.example.nerly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TastyBoothFlowerTicket extends AppCompatActivity {

    private ImageView back;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasty_booth_flower_ticket);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        AppCompatTextView textView = (AppCompatTextView) findViewById(R.id.reservationCancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeQR();
                Toast.makeText(TastyBoothFlowerTicket.this,"예약을 취소하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void changeQR(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_tasty_booth_flower, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.resBell);
        imageView.setImageResource(R.drawable.bell);

        //db에서 삭제
        mDatabase.child("nerly/reservation/달꽃").setValue(null);

        //shopdetail 화면으로 back
        Intent intent = new Intent(TastyBoothFlowerTicket.this, TastyBoothFlower.class);
        startActivity(intent);
    }
}