package com.metron.coin.ui.home;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabProfitAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private ProfitBTCFragment[] mTabs;

    public TabProfitAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabs = new ProfitBTCFragment[3];
    }

    @Override
    public Fragment getItem(int position) {
        if (mTabs[position] == null)
            mTabs[position] = new ProfitBTCFragment();
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

}