package com.metron.coin.adapter;

import com.alaer.lib.api.bean.IncomeList;
import com.metron.coin.R;
import com.metron.coin.base.repeatview.BaseViewHolder;
import com.metron.coin.data.IncomeUtil;
import com.metron.coin.databinding.ItemIncomeChannelBinding;
import com.metron.coin.util.NumberUtils;

/**
 * 收益列表:渠道专员
 */
public class IncomeChannelAdapter extends BaseViewHolder<ItemIncomeChannelBinding, IncomeList.Income> {

    @Override
    protected void onBindData(IncomeList.Income income) {
        bindRoot.setIncomeUtil(new IncomeUtil());
        bindRoot.setNumberUtil(NumberUtils.instance());
        bindRoot.setIncome(income);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_income_channel;
    }

}
