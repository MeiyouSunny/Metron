package com.metron.xiaoming.adapter;

import com.alaer.lib.api.bean.IncomeList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.repeatview.BaseViewHolder;
import com.metron.xiaoming.data.IncomeUtil;
import com.metron.xiaoming.databinding.ItemIncomeBinding;
import com.metron.xiaoming.util.NumberUtils;

/**
 * 收益列表
 */
public class IncomeAdapter extends BaseViewHolder<ItemIncomeBinding, IncomeList.Income> {

    @Override
    protected void onBindData(IncomeList.Income income) {
        bindRoot.setIncomeUtil(new IncomeUtil());
        bindRoot.setNumberUtil(NumberUtils.instance());
        bindRoot.setIncome(income);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_income;
    }

}
