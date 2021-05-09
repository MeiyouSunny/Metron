package com.metron.coin.ui.home;

import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.OrderList;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentChannelOrdersBinding;

public class ChannelOrderFragment extends BaseBindFragment<FragmentChannelOrdersBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_channel_orders;
    }

    @Override
    public void click(View view) {
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        queryOrders();
    }

    private void queryOrders() {
        ApiUtil.apiService().orderList(100, new Callback<OrderList>() {
            @Override
            public void onResponse(OrderList orderList) {
                bindRoot.repeatView.viewManager().bind(orderList.rows);
            }
        });
    }

}