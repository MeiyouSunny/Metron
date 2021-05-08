package com.metron.coin.ui.settings;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivitySettngsBinding;
import com.metron.coin.ui.account.LoginActivity;
import com.metron.coin.ui.account.ModifyPwdActivity;
import com.metron.coin.ui.home.HomeActivity;
import com.metron.coin.util.ViewUtil;

import likly.dollar.$;

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
            case R.id.about:
                ViewUtil.gotoActivity(this, AboutActivity.class);
                break;
            case R.id.modifyPwd:
                ViewUtil.gotoActivity(this, ModifyPwdActivity.class);
                break;
            case R.id.logout:
                logout();
                break;
        }
    }

    private void logout() {
        $.config().putString("phone", "");
        $.config().putString("pwd", "");
        ViewUtil.gotoActivity(this, LoginActivity.class);
        HomeActivity.instance.finish();
        finish();
    }

}