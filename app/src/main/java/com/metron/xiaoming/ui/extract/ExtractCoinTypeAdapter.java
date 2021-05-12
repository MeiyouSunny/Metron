package com.metron.xiaoming.ui.extract;

import android.content.Context;

import com.metron.xiaoming.util.CoinConst;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExtractCoinTypeAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private Fragment[] mTabs;

    public ExtractCoinTypeAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabs = new Fragment[]{new ExtractFragment(CoinConst.BTC), new ExtractFragment(CoinConst.ETH)};
    }

    @Override
    public Fragment getItem(int position) {
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

}