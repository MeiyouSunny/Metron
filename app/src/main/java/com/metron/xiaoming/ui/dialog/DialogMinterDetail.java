package com.metron.xiaoming.ui.dialog;

import android.view.View;

import com.alaer.lib.api.bean.MinterList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseDialogHolder;
import com.metron.xiaoming.data.MinterUtil;
import com.metron.xiaoming.databinding.DialogMinterDetailBinding;

/**
 * 我的矿机详情
 */
public class DialogMinterDetail extends BaseDialogHolder<DialogMinterDetailBinding> {
    MinterList.Minter minter;

    public DialogMinterDetail(MinterList.Minter minter) {
        super(R.layout.dialog_minter_detail);
        this.minter = minter;
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
        bindRoot.setUtil(new MinterUtil());
        bindRoot.setMinter(minter);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }

}
