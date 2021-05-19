package com.metron.xiaoming.ui.settings;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivitySettngsBinding;
import com.metron.xiaoming.ui.account.LoginActivity;
import com.metron.xiaoming.ui.account.ModifyPwdActivity;
import com.metron.xiaoming.ui.dialog.DialogLogout;
import com.metron.xiaoming.ui.home.HomeActivity;
import com.metron.xiaoming.util.ViewUtil;

import likly.dialogger.Dialogger;
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
                showLogoutDialog();
                break;
        }
    }

    private void showLogoutDialog() {
        Dialogger.newDialog(getContext())
                .holder(new DialogLogout(new DialogLogout.OnExitListener() {
                    @Override
                    public void onExit() {
                        logout();
                    }
                }))
                .show();
    }

    private void logout() {
        $.config().putString("phone", "");
        $.config().putString("pwd", "");
        ViewUtil.gotoActivity(this, LoginActivity.class, "type", LoginActivity.TYPE_LOGIN);
        HomeActivity.instance.finish();
        finish();
    }

}