package com.metron.coin.ui.account;

import android.view.View;

import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentForgetPwdBinding;

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
                navigate(R.id.action_forgetPwd_to_verifyPhone);
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
//        bindRoot.region.setText("+" + AppConfig.DIALLING_CODE);

//        bindRoot.etPhone.addTextChangedListener(new SimpleTextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onInputChange();
//            }
//        });
//        bindRoot.etPwd.addTextChangedListener(new SimpleTextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onInputChange();
//            }
//        });
    }

//    private void onInputChange() {
//        mPhone = ViewUtil.getText(bindRoot.etPhone);
//        mPwd = ViewUtil.getText(bindRoot.etPwd);
//        boolean hasInput = !TextUtils.isEmpty(mPhone) && !TextUtils.isEmpty(mPwd);
//        bindRoot.btnLogin.setEnabled(hasInput);
//    }

}