package com.metron.coin.ui.account;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentSetPwdBinding;
import com.metron.coin.util.SimpleTextWatcher;
import com.metron.coin.util.ViewUtil;

import likly.dollar.$;

@MvpBinder(
)
public class SetPwdFragment extends BaseBackFragment<FragmentSetPwdBinding> {

    public final static int TYPE_REGIST = 1;
    public final static int TYPE_FORGET_PWD = 2;
    public final static int TYPE_MODIFY_PWD = 3;
    private String mobile;
    private String smsCode;
    private String password;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_set_pwd;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (!TextUtils.equals(ViewUtil.getText(bindRoot.etPwd), ViewUtil.getText(bindRoot.etPwdConfirm))) {
                    $.toast().text("两次输入的密码不一致").show();
                    return;
                }

                mobile = getArguments().getString("mobile");
                smsCode = getArguments().getString("smsCode");
                password = ViewUtil.getText(bindRoot.etPwd);

                int type = getArguments().getInt("type");
                if (type == TYPE_REGIST) {
                    regist();
                } else if (type == TYPE_FORGET_PWD) {
                    resetPwd();
                } else if (type == TYPE_MODIFY_PWD) {
                    modifyPwd();
                }

                break;
        }
    }

    @Override
    protected String rightTitle() {
        return "";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        bindRoot.etPwd.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });
        bindRoot.etPwdConfirm.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });
    }

    private void onInputChange() {
        boolean hasInput = !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPwd))
                && !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etPwdConfirm));
        bindRoot.btnSubmit.setEnabled(hasInput);
    }

    private void regist() {
        ApiUtil.apiService().signup(mobile, password, smsCode,
                new Callback<String>() {
                    @Override
                    public void onResponse(String response) {
                        $.toast().text("注册成功！").show();
                        navigate(R.id.action_setPwd_to_login);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        $.toast().text(msg).show();
                    }
                });
    }

    private void resetPwd() {
        ApiUtil.apiService().resetPwd(mobile, password, smsCode,
                new Callback<String>() {
                    @Override
                    public void onResponse(String response) {
                        $.toast().text("密码重置成功！").show();
                        navigate(R.id.action_setPwd_to_login);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        $.toast().text(msg).show();
                    }
                });
    }

    private void modifyPwd() {
        ApiUtil.apiService().modifyPwd(mobile, password, smsCode,
                new Callback<String>() {
                    @Override
                    public void onResponse(String response) {
                        $.toast().text("密码修改成功！").show();
                        ViewUtil.gotoActivity(getContext(), LoginActivity.class);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        $.toast().text(msg).show();
                    }
                });
    }

}