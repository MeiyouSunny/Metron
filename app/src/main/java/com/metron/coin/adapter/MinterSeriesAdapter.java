package com.metron.coin.adapter;

import com.alaer.lib.api.bean.MinterSeries;
import com.metron.coin.R;
import com.metron.coin.base.repeatview.BaseViewHolder;
import com.metron.coin.data.MinterUtil;
import com.metron.coin.databinding.ItemMinterSeriesBinding;
import com.metron.coin.util.ViewUtil;

public class MinterSeriesAdapter extends BaseViewHolder<ItemMinterSeriesBinding, MinterSeries.MinterInfo> {

    @Override
    protected void onBindData(MinterSeries.MinterInfo minter) {
        bindRoot.setUtil(new MinterUtil());
        bindRoot.setMinter(minter);
        bindRoot.executePendingBindings();
        ViewUtil.showImage(getContext(), bindRoot.icon, minter.thumbnail);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_minter_series;
    }

}
