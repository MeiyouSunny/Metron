package com.metron.coin.adapter;

import com.alaer.lib.api.bean.WithdrawList;
import com.metron.coin.R;
import com.metron.coin.base.repeatview.BaseViewHolder;
import com.metron.coin.data.IncomeUtil;
import com.metron.coin.databinding.ItemWithdrawBinding;
import com.metron.coin.util.NumberUtils;

/**
 * 提币记录列表
 */
public class WithdrawListAdapter extends BaseViewHolder<ItemWithdrawBinding, WithdrawList.Withdraw> {

    private String[] statusList = new String[]{"转账中", "已完成", "交易失败"};

    @Override
    protected void onBindData(WithdrawList.Withdraw withdraw) {
        bindRoot.setIncomeUtil(new IncomeUtil());
        bindRoot.setNumberUtil(NumberUtils.instance());
        bindRoot.setStatusList(statusList);
        bindRoot.setWithdraw(withdraw);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_withdraw;
    }

}
