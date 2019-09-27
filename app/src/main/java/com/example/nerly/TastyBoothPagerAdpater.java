package com.example.nerly;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TastyBoothPagerAdpater extends FragmentStatePagerAdapter {
    int mNumofTabs;

    public TastyBoothPagerAdpater(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumofTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                TastyBoothFragment1 tab1 = new TastyBoothFragment1();
                return tab1;
            case 1:
                TastyBoothFragment2 tab2 = new TastyBoothFragment2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumofTabs;
    }
}
