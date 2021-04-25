package com.metron.coin.ui.extract;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExtractCoinTypeAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private Fragment[] mTabs;

    public ExtractCoinTypeAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabs = new Fragment[] {new ExtractFragment(), new ExtractFragment(), new ExtractFragment()};
    }

    @Override
    public Fragment getItem(int position) {
//        if (mTabs[position] == null) {
//            switch (position) {
//                case 0:
//                    mTabs[position] = new MinterAllFragment();
//                    break;
//                case 1:
//                case 2:
//                    mTabs[position] = new MinterTypeFragment();
//                    break;
//            }
//        }
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

}