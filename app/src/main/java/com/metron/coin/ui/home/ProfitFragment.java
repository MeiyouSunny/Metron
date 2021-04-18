package com.metron.coin.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentProfitBinding;

/**
 * 收益
 */
public class ProfitFragment extends BaseBindFragment<FragmentProfitBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_profit;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        setTabs();

    }

    String[] mTabTitles = new String[]{"BTC", "ETH", "USDT"};
    int[] mTabIcons = new int[]{R.drawable.ic_btc, R.drawable.ic_eth, R.drawable.ic_usdt};

    private void setTabs() {
        bindRoot.viewPager.setOffscreenPageLimit(3);
        TabProfitAdapter adapter = new TabProfitAdapter(getContext(), getActivity().getSupportFragmentManager());
        bindRoot.viewPager.setAdapter(adapter);
        bindRoot.tabs.setupWithViewPager(bindRoot.viewPager);

//        for (int i = 0; i < mTabTitles.length; i++) {
//            bindRoot.tabs.addTab(bindRoot.tabs.newTab());
//        }

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

}