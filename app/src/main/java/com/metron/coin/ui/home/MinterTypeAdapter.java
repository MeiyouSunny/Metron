package com.metron.coin.ui.home;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MinterTypeAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private Fragment[] mTabs;

    public MinterTypeAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabs = new Fragment[] {new MinterAllFragment(), new MinterTypeFragment(), new MinterTypeFragment()};
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