package com.metron.coin.ui.welcom;

import android.text.TextUtils;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseViewBindActivity;
import com.metron.coin.databinding.ActivityWelcomBinding;
import com.metron.coin.ui.account.LoginActivity;
import com.metron.coin.ui.home.HomeActivity;
import com.metron.coin.util.ViewUtil;

import likly.dollar.$;

public class WelcomActivity extends BaseViewBindActivity<ActivityWelcomBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void onViewCreated() {
        autoLogin();
    }

    private void autoLogin() {
        String phone = $.config().getString("phone");
        String pwd = $.config().getString("pwd");
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            login(phone, pwd);
        } else {
            ViewUtil.gotoActivity(this, LoginActivity.class);
            finish();
        }
    }

    private void login(String phone, String pwd) {
        ApiUtil.apiService().login(phone, pwd,
                new Callback<TokenInfo>() {

                    @Override
                    public void onResponse(TokenInfo tokenInfo) {
                        UserDataUtil.instance().setTokenInfo(tokenInfo);
                        getUserInfo();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        super.onError(code, msg);
                    }
                });
    }

    private void getUserInfo() {
        ApiUtil.apiService().getUserInfo(new Callback<UserInfo>() {
            @Override
            public void onResponse(UserInfo userInfo) {
                UserDataUtil.instance().setUserInfo(userInfo);
                ViewUtil.gotoActivity(getContext(), HomeActivity.class);
                finish();
            }
        });
    }

}