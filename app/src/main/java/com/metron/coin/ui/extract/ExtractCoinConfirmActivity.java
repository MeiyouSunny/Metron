package com.metron.coin.ui.extract;

import android.text.TextUtils;
import android.view.View;

import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityExtractCoinConfirmBinding;
import com.metron.coin.util.CoinConst;

/**
 * 申请提币确认
 */
public class ExtractCoinConfirmActivity extends BaseTitleActivity<ActivityExtractCoinConfirmBinding> {
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
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        type = getIntent().getStringExtra("type");

        String coinAddress = getCoinAddress();
        bindRoot.setCoinAddress(coinAddress);

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

}