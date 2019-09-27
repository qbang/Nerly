package com.example.nerly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TastyBoothAdpater1 extends BaseAdapter {
    private ArrayList<TastyBoothList> arrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.booth_list, viewGroup, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.booth_img);
        TextView text1 = (TextView)view.findViewById(R.id.booth_title);
        TextView text2 = (TextView)view.findViewById(R.id.booth_expla);
        TextView text3 = (TextView)view.findViewById(R.id.booth_num);
        TextView text4 = (TextView)view.findViewById(R.id.moreInfo);

        TastyBoothList listViewItem = arrayList.get(position);

        imageView.setImageDrawable(listViewItem.getBooth_img());
        text1.setText(listViewItem.getBooth_title());
        text2.setText(listViewItem.getBooth_expla());
        text3.setText(listViewItem.getBooth_num());
        text4.setText(listViewItem.getMore_info());

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent1 = new Intent(context, TastyBoothFlower.class);
                        context.startActivity(intent1);
                }
            }
        });


        return view;
    }

    public void addItem(Drawable drawable, String booth_title, String booth_expla, String booth_num, String more_info){
        TastyBoothList listViewItem = new TastyBoothList();
        listViewItem.setBooth_img(drawable);
        listViewItem.setBooth_title(booth_title);
        listViewItem.setBooth_expla(booth_expla);
        listViewItem.setBooth_num(booth_num);
        listViewItem.setMore_info(more_info);

        arrayList.add(listViewItem);
    }


}
