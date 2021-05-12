package com.metron.xiaoming.adapter;

import com.alaer.lib.api.bean.MinterList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.repeatview.BaseViewHolder;
import com.metron.xiaoming.data.MinterUtil;
import com.metron.xiaoming.databinding.ItemMinterBinding;

/**
 * 我的矿机列表
 */
public class MinterListAdapter extends BaseViewHolder<ItemMinterBinding, MinterList.Minter> {

    @Override
    protected void onBindData(MinterList.Minter minter) {
        bindRoot.setMinterUtil(new MinterUtil());
        bindRoot.setMinter(minter);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_minter;
    }

}
