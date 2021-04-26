package com.metron.coin.ui.account;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityEncrypedWalletBinding;

/**
 * 加密钱包
 */
public class EncryptedWalletActivity extends BaseTitleActivity<ActivityEncrypedWalletBinding> {

    @Override
    protected String title() {
        return "加密钱包";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_encryped_wallet;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.setAddressBTC:
                SetProfileActivity.start(this, SetProfileActivity.SET_ADDRESS_BTC);
                break;
            case R.id.setAddressETH:
                SetProfileActivity.start(this, SetProfileActivity.SET_ADDRESS_ETH);
                break;
            case R.id.setAddressUSDT:
                SetProfileActivity.start(this, SetProfileActivity.SET_ADDRESS_USDT);
                break;
        }
    }

}