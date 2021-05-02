package com.metron.coin.adapter;

import com.alaer.lib.api.bean.OrderList;
import com.metron.coin.R;
import com.metron.coin.base.repeatview.BaseViewHolder;
import com.metron.coin.data.MinterUtil;
import com.metron.coin.databinding.ItemOrderBinding;

public class OrderAdapter extends BaseViewHolder<ItemOrderBinding, OrderList.Order> {

    @Override
    protected void onBindData(OrderList.Order order) {
        bindRoot.setUtil(new MinterUtil());
        bindRoot.setOrder(order);
        bindRoot.executePendingBindings();
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_order;
    }

}
