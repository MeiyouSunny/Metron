package com.metron.coin.ui.home;

import android.util.Log;
import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentMinterBinding;

/**
 * 矿机
 */
public class MinterFragment extends BaseBindFragment<FragmentMinterBinding> {

    private final int[] mIcons = {R.drawable.ic_btc, R.drawable.ic_btc, R.drawable.ic_eth, R.drawable.ic_usdt};
    private final int[] mTitles = {R.string.miner_all, R.string.miner_btc, R.string.miner_eth, R.string.miner_usdt};

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_minter;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.viewPager.setOffscreenPageLimit(3);
        bindRoot.viewPager.setAdapter(new MinterTypeAdapter(getContext(), getChildFragmentManager()));

        bindRoot.setIcons(mIcons);
        bindRoot.setTitles(mTitles);
        bindRoot.setType(0);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.typeSelect:
                bindRoot.setTypeListVisible(!bindRoot.getTypeListVisible());
                break;
            case R.id.typeAll:
                selectType(0);
                break;
            case R.id.typeBTC:
                selectType(1);
                break;
            case R.id.typeETH:
                selectType(2);
                break;
            case R.id.typeUSDT:
                selectType(3);
                break;
        }
    }

    private void selectType(int type) {
        bindRoot.setTypeListVisible(false);
        bindRoot.setType(type);

        if (type < 3)
            bindRoot.viewPager.setCurrentItem(type);
    }

}