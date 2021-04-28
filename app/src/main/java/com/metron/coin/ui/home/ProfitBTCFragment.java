package com.metron.coin.ui.home;

import android.graphics.drawable.Drawable;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.IncomeLastest;
import com.alaer.lib.api.bean.IncomeList;
import com.alaer.lib.api.bean.WithdrawStats;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.data.IncomeUtil;
import com.metron.coin.databinding.FragmentProfitBtcBinding;
import com.metron.coin.util.CoinConst;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.util.NumberUtils;

import java.util.List;

/**
 * 收益BTC
 */
public class ProfitBTCFragment extends BaseBindFragment<FragmentProfitBtcBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_profit_btc;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.setNumberUtil(NumberUtils.instance());
        bindRoot.setIncomeUtil(new IncomeUtil());
        showData();
        requestData();
    }

    private void showData() {
        LineChartBean lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(getContext(), "line_chart.json", LineChartBean.class);
        List<IncomeBean> incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        //展示图表
        LineChartManager lineChartManager = new LineChartManager(bindRoot.lineChart);
        lineChartManager.showLineChart(incomeBeanList, "我的收益", getResources().getColor(R.color.green));
//        lineChartManager1.addLine(shanghai, "上证指数", getResources().getColor(R.color.orange));

        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_green);
        lineChartManager.setChartFillDrawable(drawable);
        lineChartManager.setMarkerView(getContext());
    }

    public void requestData() {
        ApiUtil.apiService().incomeLatest(CoinConst.BTC, new Callback<IncomeLastest>() {
            @Override
            public void onResponse(IncomeLastest incomeLastest) {
                bindRoot.setIncomeLatest(incomeLastest);
            }
        });

        ApiUtil.apiService().withdrawStats(CoinConst.BTC, new Callback<WithdrawStats>() {
            @Override
            public void onResponse(WithdrawStats withdrawStats) {
                bindRoot.setWithdrawStats(withdrawStats);
            }
        });

        ApiUtil.apiService().incomeList(CoinConst.BTC, new Callback<IncomeList>() {
            @Override
            public void onResponse(IncomeList incomeList) {
                if (incomeList != null && !CollectionUtils.isEmpty(incomeList.rows)) {
                    bindRoot.repeatView.viewManager().bind(incomeList.rows);
                }
            }
        });
    }

}