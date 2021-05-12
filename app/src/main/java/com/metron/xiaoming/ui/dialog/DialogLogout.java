package com.metron.xiaoming.ui.dialog;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseDialogHolder;
import com.metron.xiaoming.databinding.DialogLogoutBinding;

/**
 * 退出登录
 */
public class DialogLogout extends BaseDialogHolder<DialogLogoutBinding> {

    private OnExitListener mListener;

    public DialogLogout(OnExitListener listener) {
        super(R.layout.dialog_logout);
        mListener = listener;
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.exit:
                if (mListener != null)
                    mListener.onExit();
                dismiss();
                break;
        }
    }

    public interface OnExitListener {
        void onExit();
    }

}
