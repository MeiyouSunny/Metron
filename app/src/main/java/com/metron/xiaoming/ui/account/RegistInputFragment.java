package com.metron.xiaoming.ui.account;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.meiyou.mvp.MvpBinder;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBackFragment;
import com.metron.xiaoming.databinding.FragmentRegistInputBinding;
import com.metron.xiaoming.ui.webpage.WebPageActivity;
import com.metron.xiaoming.util.SimpleTextWatcher;
import com.metron.xiaoming.util.ViewUtil;

import likly.dollar.$;

@MvpBinder(
)
public class RegistInputFragment extends BaseBackFragment<FragmentRegistInputBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_regist_input;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                if (!bindRoot.checkbox.isChecked()) {
                    $.toast().text("请先同意用户协议和隐私政策!").show();
                    break;
                }
                sendSms();
                break;
            case R.id.userAgreement:
                WebPageActivity.start(getContext(), "https://app.tokensky.cn/articles/zh_CN/agreement-zh_CN.html", "用户协议");
                break;
            case R.id.privacyPolicy:
                WebPageActivity.start(getContext(), "https://app.tokensky.cn/articles/zh_CN/agreement-zh_CN.html", "隐私政策");
                break;
        }
    }

    private void sendSms() {
        ApiUtil.apiService().sendSms("86", ViewUtil.getText(bindRoot.etPhone),
                new Callback<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", SetPwdFragment.TYPE_REGIST);
                        bundle.putString("mobile", ViewUtil.getText(bindRoot.etPhone));
                        navigate(R.id.action_regist_to_verify, bundle);
                    }
                });
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
                bindRoot.btnGetCode.setEnabled(!TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPhone)));
            }
        });

        bindRoot.userAgreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        bindRoot.privacyPolicy.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

}