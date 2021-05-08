package com.metron.coin.ui.home;

import android.view.View;

import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentMineBinding;
import com.metron.coin.ui.account.AccountInfoActivity;
import com.metron.coin.ui.account.EncryptedWalletActivity;
import com.metron.coin.ui.extract.ExtractCoinActivity;
import com.metron.coin.ui.extract.ExtractMoneyActivity;
import com.metron.coin.ui.order.OdersActivity;
import com.metron.coin.ui.settings.SettingActivity;

public class MineFragment extends BaseBindFragment<FragmentMineBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.accountInfo:
                toPage(AccountInfoActivity.class);
                break;
            case R.id.settings:
                toPage(SettingActivity.class);
                break;
            case R.id.extractCoin:
                toPage(ExtractCoinActivity.class);
                break;
            case R.id.extractMoney:
                toPage(ExtractMoneyActivity.class);
                break;
            case R.id.encryptedWallet:
                toPage(EncryptedWalletActivity.class);
                break;
            case R.id.orders:
                toPage(OdersActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo userInfo = UserDataUtil.instance().getUserInfo();
        if (userInfo != null)
            bindRoot.setUserInfo(userInfo);
        bindRoot.setIsChannelUser(UserDataUtil.isChannelUser());
    }

}