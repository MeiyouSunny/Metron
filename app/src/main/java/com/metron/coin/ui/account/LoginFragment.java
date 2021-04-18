package com.metron.coin.ui.account;

import android.view.View;

import com.meiyou.mvp.MvpBinder;
import com.metron.coin.R;
import com.metron.coin.base.BaseBackFragment;
import com.metron.coin.databinding.FragmentLoginBinding;

@MvpBinder(
)
public class LoginFragment extends BaseBackFragment<FragmentLoginBinding> {

    private String mPhone, mPwd;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.forgetPwd:
                navigate(R.id.action_to_forgetPwd);
                break;
            case R.id.btnLogin:
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