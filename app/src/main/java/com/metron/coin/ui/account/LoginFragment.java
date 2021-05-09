package com.metron.coin.ui.account;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentLoginBinding;
import com.metron.coin.ui.home.HomeActivity;
import com.metron.coin.util.SimpleTextWatcher;
import com.metron.coin.util.ViewUtil;

import likly.dollar.$;

@MvpBinder(
)
public class LoginFragment extends BaseBackFragment<FragmentLoginBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.icClear:
                bindRoot.etPhone.setText("");
                break;
            case R.id.forgetPwd:
                navigate(R.id.action_to_forgetPwd);
                break;
            case R.id.btnLogin:
                login(ViewUtil.getText(bindRoot.etPhone), ViewUtil.getText(bindRoot.etPwd));
                break;
        }
    }

    @Override
    protected void onTitleRightClick() {
        navigate(R.id.action_to_registInput);
    }

    @Override
    protected String rightTitle() {
        return "注册";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });
        bindRoot.etPwd.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });

//        autoLogin();
    }

    private void autoLogin() {
        String phone = $.config().getString("phone");
        String pwd = $.config().getString("pwd");
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            login(phone, pwd);
        }
    }

    private void onInputChange() {
        boolean hasInput = !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPhone))
                && !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPwd));
        bindRoot.btnLogin.setEnabled(hasInput);
    }

    private void login(String phone, String pwd) {
        ApiUtil.apiService().login(phone, pwd,
                new Callback<TokenInfo>() {

                    @Override
                    public void onResponse(TokenInfo tokenInfo) {
                        UserDataUtil.instance().setTokenInfo(tokenInfo);
                        $.config().putString("phone", phone);
                        $.config().putString("pwd", pwd);
                        UserDataUtil.setIsChannelUser(false);

                        getUserInfo();
                    }
                });
    }

    private void getUserInfo() {
        ApiUtil.apiService().getUserInfo(new Callback<UserInfo>() {
            @Override
            public void onResponse(UserInfo userInfo) {
                UserDataUtil.instance().setUserInfo(userInfo);
                ViewUtil.gotoActivity(getContext(), HomeActivity.class);
                getActivity().finish();
            }
        });
    }

}