package com.metron.coin.ui.account;

import android.content.Intent;
import android.view.View;

import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityAccountInfoBinding;

/**
 * 账户信息
 */
public class AccountInfoActivity extends BaseTitleActivity<ActivityAccountInfoBinding> {

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
    public void onResume() {
        super.onResume();
        UserInfo userInfo = UserDataUtil.instance().getUserInfo();
        if (userInfo != null)
            bindRoot.setUserInfo(userInfo);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.userName:
                SetProfileActivity.start(this, SetProfileActivity.NAME);
                break;
            case R.id.phone:
                Intent intent = new Intent(this, ModifyPwdActivity.class);
                intent.putExtra("isModifyPhone", true);
                startActivity(intent);
                break;
        }
    }

}