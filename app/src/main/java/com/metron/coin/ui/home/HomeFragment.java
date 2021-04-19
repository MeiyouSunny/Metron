package com.metron.coin.ui.home;

import android.view.View;
import android.widget.TextView;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.PollNewInfo;
import com.google.android.material.tabs.TabLayout;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.data.MinterUtil;
import com.metron.coin.databinding.FragmentHomeBinding;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.view.FullyLinearLayoutManager;

import java.util.List;

public class HomeFragment extends BaseBindFragment<FragmentHomeBinding> implements TabLayout.BaseOnTabSelectedListener {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.settings:
//                break;
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        setTabEvent();

        bindRoot.setMinterUtil(new MinterUtil());
        queryPollNewInfo();
        queryMinterSeries();
    }

    private void setTabEvent() {
        bindRoot.tabs.addOnTabSelectedListener(this);
    }

    private void queryPollNewInfo() {
        ApiUtil.apiService().pollNewInfo(new Callback<PollNewInfo>() {
            @Override
            public void onResponse(PollNewInfo pollNewInfo) {
                if (pollNewInfo != null) {
//                    bindRoot.setMinterUtil(new MinterUtil());
                    bindRoot.setPollNewInfo(pollNewInfo);
                }
            }

            @Override
            public void onError(int code, String msg) {
                super.onError(code, msg);
            }
        });
    }

    private void queryMinterSeries() {
        ApiUtil.apiService().getMinterSeries(new Callback<List<MinterSeries>>() {
            @Override
            public void onResponse(List<MinterSeries> response) {
                showMinterSeries(response);
            }

            @Override
            public void onError(int code, String msg) {
                super.onError(code, msg);
            }
        });
    }

    private void showMinterSeries(List<MinterSeries> series) {
        if (CollectionUtils.isEmpty(series)) {

            return;
        }

        for (MinterSeries minterSeries : series) {
            TabLayout.Tab tab = bindRoot.tabs.newTab();
            bindRoot.tabs.addTab(tab);
            tab.setCustomView(R.layout.home_minter_series_tab_item);
            ((TextView) tab.getCustomView().findViewById(R.id.text)).setText(minterSeries.name);
            tab.setTag(minterSeries);
        }

        showMinterList(series.get(0));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        MinterSeries minterSeries = (MinterSeries) tab.getTag();
        showMinterList(minterSeries);
    }

    private void showMinterList(MinterSeries minterSeries) {
        if (minterSeries == null || CollectionUtils.isEmpty(minterSeries.models)) {
            return;
        }

//        bindRoot.setMinterUtil(new MinterUtil());
        bindRoot.setMinterSeries(minterSeries);

//        bindRoot.repeatView.getRecyclerView().setPaddingRelative(0, 30, 0, 30);
//        bindRoot.repeatView.getRecyclerView().setClipToPadding(false);

        if (CollectionUtils.isEmpty(minterSeries.models))
            bindRoot.repeatView.layoutAdapterManager().showEmptyView();
        else
            bindRoot.repeatView.viewManager().bind(minterSeries.models);

        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(getContext(), minterSeries.models.size());
        bindRoot.repeatView.getRecyclerView().setLayoutManager(layoutManager);
        bindRoot.scrollView.scrollTo(0, 0);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

}