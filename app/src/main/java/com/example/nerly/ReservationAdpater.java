package com.example.nerly;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ReservationAdpater extends BaseAdapter {
    private ArrayList<ReservationList> arrayList = new ArrayList<>();
    ListView resList;

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
            view = inflater.inflate(R.layout.reservation_list, viewGroup, false);
        }

        TextView text1 = (TextView)view.findViewById(R.id.boothName);
        TextView text2 = (TextView)view.findViewById(R.id.location);
        TextView text3 = (TextView)view.findViewById(R.id.waitNumber);
        TextView text4 = (TextView)view.findViewById(R.id.waiting);
        ImageView imageView = (ImageView)view.findViewById(R.id.reservationCancel);


        ReservationList listViewItem = arrayList.get(position);

        text1.setText(listViewItem.getBoothName());
        text2.setText(listViewItem.getLocation());
        text3.setText(listViewItem.getWaitNumber());
        text4.setText(listViewItem.getWaiting());
        imageView.setImageDrawable(listViewItem.getCancel());

        //아이템 삭제
        resList = (ListView) view.findViewById(R.id.resList);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.size() != 0){
                    arrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    public void addItem(String boothName, String location, String waitNumber, String waiting, Drawable cancel){
        ReservationList listViewItem = new ReservationList();

        listViewItem.setBoothName(boothName);
        listViewItem.setLocation(location);
        listViewItem.setWaitNumber(waitNumber);
        listViewItem.setWaiting(waiting);
        listViewItem.setCancel(cancel);

        arrayList.add(listViewItem);
    }
}
