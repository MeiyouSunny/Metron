package com.metron.xiaoming.ui.home;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.PollNewInfo;
import com.google.android.material.tabs.TabLayout;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.data.MinterUtil;
import com.metron.xiaoming.databinding.FragmentHomeBinding;
import com.metron.xiaoming.ui.dialog.DialogCustomerService;
import com.metron.xiaoming.ui.message.MessagesActivity;
import com.metron.xiaoming.util.CollectionUtils;
import com.metron.xiaoming.util.ViewUtil;

import java.util.List;

import likly.dialogger.Dialogger;

public class HomeFragment extends BaseBindFragment<FragmentHomeBinding> implements TabLayout.BaseOnTabSelectedListener {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.customerService:
                Dialogger.newDialog(getContext())
                        .holder(new DialogCustomerService())
                        .gravity(Gravity.BOTTOM)
                        .show();
                break;
            case R.id.messages:
                toPage(MessagesActivity.class);
                break;
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
                    bindRoot.setMinterUtil(new MinterUtil());
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
            ((TextView) tab.getCustomView().findViewById(R.id.text)).setText(getString(R.string.series_is, minterSeries.name));
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

        bindRoot.setMinterSeries(minterSeries);
        ViewUtil.showListData(bindRoot.repeatView, minterSeries.models);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onResume() {
        super.onResume();
        unreadMessage();
    }

    private void unreadMessage() {
        ApiUtil.apiService().messageUnread(new Callback<Integer>() {
            @Override
            public void onResponse(Integer unreadCount) {
                bindRoot.setUnreadMessage(unreadCount);
            }
        });
    }

}