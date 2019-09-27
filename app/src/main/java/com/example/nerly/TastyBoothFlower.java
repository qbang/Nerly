package com.example.nerly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TastyBoothFlower extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private FloatingActionButton floatingActionButton;
    private FloatingActionButton putItem;
    private ImageView back;
    private ImageView qr;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasty_booth_flower);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_tasty_booth_flower_ticket, null);

        // bottom navi
        floatingActionButton = (FloatingActionButton) findViewById(R.id.home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TastyBoothFlower.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // bottom navigate
        CurvedBottomNavigationView mView = findViewById(R.id.customBottomBar);
        mView.inflateMenu(R.menu.bottom_menu);
        mView.setSelectedItemId(R.id.home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(TastyBoothFlower.this);

        callItem();

        final List<String> selectedItem = new ArrayList<String>();
        final List<String> selectedPrice = new ArrayList<String>();

        //상품담기 버튼 클릭하면 다이어로그 나오게
        putItem = (FloatingActionButton) findViewById(R.id.putItem);
        putItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"박하차", "전통차", "해바라기차", "구기자차"};
                final String[] prices = new String[]{"3000원", "3500원", "2800원", "3000원"};
                Toast.makeText(TastyBoothFlower.this, "상품 개수는 부스에서 문의바랍니다.", Toast.LENGTH_LONG).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(TastyBoothFlower.this);
                dialog.setTitle("상품을 선택해주세요")
                      .setMultiChoiceItems(items,
                              new boolean[]{false, false, false, false},
                              new DialogInterface.OnMultiChoiceClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                      if(isChecked){
                                          selectedItem.add(items[which]);
                                          selectedPrice.add(prices[which]);
                                      }else{
                                          selectedItem.remove(items[which]);
                                          selectedPrice.remove(prices[which]);
                                      }
                                  }
                              })
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(selectedItem.size() == 0){
                                Toast.makeText(TastyBoothFlower.this, "선택된 상품이 없습니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                String items = "";
                                for (String selectedItem : selectedItem){
                                    items += (selectedItem + ", ");
                                }

                                for(int i=0; i<selectedItem.size(); i++)
                                mDatabase.child("nerly").child("shopping").child("달꽃").child("item").setValue(selectedItem);
                                mDatabase.child("nerly").child("shopping").child("달꽃").child("price").setValue(selectedPrice);

                                selectedItem.clear();
                                selectedPrice.clear();
                                items = items.substring(0, items.length() -2);
                                Toast.makeText(TastyBoothFlower.this, items+"를(을) 장바구니에 담았습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).create().show();
            }
        });

        final ImageView resBell = (ImageView) findViewById(R.id.resBell);
        resBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resBell.setImageResource(R.drawable.full_bell);
                Intent intent = new Intent(TastyBoothFlower.this, TastyBoothFlowerTicket.class);
                startActivity(intent);

                addToReservation("달꽃","004번", "15분", "A001");
            }
        });

        final ImageView qr = (ImageView) findViewById(R.id.qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator qrScan = new IntentIntegrator(TastyBoothFlower.this);
                qrScan.setPrompt("QR코드를 인식해주세요.");
                qrScan.initiateScan();
            }
        });

        // 뒤로가기
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

    public void callItem(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.backha_tea);
        list.add(R.drawable.basic_tea);
        list.add(R.drawable.sunflower_tea);
        list.add(R.drawable.wolfberry_tea);

        ArrayList<String> title = new ArrayList<>();
        title.add("박하차");
        title.add("전통차");
        title.add("해바라기차");
        title.add("구기자차");

        ArrayList<String> price = new ArrayList<>();
        price.add("3000원");
        price.add("3500원");
        price.add("2800원");
        price.add("3000원");

        ViewPager viewPager = findViewById(R.id.viewPager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < list.size(); i++) {
            TastyFlowerFragment flowerFragment = new TastyFlowerFragment();
            Bundle bundle = new Bundle();

            bundle.putString("textRes", title.get(i));
            bundle.putInt("imgRes", list.get(i));
            bundle.putString("priceRes", price.get(i));

            flowerFragment.setArguments(bundle);
            fragmentAdapter.addItem(flowerFragment);
        }
        fragmentAdapter.notifyDataSetChanged();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent = new Intent(TastyBoothFlower.this, Reservation.class);
                startActivity(intent);
                break;
            case R.id.home:
                Intent intent2 = new Intent(TastyBoothFlower.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.shopping:
                Intent intent3 = new Intent(TastyBoothFlower.this, Shopping.class);
                startActivity(intent3);
                break;
        }
        return true;
    }



    //QR 코드 결과 처리
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            //QR 없으면
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }else{
                //QR 있으면
                try{
                    JSONObject object = new JSONObject(result.getContents());
                    String boothName = object.getString("booth");

                    if(boothName.equals("달꽃")){
                        Toast.makeText(getApplicationContext(), "스탬프찍기 완료!", Toast.LENGTH_LONG).show();

                        // 스탬프 이미지 바꾸기
                        qr = (ImageView) findViewById(R.id.qr);
                        qr.setImageResource(R.drawable.qr_full);
                        qr.setEnabled(false);

                        //stampNum 1개씩 증가
                        Intent intent = new Intent(this, TastyBooth.class);
                        intent.putExtra("stampNum", 1);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "올바른 QR코드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void addToReservation(String booth_name, String wait_num, String wait_time, String booth_location){
        DBReservation reservation = new DBReservation(booth_name, wait_num, wait_time, booth_location);
        mDatabase.child("nerly").child("reservation").child(booth_name).setValue(reservation);
    }
}
