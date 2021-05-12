package com.metron.xiaoming.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.TotalAssets;
import com.alaer.lib.util.UserDataUtil;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.databinding.FragmentProfitBinding;
import com.metron.xiaoming.util.NumberUtils;
import com.metron.xiaoming.util.StringUtil;

/**
 * 收益
 */
public class ProfitFragment extends BaseBindFragment<FragmentProfitBinding> {
    private boolean moneyHide;
    private TotalAssets mTotalAssets;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_profit;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    protected void loadData() {
        setTabs();
        queryTotalAssets();
    }

    String[] mTabTitles = new String[]{"BTC", "ETH"};
    int[] mTabIcons = new int[]{R.drawable.ic_btc, R.drawable.ic_eth};

    private void setTabs() {
        bindRoot.viewPager.setOffscreenPageLimit(3);
        TabProfitAdapter adapter = new TabProfitAdapter(getContext(), getChildFragmentManager());
        bindRoot.viewPager.setAdapter(adapter);
        bindRoot.tabs.setupWithViewPager(bindRoot.viewPager);

        for (int i = 0; i < mTabTitles.length; i++) {
            bindRoot.tabs.getTabAt(i).setCustomView(makeTabView(i));
        }
    }

    private View makeTabView(int position) {
        View tabView = LayoutInflater.from(getContext()).inflate(R.layout.profit_tab_item, null);
        TextView textView = tabView.findViewById(R.id.text);
        ImageView imageView = tabView.findViewById(R.id.icon);
        textView.setText(mTabTitles[position]);
        imageView.setImageResource(mTabIcons[position]);

        return tabView;
    }

    private void queryTotalAssets() {
        ApiUtil.apiService().totalAssets(UserDataUtil.instance().getUserInfo().role, new Callback<TotalAssets>() {
            @Override
            public void onResponse(TotalAssets totalAssets) {
                mTotalAssets = totalAssets;
                bindRoot.setUtil(NumberUtils.instance());
                bindRoot.setTotalAssets(totalAssets);
            }
        });
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.moneyHide:
                if (mTotalAssets == null)
                    break;
                moneyHide = !moneyHide;
                String totalBTC = NumberUtils.instance().parseFloat8(mTotalAssets.totalBtc);
                String totalCNY = NumberUtils.instance().parseFloat8(mTotalAssets.totalCny);
                if (moneyHide) {
                    totalBTC = StringUtil.parseStringToStar(totalBTC);
                    totalCNY = "≈ " + StringUtil.parseStringToStar(totalCNY) + " CNY";
                } else {
                    totalCNY = "≈ " + totalCNY + " CNY";
                }
                bindRoot.labelTotalBTC.setText(totalBTC);
                bindRoot.labelTotalCNY.setText(totalCNY);
                break;
        }
    }

}