package com.metron.coin.ui.home;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.IncomeLastest;
import com.alaer.lib.api.bean.MinterList;
import com.alaer.lib.api.bean.MinterStats;
import com.alaer.lib.api.bean.PollNewInfo;
import com.alaer.lib.api.bean.WithdrawStats;
import com.metron.coin.R;
import com.metron.coin.adapter.MinterListAdapter;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.data.MinterUtil;
import com.metron.coin.databinding.FragmentMinterBtcBinding;
import com.metron.coin.ui.dialog.DialogMinterDetail;
import com.metron.coin.util.CoinConst;
import com.metron.coin.util.CollectionUtils;
import com.metron.coin.util.NumberUtils;
import com.metron.coin.view.FullyLinearLayoutManager;

import java.util.List;

import likly.dialogger.Dialogger;
import likly.view.repeat.OnHolderClickListener;

public class MinterTypeFragment extends BaseBindFragment<FragmentMinterBtcBinding>
        implements OnHolderClickListener<MinterListAdapter> {

    String type;

    public MinterTypeFragment(String type) {
        this.type = type;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_minter_btc;
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
        ApiUtil.apiService().workerStats(type,
                new Callback<List<MinterStats>>() {
                    @Override
                    public void onResponse(List<MinterStats> minterStatsList) {
                        if (minterStatsList != null) {
                            int[] values = new MinterUtil().parseMinterStatsValues(minterStatsList);
                            bindRoot.setMinterValues(values);
                        }
                    }
                });

        ApiUtil.apiService().pollNewInfo(new Callback<PollNewInfo>() {
            @Override
            public void onResponse(PollNewInfo pollNewInfo) {
                if (pollNewInfo != null) {
                    bindRoot.setMinterUtil(new MinterUtil());
                    bindRoot.setNumberUtil(NumberUtils.instance());
                    if (TextUtils.equals(type, CoinConst.BTC))
                        bindRoot.setCoin(pollNewInfo.BTC);
                    else if (TextUtils.equals(type, CoinConst.ETH))
                        bindRoot.setCoin(pollNewInfo.ETH);
                }
            }

            @Override
            public void onError(int code, String msg) {
                super.onError(code, msg);
            }
        });

        ApiUtil.apiService().withdrawStats(type, new Callback<WithdrawStats>() {
            @Override
            public void onResponse(WithdrawStats withdrawStats) {
                bindRoot.setWithdrawStats(withdrawStats);
            }
        });

        ApiUtil.apiService().incomeLatest(type, new Callback<IncomeLastest>() {
            @Override
            public void onResponse(IncomeLastest incomeLastest) {
                bindRoot.setIncomeLatest(incomeLastest);
            }
        });

        ApiUtil.apiService().workerList(type, 10, new Callback<MinterList>() {
            @Override
            public void onResponse(MinterList minterList) {
                super.onResponse(minterList);
                if (minterList != null && !CollectionUtils.isEmpty(minterList.rows)) {
                    bindRoot.repeatView.viewManager().bind(minterList.rows);

                    FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(getContext(), minterList.rows.size());
                    bindRoot.repeatView.getRecyclerView().setLayoutManager(layoutManager);
                    bindRoot.scrollView.scrollTo(0, 0);
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