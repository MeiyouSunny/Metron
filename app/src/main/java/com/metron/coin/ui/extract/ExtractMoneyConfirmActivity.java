package com.metron.coin.ui.extract;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityExtractMoneyConfirmBinding;

/**
 * 申请提现确认
 */
public class ExtractMoneyConfirmActivity extends BaseTitleActivity<ActivityExtractMoneyConfirmBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_extract_money_confirm;
    }

    @Override
    protected String title() {
        return "申请提现";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
    }

}