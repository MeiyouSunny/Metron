package com.metron.coin.ui.home;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseBindFragment<FragmentHomeBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.settings:
//                break;
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

}