package com.metron.coin.ui.home;

import android.view.Gravity;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.MinterList;
import com.metron.coin.R;
import com.metron.coin.adapter.MinterListAdapter;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentMinterAllBinding;
import com.metron.coin.ui.dialog.DialogMinterDetail;
import com.metron.coin.util.CollectionUtils;

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
        ApiUtil.apiService().workerList("", new Callback<MinterList>() {
            @Override
            public void onResponse(MinterList minterList) {
                super.onResponse(minterList);
                if (minterList != null && !CollectionUtils.isEmpty(minterList.rows)) {
                    bindRoot.repeatView.viewManager().bind(minterList.rows);
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