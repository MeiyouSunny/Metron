package com.metron.coin.ui.home;

import android.graphics.drawable.Drawable;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.IncomeLastest;
import com.alaer.lib.api.bean.IncomeList;
import com.alaer.lib.api.bean.IncomeTrend;
import com.alaer.lib.api.bean.WithdrawStats;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.data.IncomeUtil;
import com.metron.coin.databinding.FragmentProfitBtcBinding;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.util.NumberUtils;
import com.metron.coin.util.ViewUtil;

import java.util.List;

/**
 * 收益BTC
 */
public class ProfitTypeFragment extends BaseBindFragment<FragmentProfitBtcBinding> {
    private String type;

    public ProfitTypeFragment(String type) {
        this.type = type;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_profit_btc;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.setType(type);
        bindRoot.setNumberUtil(NumberUtils.instance());
        bindRoot.setIncomeUtil(new IncomeUtil());
        requestData();
    }

    public void requestData() {
        ApiUtil.apiService().incomeLatest(type, new Callback<IncomeLastest>() {
            @Override
            public void onResponse(IncomeLastest incomeLastest) {
                bindRoot.setIncomeLatest(incomeLastest);
            }
        });

        ApiUtil.apiService().withdrawStats(type, new Callback<WithdrawStats>() {
            @Override
            public void onResponse(WithdrawStats withdrawStats) {
                bindRoot.setWithdrawStats(withdrawStats);
            }
        });

        ApiUtil.apiService().incomeList(type, new Callback<IncomeList>() {
            @Override
            public void onResponse(IncomeList incomeList) {
                if (incomeList != null && !CollectionUtils.isEmpty(incomeList.rows)) {
                    ViewUtil.showListData(bindRoot.repeatView, incomeList.rows);
                }
            }
        });

        ApiUtil.apiService().incomeTrend(type, "d", new Callback<IncomeTrend>() {
            @Override
            public void onResponse(IncomeTrend incomeTrend) {
                if (incomeTrend != null && !CollectionUtils.isEmpty(incomeTrend.tickers)) {
                    showChart(incomeTrend.tickers);
                }
            }
        });
    }

    private void showChart(List<IncomeTrend.Ticker> tickers) {
        //展示图表
        LineChartManager lineChartManager = new LineChartManager(bindRoot.lineChart);
        lineChartManager.showLineChart(tickers, "我的收益", getResources().getColor(R.color.green));
//        lineChartManager1.addLine(shanghai, "上证指数", getResources().getColor(R.color.orange));

        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_green);
        lineChartManager.setChartFillDrawable(drawable);
        lineChartManager.setMarkerView(getContext());
    }

}