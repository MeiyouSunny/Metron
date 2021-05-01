package com.metron.coin.adapter;

import com.alaer.lib.api.bean.MinterList;
import com.metron.coin.R;
import com.metron.coin.base.repeatview.BaseViewHolder;
import com.metron.coin.data.MinterUtil;
import com.metron.coin.databinding.ItemMinterBinding;

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
