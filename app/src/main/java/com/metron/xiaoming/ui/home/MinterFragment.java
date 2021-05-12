package com.metron.xiaoming.ui.home;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.databinding.FragmentMinterBinding;

/**
 * 矿机
 */
public class MinterFragment extends BaseBindFragment<FragmentMinterBinding> {

    private final int[] mIcons = {R.drawable.ic_btc, R.drawable.ic_btc, R.drawable.ic_eth};
    private final int[] mTitles = {R.string.miner_all, R.string.miner_btc, R.string.miner_eth};

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_minter;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        bindRoot.setIcons(mIcons);
        bindRoot.setTitles(mTitles);
        bindRoot.setType(0);
    }

    @Override
    protected void loadData() {
        bindRoot.viewPager.setOffscreenPageLimit(3);
        bindRoot.viewPager.setAdapter(new MinterTypeAdapter(getContext(), getChildFragmentManager()));
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.typeSelect:
                bindRoot.setTypeListVisible(!bindRoot.getTypeListVisible());
                break;
            case R.id.typeOutside:
                bindRoot.setTypeListVisible(false);
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
        }
    }

    private void selectType(int type) {
        bindRoot.setTypeListVisible(false);
        bindRoot.setType(type);

        if (type < 3)
            bindRoot.viewPager.setCurrentItem(type);
    }

}