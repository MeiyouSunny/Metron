package com.metron.coin.ui.home;

import android.graphics.drawable.Drawable;

import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentProfitBtcBinding;

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
        showData();
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

}