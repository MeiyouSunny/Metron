package com.metron.coin.ui.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentRegistInputBinding;
import com.metron.coin.util.SimpleTextWatcher;
import com.metron.coin.util.ViewUtil;

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
                sendSms();
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
    }

}