package com.metron.coin.ui.extract;

import android.view.Gravity;
import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentProfitBtcBinding;
import com.metron.coin.ui.dialog.DialogNoEncryptedWallet;

import likly.dialogger.Dialogger;

/**
 * 申请提币Fragment
 */
public class ExtractFragment extends BaseBindFragment<FragmentProfitBtcBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_extract_coin;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.submit:
                Dialogger.newDialog(getContext())
                        .holder(new DialogNoEncryptedWallet())
                        .gravity(Gravity.BOTTOM)
                        .show();
                break;
        }
    }

}