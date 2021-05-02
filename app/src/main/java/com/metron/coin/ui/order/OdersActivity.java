package com.metron.coin.ui.order;

import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.OrderList;
import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityOrdersBinding;
import com.metron.coin.ui.settings.AboutActivity;
import com.metron.coin.util.ViewUtil;

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
                bindRoot.repeatView.viewManager().bind(orderList.rows);
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