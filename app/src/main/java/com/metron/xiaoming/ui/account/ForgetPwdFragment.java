package com.metron.xiaoming.ui.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.meiyou.mvp.MvpBinder;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBackFragment;
import com.metron.xiaoming.databinding.FragmentForgetPwdBinding;
import com.metron.xiaoming.util.SimpleTextWatcher;
import com.metron.xiaoming.util.ViewUtil;

@MvpBinder(
)
public class ForgetPwdFragment extends BaseBackFragment<FragmentForgetPwdBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                sendSms();
                break;
        }
    }

    @Override
    protected void onTitleRightClick() {
        navigate(R.id.action_registInput_to_login);
    }

    @Override
    protected String rightTitle() {
        return "登录";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean hasInput = !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPhone));
                bindRoot.btnGetCode.setEnabled(hasInput);
            }
        });
    }

    private void sendSms() {
        ApiUtil.apiService().sendSms("86", ViewUtil.getText(bindRoot.etPhone),
                new Callback<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", SetPwdFragment.TYPE_FORGET_PWD);
                        bundle.putString("mobile", ViewUtil.getText(bindRoot.etPhone));
                        navigate(R.id.action_forgetPwd_to_verifyPhone, bundle);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        super.onError(code, msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

}