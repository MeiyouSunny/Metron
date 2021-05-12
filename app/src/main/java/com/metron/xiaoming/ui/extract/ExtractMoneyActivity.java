package com.metron.xiaoming.ui.extract;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityExtractCoinConfirmBinding;
import com.metron.xiaoming.util.ViewUtil;

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