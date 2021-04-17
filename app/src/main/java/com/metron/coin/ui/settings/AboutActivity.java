package com.metron.coin.ui.settings;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityAboutBinding;

/**
 * 关于
 */
public class AboutActivity extends BaseTitleActivity<ActivityAboutBinding> {

    @Override
    protected String title() {
        return "关于";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_about;
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