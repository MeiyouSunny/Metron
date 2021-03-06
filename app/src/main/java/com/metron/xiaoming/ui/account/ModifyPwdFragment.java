package com.metron.xiaoming.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.util.UserDataUtil;
import com.meiyou.mvp.MvpBinder;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBackFragment;
import com.metron.xiaoming.databinding.FragmentModifyPwdBinding;
import com.metron.xiaoming.util.SimpleTextWatcher;
import com.metron.xiaoming.util.ViewUtil;

import java.util.Arrays;
import java.util.List;

import likly.dollar.$;

@MvpBinder(
)
public class ModifyPwdFragment extends BaseBackFragment<FragmentModifyPwdBinding> {

    private String phone;
    private List<EditText> mEtCodes;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_modify_pwd;
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
        ApiUtil.apiService().sendSms("86", phone,
                new Callback<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        $.toast().text("验证码已发送！");
                    }
                });
    }

    @Override
    protected void onTitleRightClick() {
        sendSms();
    }

    @Override
    protected String title() {
        return "修改密码";
    }

    @Override
    protected String rightTitle() {
        return "获取验证";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        phone = UserDataUtil.instance().getUserInfo().mobile;
        bindRoot.hint.setText(getString(R.string.modify_pwd_need_verify, phone));

        mEtCodes = Arrays.asList(new EditText[]{
                bindRoot.etCode1, bindRoot.etCode2, bindRoot.etCode3, bindRoot.etCode4, bindRoot.etCode5, bindRoot.etCode6
        });
        for (EditText etInput : mEtCodes) {
            etInput.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    onInputChange(etInput);
                }
            });
        }

        bindRoot.etCode1.requestFocus();
    }

    private void onInputChange(EditText etInput) {
        String input = ViewUtil.getText(etInput);
        boolean hasInput = !TextUtils.isEmpty(input);
        int index = mEtCodes.indexOf(etInput);
        proccessInput(index, hasInput);
    }

    private void proccessInput(int index, boolean hasInput) {
        if (index == 5 && hasInput) {
            verifyCode();
            return;
        }

        if (hasInput && index < 6) {
            index++;
            mEtCodes.get(index).requestFocus();
            return;
        }

        if (!hasInput && (index > 0 && index <= 6)) {
            index--;
            mEtCodes.get(index).requestFocus();
            return;
        }
    }

    boolean hasGoto;

    private void verifyCode() {
        if (hasGoto)
            return;

        boolean isModifyPhone = getActivity().getIntent().getBooleanExtra("isModifyPhone", false);
        if (isModifyPhone) {
            Intent intent = new Intent(getActivity(), SetProfileActivity.class);
            intent.putExtra("type", SetProfileActivity.PHONE);
            intent.putExtra("smsCode", getInputCode());
            startActivity(intent);
            getActivity().finish();
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("type", SetPwdFragment.TYPE_MODIFY_PWD);
            bundle.putString("mobile", phone);
            bundle.putString("smsCode", getInputCode());
            navigate(R.id.action_modifyPwd_to_setPwd, bundle);
        }
    }

    private String getInputCode() {
        String code = "";
        for (EditText editText : mEtCodes) {
            code += ViewUtil.getText(editText);
        }
        return code;
    }

}