package com.metron.xiaoming.ui.home;

import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.OrderList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.databinding.FragmentChannelOrdersBinding;
import com.metron.xiaoming.util.ViewUtil;

public class OrderChannelFragment extends BaseBindFragment<FragmentChannelOrdersBinding> {

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
    }

    @Override
    protected void loadData() {
        queryOrders();
    }

    private void queryOrders() {
        ApiUtil.apiService().orderList(2, 100, new Callback<OrderList>() {
            @Override
            public void onResponse(OrderList orderList) {
                ViewUtil.showListData(bindRoot.repeatView, orderList.rows);
            }
        });
    }

}