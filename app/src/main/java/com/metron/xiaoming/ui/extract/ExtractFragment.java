package com.metron.xiaoming.ui.extract;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.WithdrawList;
import com.alaer.lib.api.bean.WithdrawStats;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.data.IncomeUtil;
import com.metron.xiaoming.databinding.FragmentExtractCoinBinding;
import com.metron.xiaoming.ui.account.EncryptedWalletActivity;
import com.metron.xiaoming.ui.dialog.DialogNoEncryptedWallet;
import com.metron.xiaoming.util.CoinConst;
import com.metron.xiaoming.util.CollectionUtils;
import com.metron.xiaoming.util.NumberUtils;
import com.metron.xiaoming.util.ViewUtil;

import likly.dialogger.Dialogger;

/**
 * 申请提币Fragment
 */
public class ExtractFragment extends BaseBindFragment<FragmentExtractCoinBinding> implements DialogNoEncryptedWallet.OnSetWalletListener {

    private String type;
    private float[] withdrawValues;

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
                    Intent intent = new Intent(getContext(), ExtractCoinConfirmActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("balance", withdrawValues[3]);
                    getContext().startActivity(intent);
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

        ApiUtil.apiService().withdrawStats(type, new Callback<WithdrawStats>() {
            @Override
            public void onResponse(WithdrawStats withdrawStats) {
                withdrawValues = new IncomeUtil().parseWithdrawValues(withdrawStats);
                bindRoot.setUtil(NumberUtils.instance());
                bindRoot.setValues(withdrawValues);
            }
        });

        ApiUtil.apiService().withdrawList(type, 200,
                new Callback<WithdrawList>() {
                    @Override
                    public void onResponse(WithdrawList withdrawList) {
                        if (withdrawList != null && !CollectionUtils.isEmpty(withdrawList.rows)) {
                            ViewUtil.showListData(bindRoot.repeatView, withdrawList.rows);
                        }
                    }
                });
    }

    @Override
    public void onSetWallet() {
        ViewUtil.gotoActivity(getContext(), EncryptedWalletActivity.class);
    }

}