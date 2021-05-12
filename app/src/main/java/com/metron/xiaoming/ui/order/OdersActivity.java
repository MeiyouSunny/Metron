package com.metron.xiaoming.ui.order;

import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.OrderList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityOrdersBinding;
import com.metron.xiaoming.ui.settings.AboutActivity;
import com.metron.xiaoming.util.ViewUtil;

/**
 * 订单列表
 */
public class OdersActivity extends BaseTitleActivity<ActivityOrdersBinding> {

    @Override
    protected String title() {
        return "订单";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_orders;
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
                ViewUtil.showListData(bindRoot.repeatView, orderList.rows);
            }
        });
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.about:
                ViewUtil.gotoActivity(this, AboutActivity.class);
                break;
        }
    }

}