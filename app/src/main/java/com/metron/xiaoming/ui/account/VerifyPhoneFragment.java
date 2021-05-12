package com.metron.xiaoming.ui.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.meiyou.mvp.MvpBinder;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBackFragment;
import com.metron.xiaoming.databinding.FragmentVerifyPhoneBinding;
import com.metron.xiaoming.util.SimpleTextWatcher;
import com.metron.xiaoming.util.ViewUtil;

import java.util.Arrays;
import java.util.List;

@MvpBinder(
)
public class VerifyPhoneFragment extends BaseBackFragment<FragmentVerifyPhoneBinding> {

    private List<EditText> mEtCodes;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_verify_phone;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.toRegist:
//                navigate(R.id.action_to_regisPhoneVerify);
//                break;
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

        String phone = getArguments().getString("mobile");
        bindRoot.tvPhone.setText(phone);

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
        Bundle bundle = getArguments();
        bundle.putString("smsCode", getInputCode());
        navigate(R.id.action_to_setPwd, bundle);
    }

    private String getInputCode() {
        String code = "";
        for (EditText editText : mEtCodes) {
            code += ViewUtil.getText(editText);
        }
        return code;
    }

}