package com.metron.coin.ui.extract;

import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.WithdrawList;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentExtractCoinBinding;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.util.ViewUtil;

/**
 * 申请提币Fragment
 */
public class ExtractFragment extends BaseBindFragment<FragmentExtractCoinBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_extract_coin;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.submit:
//                Dialogger.newDialog(getContext())
//                        .holder(new DialogNoEncryptedWallet())
//                        .gravity(Gravity.BOTTOM)
//                        .show();

                ViewUtil.gotoActivity(getContext(), ExtractCoinConfirmActivity.class);
                break;
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

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

}