package com.metron.xiaoming.ui.extract;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.ExtraConfig;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityExtractCoinConfirmBinding;
import com.metron.xiaoming.util.CoinConst;
import com.metron.xiaoming.util.CollectionUtils;
import com.metron.xiaoming.util.NumberUtils;
import com.metron.xiaoming.util.ViewUtil;

import java.util.List;

import likly.dollar.$;

/**
 * 申请提币确认
 */
public class ExtractCoinConfirmActivity extends BaseTitleActivity<ActivityExtractCoinConfirmBinding> {

    // 最小提币数量/手续费
    private float[] mConfigValues = new float[2];
    private String type;

    @Override
    protected int layoutId() {
        return R.layout.activity_extract_coin_confirm;
    }

    @Override
    protected String title() {
        return "申请提币";
    }

    @Override
    public void click(View view) {
        if (view.getId() == R.id.btnSubmit) {
            extraCoin();
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        type = getIntent().getStringExtra("type");
        bindRoot.setType(type);
        String coinAddress = getCoinAddress();
        bindRoot.setCoinAddress(coinAddress);
        float balance = getIntent().getFloatExtra("balance", 0F);
        bindRoot.setBalance(NumberUtils.instance().parseFloat8(balance));

        queryConfig();
    }

    private void queryConfig() {
        ApiUtil.apiService().extraConfig(new Callback<List<ExtraConfig>>() {
            @Override
            public void onResponse(List<ExtraConfig> response) {
                if (!CollectionUtils.isEmpty(response)) {
                    for (ExtraConfig extraConfig : response) {
                        if (TextUtils.equals(extraConfig.name, type)) {
                            if (TextUtils.equals(extraConfig.type, "minWithdraw")) {
                                mConfigValues[0] = Float.parseFloat(extraConfig.value);
                            } else if (TextUtils.equals(extraConfig.type, "feeRate")) {
                                mConfigValues[1] = Float.parseFloat(extraConfig.value);
                            }
                        }
                    }
                }

                bindRoot.hintBTC.setText(getString(R.string.extra_config_is, mConfigValues[0], mConfigValues[1]));
            }

            ;
        });
    }

    private String getCoinAddress() {
        if (!TextUtils.equals(type, CoinConst.BTC)) {
            return UserDataUtil.instance().getUserInfo().btcWallet;
        }
        if (!TextUtils.equals(type, CoinConst.ETH)) {
            return UserDataUtil.instance().getUserInfo().ethWallet;
        }
        return "";
    }

    private void extraCoin() {
        try {
            String input = ViewUtil.getText(bindRoot.input);
            float amount = Float.parseFloat(input);
            if (inputTooSmall(amount)) {
                $.toast().text("输入不能小于起提数量!").show();
                return;
            }

            ApiUtil.apiService().withdrawApply(UserDataUtil.instance().getUserInfo().role, type, amount,
                    new Callback<String>() {
                        @Override
                        public void onResponse(String response) {
                            $.toast().text("提币申请成功,等待处理!").show();
                            finish();
                        }

                        @Override
                        public void onError(int code, String msg) {
                            $.toast().text(msg).show();
                        }
                    });

        } catch (NumberFormatException e) {
            e.printStackTrace();
            $.toast().text("请输入有效的数量!").show();
        }

    }

    private boolean inputTooSmall(float inputValue) {
        if (inputValue <= 0)
            return true;
        if (mConfigValues.length >= 1 && inputValue < mConfigValues[0])
            return true;

        return false;
    }

}