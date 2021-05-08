package com.metron.coin.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivitySetProfileBinding;
import com.metron.coin.util.SimpleTextWatcher;
import com.metron.coin.util.ViewUtil;

import androidx.annotation.IntDef;

/**
 * 修改用户属性
 */
public class SetProfileActivity extends BaseTitleActivity<ActivitySetProfileBinding> {

    public static final int REQUST_CODE = 1;

    public static final int NAME = 1;
    public static final int PHONE = 2;
    public static final int SET_ADDRESS_BTC = 3;
    public static final int SET_ADDRESS_ETH = 4;

    @Override
    protected String title() {
        return "";
    }

    @IntDef({NAME, PHONE, SET_ADDRESS_BTC, SET_ADDRESS_ETH})
    @interface TYPE {
    }

    int type;
    String key;

    public static void start(Activity context, @TYPE int type) {
        Intent intent = new Intent(context, SetProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, REQUST_CODE);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_set_profile;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        type = getIntent().getIntExtra("type", 0);

        bindRoot.input.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                bindRoot.btnSubmit.setEnabled(!TextUtils.isEmpty(ViewUtil.getText(bindRoot.input)));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == NAME) {
            setTitleText("用户名");
            bindRoot.input.setHint("");
        } else if (type == PHONE) {
            setTitleText("手机号");
            bindRoot.input.setHint("");
        } else if (type == SET_ADDRESS_BTC) {
            setTitleText("BTC收款地址");
            bindRoot.input.setHint("BTC收款地址");
        } else if (type == SET_ADDRESS_ETH) {
            setTitleText("ETH收款地址");
            bindRoot.input.setHint("ETH收款地址");
        }
    }

    @Override
    public void click(View view) {
        if (view.getId() == R.id.btnSubmit) {
            modify();
        }
    }

    private void modify() {
        String input = ViewUtil.getText(bindRoot.input);
        if (type == NAME) {
            modifyNick(input);
        } else if (type == PHONE) {
            modifyPhone(input);
        } else if (type == SET_ADDRESS_BTC) {
            modifyBtc(input);
        } else if (type == SET_ADDRESS_ETH) {
            modifyEth(input);
        }
    }

    private void modifyNick(String input) {
        ApiUtil.apiService().modifyNick(input, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                UserDataUtil.instance().getUserInfo().nick = input;
                finish();
            }
        });
    }

    private void modifyPhone(String input) {
        String smsCode = getIntent().getStringExtra("smsCode");
        ApiUtil.apiService().modifyPhone(input, smsCode, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                UserDataUtil.instance().getUserInfo().mobile = input;
                finish();
            }
        });
    }

    private void modifyBtc(String input) {
        ApiUtil.apiService().modifyBtcWallet(input, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                UserDataUtil.instance().getUserInfo().btcWallet = input;
                finish();
            }
        });
    }

    private void modifyEth(String input) {
        ApiUtil.apiService().modifyEthWallet(input, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                UserDataUtil.instance().getUserInfo().ethWallet = input;
                finish();
            }
        });
    }

}