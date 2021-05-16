package com.metron.xiaoming.ui.home;

import android.view.Gravity;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.MinterList;
import com.alaer.lib.api.bean.WorkerStats;
import com.metron.xiaoming.R;
import com.metron.xiaoming.adapter.MinterListAdapter;
import com.metron.xiaoming.base.BaseBindFragment;
import com.metron.xiaoming.databinding.FragmentMinterAllBinding;
import com.metron.xiaoming.ui.dialog.DialogMinterDetail;
import com.metron.xiaoming.util.ViewUtil;

import likly.dialogger.Dialogger;
import likly.view.repeat.OnHolderClickListener;

public class MinterAllFragment extends BaseBindFragment<FragmentMinterAllBinding> implements OnHolderClickListener<MinterListAdapter> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_minter_all;
    }

    @Override
    public void click(View view) {
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        bindRoot.repeatView.onClick(this);
        queryMinters();
    }

    private void queryMinters() {
        ApiUtil.apiService().workerList("", 200, new Callback<MinterList>() {
            @Override
            public void onResponse(MinterList minterList) {
                super.onResponse(minterList);
                if (minterList != null) {
                    ViewUtil.showListData(bindRoot.repeatView, minterList.rows);
                } else {
                    bindRoot.repeatView.layoutAdapterManager().showEmptyView();
                }
            }
        });

        ApiUtil.apiService().workerAllStats(new Callback<WorkerStats>() {
            @Override
            public void onResponse(WorkerStats workerStats) {
                if (workerStats != null) {
                    bindRoot.setWorkerStats(workerStats);
                }
            }
        });
    }

    @Override
    public void onHolderClick(MinterListAdapter adapter) {
        MinterList.Minter minter = adapter.getData();
        Dialogger.newDialog(getContext())
                .holder(new DialogMinterDetail(minter))
                .gravity(Gravity.CENTER)
                .show();
    }
}