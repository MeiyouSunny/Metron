package com.metron.coin.ui.settings;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivitySettngsBinding;

/**
 * 设置
 */
public class SettingActivity extends BaseTitleActivity<ActivitySettngsBinding> {

    @Override
    protected String title() {
        return "设置";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_settngs;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.exit:
//                exitAccount();
//                break;
        }
    }

}