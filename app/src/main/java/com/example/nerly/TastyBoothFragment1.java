package com.example.nerly;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class TastyBoothFragment1 extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ListView boothList;
    private TastyBoothAdpater1 tastyBoothAdpater;
    private Object mData;

    public TastyBoothFragment1() {
        // Required empty public constructor
    }

    public static TastyBoothFragment1 newInstance(String param1, String param2) {
        TastyBoothFragment1 fragment = new TastyBoothFragment1();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasty_booth_fragment1, container, false);
        final Context context = view.getContext();

        boothList = (ListView) view.findViewById(R.id.listview_frag1);
        tastyBoothAdpater = new TastyBoothAdpater1();
        boothList.setAdapter(tastyBoothAdpater);

        addTastyItem();

        boothList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

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
        }
        else {
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

    public void addTastyItem(){
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.flower_tea),"달꽃","Flower of Moon", "1-A001", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.bamboo),"죽림다일","향긋한 대나무의 향을 그대로", "1-A002", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.green_tea),"보성차협동조합","보성차협동조합", "1-A003", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.herb),"(주)허브월두","허브, 허브티 판매", "1-A004", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.flower),"코레꽃연구소","꽃을 연구하는 작은 곳", "1-A005", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.apple),"카인드오프애플","달콤한 사과, 쌉싸름한 사과", "1-A006", "더보기");
        tastyBoothAdpater.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.shaved_ice),"쿠빙수","여름하면 빙수!", "1-A007","더보기");
    }
}
