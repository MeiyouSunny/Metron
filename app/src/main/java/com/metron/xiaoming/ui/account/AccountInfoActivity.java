package com.metron.xiaoming.ui.account;

import android.content.Intent;
import android.view.View;

import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityAccountInfoBinding;
import com.metron.xiaoming.ui.home.HomeActivity;
import com.metron.xiaoming.util.ViewUtil;

import likly.dollar.$;

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
        bindRoot.setIsChannelUser(UserDataUtil.isChannelUser());
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
            case R.id.swtichAccount:
                boolean isChannelUser = UserDataUtil.isChannelUser();
                isChannelUser = !isChannelUser;
                UserDataUtil.setIsChannelUser(isChannelUser);
                bindRoot.setIsChannelUser(isChannelUser);
                final String hintText = "已切换至 " + (isChannelUser ? "渠道专员" : "普通用户");
                $.toast().text(hintText).show();
                HomeActivity.instance.finish();
                ViewUtil.gotoActivity(this, HomeActivity.class);
                finish();
                break;
        }
    }

}