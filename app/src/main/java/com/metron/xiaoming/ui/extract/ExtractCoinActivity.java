package com.metron.xiaoming.ui.extract;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseViewBindActivity;
import com.metron.xiaoming.databinding.ActivityExtractCoinBinding;

/**
 * 申请提币
 */
public class ExtractCoinActivity extends BaseViewBindActivity<ActivityExtractCoinBinding> {

    private final int[] mIcons = {R.drawable.ic_btc, R.drawable.ic_eth};
    private final int[] mTitles = {R.string.miner_btc, R.string.miner_eth};

    @Override
    protected int layoutId() {
        return R.layout.activity_extract_coin;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.viewPager.setOffscreenPageLimit(3);
        bindRoot.viewPager.setAdapter(new ExtractCoinTypeAdapter(getContext(), getSupportFragmentManager()));

        bindRoot.setIcons(mIcons);
        bindRoot.setTitles(mTitles);
        bindRoot.setType(0);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.typeSelect:
                bindRoot.setTypeListVisible(!bindRoot.getTypeListVisible());
                break;
            case R.id.typeOutside:
                bindRoot.setTypeListVisible(false);
                break;
            case R.id.typeBTC:
                selectType(0);
                break;
            case R.id.typeETH:
                selectType(1);
                break;
        }
    }

    private void selectType(int type) {
        bindRoot.setTypeListVisible(false);
        bindRoot.setType(type);
        bindRoot.viewPager.setCurrentItem(type);
    }

}