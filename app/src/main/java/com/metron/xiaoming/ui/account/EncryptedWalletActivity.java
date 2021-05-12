package com.metron.xiaoming.ui.account;

import android.view.View;

import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityEncrypedWalletBinding;

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
    public void onResume() {
        super.onResume();
        UserInfo userInfo = UserDataUtil.instance().getUserInfo();
        if (userInfo != null)
            bindRoot.setUserInfo(userInfo);
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
        }
    }

}