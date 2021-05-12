package com.metron.xiaoming.ui.welcom;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseViewBindActivity;
import com.metron.xiaoming.databinding.ActivityWelcomBinding;
import com.metron.xiaoming.ui.account.LoginActivity;
import com.metron.xiaoming.ui.home.HomeActivity;
import com.metron.xiaoming.util.ViewUtil;

import likly.dollar.$;

public class WelcomActivity extends BaseViewBindActivity<ActivityWelcomBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.login:
                ViewUtil.gotoActivity(this, LoginActivity.class, "type", LoginActivity.TYPE_LOGIN);
                finish();
                break;
            case R.id.regist:
                ViewUtil.gotoActivity(this, LoginActivity.class, "type", LoginActivity.TYPE_REGIST);
                finish();
                break;
        }
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
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    bindRoot.setVisiable(true);
                }
            }, 1500);
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