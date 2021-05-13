package com.metron.xiaoming.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alaer.lib.api.bean.OrderList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.repeatview.BaseViewHolder;
import com.metron.xiaoming.data.MinterUtil;
import com.metron.xiaoming.databinding.ItemOrderChannelBinding;
import com.metron.xiaoming.util.CollectionUtils;
import com.metron.xiaoming.util.ViewUtil;

public class OrderChannelAdapter extends BaseViewHolder<ItemOrderChannelBinding, OrderList.Order> {

    @Override
    protected void onBindData(OrderList.Order order) {
        bindRoot.setUtil(new MinterUtil());
        bindRoot.setOrder(order);
        bindRoot.executePendingBindings();

        showModelList(order, bindRoot.mintersLayout);
    }

    private void showModelList(OrderList.Order order, LinearLayout groupLayout) {
        if (CollectionUtils.isEmpty(order.quantity)) {
            groupLayout.removeAllViews();
            return;
        }

        for (OrderList.Quantity quantity : order.quantity) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_order_minter, null);
            ImageView thumb = view.findViewById(R.id.minterThumb);
            TextView name = view.findViewById(R.id.modeName);
            TextView info = view.findViewById(R.id.count);
            name.setText("小铭" + quantity.model.name);
            info.setText("¥" + quantity.model.price + " X " + quantity.quantity + "台");
            ViewUtil.showImage(getContext().getApplicationContext(), thumb, quantity.model.thumbnail);

            groupLayout.addView(view);
        }

    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_order_channel;
    }

}
