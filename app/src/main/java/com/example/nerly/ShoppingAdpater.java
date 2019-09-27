package com.example.nerly;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingAdpater extends BaseAdapter {
    private ArrayList<ShoppingList> arrayList = new ArrayList<>();
    private ListView resList2;

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
            view = inflater.inflate(R.layout.shopping_list, viewGroup, false);
        }

        TextView text1 = (TextView)view.findViewById(R.id.boothName);
        TextView text2 = (TextView)view.findViewById(R.id.itemName);
        TextView text3 = (TextView)view.findViewById(R.id.itemPrice);
        ImageView imageView = (ImageView)view.findViewById(R.id.itemImage);

        ShoppingList listViewItem = arrayList.get(position);

        text1.setText(listViewItem.getBoothName());
        text2.setText(listViewItem.getItemName());
        text3.setText(listViewItem.getItemPrice());
        imageView.setImageDrawable(listViewItem.getItemImage());

        //아이템 삭제
        resList2 = (ListView) view.findViewById(R.id.resList2);
        Button certifi = (Button) view.findViewById(R.id.certifi);
        certifi.setOnClickListener(new View.OnClickListener() {
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

    public void addItem(String boothName, String itemName, String itemPrice, Drawable itemImage){
        ShoppingList listViewItem = new ShoppingList();

        listViewItem.setBoothName(boothName);
        listViewItem.setItemName(itemName);
        listViewItem.setItemPrice(itemPrice);
        listViewItem.setItemImage(itemImage);

        arrayList.add(listViewItem);
    }
}
