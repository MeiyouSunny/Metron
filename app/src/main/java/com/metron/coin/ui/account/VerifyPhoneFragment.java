package com.metron.coin.ui.account;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentVerifyPhoneBinding;
import com.metron.coin.util.SimpleTextWatcher;
import com.metron.coin.util.ViewUtil;

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

        mEtCodes = Arrays.asList(new EditText[]{
                bindRoot.etCode1, bindRoot.etCode2, bindRoot.etCode3, bindRoot.etCode4
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
        if (index == 3 && hasInput) {
            verifyCode();
            return;
        }

        if (hasInput && index < 3) {
            index++;
            mEtCodes.get(index).requestFocus();
            return;
        }

        if (!hasInput && (index > 0 && index <= 3)) {
            index--;
            mEtCodes.get(index).requestFocus();
            return;
        }
    }

    boolean hasGoto;

    private void verifyCode() {
        if (hasGoto)
            return;
        navigate(R.id.action_to_setPwd);
    }

}