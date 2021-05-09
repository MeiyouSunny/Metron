package com.metron.coin.ui.dialog;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseDialogHolder;
import com.metron.coin.databinding.DialogNoEncryptedWalletBinding;

/**
 * 未设置加密钱包
 */
public class DialogNoEncryptedWallet extends BaseDialogHolder<DialogNoEncryptedWalletBinding> {

    private OnSetWalletListener mListener;

    public DialogNoEncryptedWallet(OnSetWalletListener listener) {
        super(R.layout.dialog_no_encrypted_wallet);
        mListener = listener;
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.goSet:
                if (mListener != null)
                    mListener.onSetWallet();
                dismiss();
                break;
        }
    }

    public interface OnSetWalletListener {
        void onSetWallet();
    }

}
