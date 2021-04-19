package com.metron.coin.ui.welcom;

import android.util.Log;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseViewBindActivity;
import com.metron.coin.databinding.ActivityWelcomBinding;

public class WelcomActivity extends BaseViewBindActivity<ActivityWelcomBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void onViewCreated() {
        testApi();
    }

    public void testApi(View view) {
        testApi();
    }

    private void testApi() {
        Log.e("testApi", "testApi");
        ApiUtil.apiService().login("18189202461", "123456",
                new Callback<TokenInfo>() {

                    @Override
                    public void onResponse(TokenInfo tokenInfo) {
                        UserDataUtil.instance().setTokenInfo(tokenInfo);

                        ApiUtil.apiService().getUserInfo(new Callback<UserInfo>() {
                            @Override
                            public void onResponse(UserInfo response) {
                                super.onResponse(response);
                            }

                            @Override
                            public void onError(int code, String msg) {
                                super.onError(code, msg);
                            }
                        });
                    }

                });
    }

}