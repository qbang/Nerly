package com.example.nerly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TastyFlowerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasty_flower, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.title);
        TextView textView1 = view.findViewById(R.id.price);

        if (getArguments() != null) {
            Bundle args = getArguments();

            // MainActivity에서 받아온 Resource를 View에 셋팅
            imageView.setImageResource(args.getInt("imgRes"));
            textView.setText(args.getString("textRes"));
            textView1.setText(args.getString("priceRes"));
        }

        return view;
    }

}
