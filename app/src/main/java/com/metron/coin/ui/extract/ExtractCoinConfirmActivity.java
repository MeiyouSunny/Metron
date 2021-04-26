package com.metron.coin.ui.extract;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityExtractCoinConfirmBinding;

/**
 * 申请提币确认
 */
public class ExtractCoinConfirmActivity extends BaseTitleActivity<ActivityExtractCoinConfirmBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_extract_coin_confirm;
    }

    @Override
    protected String title() {
        return "申请提币";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
    }

}