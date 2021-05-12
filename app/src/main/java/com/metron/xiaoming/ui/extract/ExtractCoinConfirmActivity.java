package com.metron.xiaoming.ui.extract;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityExtractCoinConfirmBinding;
import com.metron.xiaoming.util.CoinConst;
import com.metron.xiaoming.util.NumberUtils;
import com.metron.xiaoming.util.ViewUtil;

import likly.dollar.$;

/**
 * 申请提币确认
 */
public class ExtractCoinConfirmActivity extends BaseTitleActivity<ActivityExtractCoinConfirmBinding> {

    // 最小提币数量
    private static final float MIN_BTC = 0.005F;
    private static final float MIN_ETH = 0.2F;

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

        if (TextUtils.equals(type, CoinConst.BTC))
            bindRoot.hintBTC.setVisibility(View.VISIBLE);
        if (TextUtils.equals(type, CoinConst.ETH))
            bindRoot.hintETH.setVisibility(View.VISIBLE);
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
        if (TextUtils.equals(type, CoinConst.BTC) && inputValue < MIN_BTC)
            return true;
        if (TextUtils.equals(type, CoinConst.ETH) && inputValue < MIN_ETH)
            return true;

        return false;
    }

}