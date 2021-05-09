package com.metron.coin.ui.extract;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.WithdrawList;
import com.alaer.lib.util.UserDataUtil;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentExtractCoinBinding;
import com.metron.coin.ui.account.EncryptedWalletActivity;
import com.metron.coin.ui.dialog.DialogNoEncryptedWallet;
import com.metron.coin.util.CoinConst;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.util.ViewUtil;

import likly.dialogger.Dialogger;

/**
 * 申请提币Fragment
 */
public class ExtractFragment extends BaseBindFragment<FragmentExtractCoinBinding> implements DialogNoEncryptedWallet.OnSetWalletListener {

    private String type;

    public ExtractFragment(String type) {
        this.type = type;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_extract_coin;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (!hasCoinWalletAddress()) {
                    Dialogger.newDialog(getContext())
                            .holder(new DialogNoEncryptedWallet(this))
                            .gravity(Gravity.BOTTOM)
                            .show();
                } else {
                    ViewUtil.gotoActivity(getContext(), ExtractCoinConfirmActivity.class, "type", type);
                }
                break;
        }
    }

    // 是否已经设置了对应的钱包地址
    private boolean hasCoinWalletAddress() {
        if (!TextUtils.equals(type, CoinConst.BTC)) {
            return !TextUtils.isEmpty(UserDataUtil.instance().getUserInfo().btcWallet);
        }
        if (!TextUtils.equals(type, CoinConst.ETH)) {
            return !TextUtils.isEmpty(UserDataUtil.instance().getUserInfo().ethWallet);
        }

        return false;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        bindRoot.setType(type);

        ApiUtil.apiService().withdrawList(new String[]{"BTC"}, 100,
                new Callback<WithdrawList>() {
                    @Override
                    public void onResponse(WithdrawList withdrawList) {
                        if (withdrawList != null && !CollectionUtils.isEmpty(withdrawList.rows)) {
                            bindRoot.repeatView.viewManager().bind(withdrawList.rows);
                        }
                    }
                });
    }

    @Override
    public void onSetWallet() {
        ViewUtil.gotoActivity(getContext(), EncryptedWalletActivity.class);
    }

}