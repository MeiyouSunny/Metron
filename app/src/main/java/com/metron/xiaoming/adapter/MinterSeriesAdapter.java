package com.metron.xiaoming.adapter;

import com.alaer.lib.api.bean.MinterSeries;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.repeatview.BaseViewHolder;
import com.metron.xiaoming.data.MinterUtil;
import com.metron.xiaoming.databinding.ItemMinterSeriesBinding;
import com.metron.xiaoming.util.ViewUtil;

public class MinterSeriesAdapter extends BaseViewHolder<ItemMinterSeriesBinding, MinterSeries.MinterInfo> {

    @Override
    protected void onBindData(MinterSeries.MinterInfo minter) {
        bindRoot.setUtil(new MinterUtil());
        bindRoot.setMinter(minter);
        bindRoot.executePendingBindings();
        ViewUtil.showImage(getContext(), bindRoot.thumb, minter.thumbnail);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_minter_series;
    }

}
