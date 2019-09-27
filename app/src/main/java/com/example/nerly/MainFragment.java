package com.example.nerly;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.title);

        if (getArguments() != null) {
            Bundle args = getArguments();

            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            imageView.setImageResource(args.getInt("imgRes"));
            textView.setText(args.getString("textRes"));
        }

        return view;
    }
}
