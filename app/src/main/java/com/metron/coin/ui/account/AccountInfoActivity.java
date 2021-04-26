package com.metron.coin.ui.account;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivitySettngsBinding;

/**
 * 账户信息
 */
public class AccountInfoActivity extends BaseTitleActivity<ActivitySettngsBinding> {

    @Override
    protected String title() {
        return "账户信息";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_account_info;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.userName:
                SetProfileActivity.start(this, SetProfileActivity.NAME);
                break;
            case R.id.phone:
                SetProfileActivity.start(this, SetProfileActivity.PHONE);
                break;
        }
    }

}