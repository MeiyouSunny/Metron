package com.metron.coin.ui.order;

import android.view.View;

import com.alaer.lib.api.bean.MinterSeries;
import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityOrdersBinding;
import com.metron.coin.ui.settings.AboutActivity;
import com.metron.coin.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

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

        List<MinterSeries.MinterInfo> list = new ArrayList<>();
        list.add(new MinterSeries.MinterInfo());
        list.add(new MinterSeries.MinterInfo());
        list.add(new MinterSeries.MinterInfo());
        list.add(new MinterSeries.MinterInfo());
        list.add(new MinterSeries.MinterInfo());
        bindRoot.repeatView.viewManager().bind(list);
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