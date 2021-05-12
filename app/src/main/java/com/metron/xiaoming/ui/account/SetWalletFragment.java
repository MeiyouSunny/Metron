package com.metron.xiaoming.ui.account;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.util.UserDataUtil;
import com.meiyou.mvp.MvpBinder;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBackFragment;
import com.metron.xiaoming.databinding.FragmentSetWalletBinding;
import com.metron.xiaoming.ui.home.HomeActivity;
import com.metron.xiaoming.util.SimpleTextWatcher;
import com.metron.xiaoming.util.ViewUtil;

import likly.dollar.$;

@MvpBinder(
)
public class SetWalletFragment extends BaseBackFragment<FragmentSetWalletBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_set_wallet;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.skip:
                goHome();
                break;
            case R.id.btnSubmit:
                submit();
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
        bindRoot.etETH.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });
        bindRoot.etBTC.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onInputChange();
            }
        });
    }

    private void onInputChange() {
        boolean hasInput = !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etETH))
                || !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etBTC));
        bindRoot.btnSubmit.setEnabled(hasInput);
    }

    private void submit() {
        if (!TextUtils.isEmpty(ViewUtil.getText(bindRoot.etETH))
                && !TextUtils.isEmpty(ViewUtil.getText(bindRoot.etBTC))) {
            ApiUtil.apiService().modifyBtcAndEthWallet(ViewUtil.getText(bindRoot.etBTC), ViewUtil.getText(bindRoot.etETH),
                    new Callback<String>() {
                        @Override
                        public void onResponse(String response) {
                            $.toast().text("设置成功!").show();
                            UserDataUtil.instance().getUserInfo().btcWallet = ViewUtil.getText(bindRoot.etBTC);
                            UserDataUtil.instance().getUserInfo().ethWallet = ViewUtil.getText(bindRoot.etETH);
                            goHome();
                        }
                    });
            return;
        }

        if (!TextUtils.isEmpty(ViewUtil.getText(bindRoot.etBTC))) {
            ApiUtil.apiService().modifyBtcWallet(ViewUtil.getText(bindRoot.etBTC),
                    new Callback<String>() {
                        @Override
                        public void onResponse(String response) {
                            $.toast().text("设置成功!").show();
                            UserDataUtil.instance().getUserInfo().btcWallet = ViewUtil.getText(bindRoot.etBTC);
                            goHome();
                        }
                    });
            return;
        }

        if (!TextUtils.isEmpty(ViewUtil.getText(bindRoot.etETH))) {
            ApiUtil.apiService().modifyEthWallet(ViewUtil.getText(bindRoot.etETH),
                    new Callback<String>() {
                        @Override
                        public void onResponse(String response) {
                            $.toast().text("设置成功!").show();
                            UserDataUtil.instance().getUserInfo().ethWallet = ViewUtil.getText(bindRoot.etETH);
                            goHome();
                        }
                    });
        }
    }

    private void goHome() {
        ViewUtil.gotoActivity(getContext(), HomeActivity.class);
        getActivity().finish();
    }

}