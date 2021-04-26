package com.metron.coin.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivitySetProfileBinding;

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
    public static final int SET_ADDRESS_USDT = 5;

    @Override
    protected String title() {
        return "";
    }

    @IntDef({NAME, PHONE, SET_ADDRESS_BTC, SET_ADDRESS_ETH, SET_ADDRESS_USDT})
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
        } else if (type == SET_ADDRESS_USDT) {
            setTitleText("USDT收款地址");
            bindRoot.input.setHint("USDT收款地址");
        }
    }

    @Override
    public void click(View view) {
    }

}