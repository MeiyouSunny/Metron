package com.metron.xiaoming.ui.home;

import android.content.Context;

import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.util.CoinConst;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabProfitAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private Fragment[] mTabs;

    public TabProfitAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabs = new Fragment[2];
    }

    @Override
    public Fragment getItem(int position) {
        if (mTabs[position] == null) {
            String type = (position == 0) ? CoinConst.BTC : CoinConst.ETH;
            if (!UserDataUtil.isChannelUser())
                mTabs[position] = new ProfitTypeFragment(type);
            else
                mTabs[position] = new ProfitTypeChannelFragment(type);
        }
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

}