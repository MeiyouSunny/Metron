package com.metron.coin.ui.extract;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityExtractCoinConfirmBinding;
import com.metron.coin.util.ViewUtil;

/**
 * 申请提现
 */
public class ExtractMoneyActivity extends BaseTitleActivity<ActivityExtractCoinConfirmBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_extract_money;
    }

    @Override
    protected String title() {
        return "提现";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        if (view.getId() == R.id.submit) {
            ViewUtil.gotoActivity(this, ExtractMoneyConfirmActivity.class);
        }
    }

}