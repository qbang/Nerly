package com.example.nerly;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TastyBoothFragment2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ListView boothList;
    TastyBoothAdpater2 tastyBoothAdpater;

    private OnFragmentInteractionListener mListener;

    public TastyBoothFragment2() {
        // Required empty public constructor
    }

    public static TastyBoothFragment2 newInstance(String param1, String param2) {
        TastyBoothFragment2 fragment = new TastyBoothFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasty_booth_fragment2, container, false);

        boothList = (ListView) view.findViewById(R.id.listview_frag2);
        tastyBoothAdpater = new TastyBoothAdpater2();
        boothList.setAdapter(tastyBoothAdpater);

        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.brownie),"파리브라우니","파리의 맛을 전하는 진짜 브라우니", "2-A001", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.pumpkinlatte),"웰인더스토리","테마 커피 연구소", "2-A002", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.smallcup),"스몰스위트컵","작은 잔에 큰 감동을", "2-A003", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.macaaron),"대삼제과","마카롱", "2-A004", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.fruit),"루츠오프프룻","신선한 과일을 맛보세요", "2-A005", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.legend),"전설커피","커피계의 전설", "2-A006", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.coffeescience),"사이언스커피","커피의 원리를 이해하다", "2-A007", "더보기");

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
