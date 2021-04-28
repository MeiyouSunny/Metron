package com.metron.coin.data;

import android.text.TextUtils;

import com.alaer.lib.api.bean.WithdrawStats;
import com.metron.coin.util.CollectionUtils;

public class IncomeUtil {

    public String parserIncomeTime(String time) {
        if (!TextUtils.isEmpty(time) && time.contains("T"))
            return time.split("T")[0];
        return time;
    }

    public float parseWithdrawTotal(WithdrawStats withdrawStats) {
        if (withdrawStats == null || CollectionUtils.isEmpty(withdrawStats.withdraw))
            return 0;
        for (WithdrawStats.Withdraw withdraw : withdrawStats.withdraw) {
            if (withdraw.status == 1)
                return withdraw.amount;
        }

        return 0;
    }

}
