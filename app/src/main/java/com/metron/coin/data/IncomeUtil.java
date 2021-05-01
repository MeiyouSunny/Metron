package com.metron.coin.data;

import android.text.TextUtils;

import com.alaer.lib.api.bean.WithdrawStats;
import com.metron.coin.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    // 2021-04-23 00:00 --> 04/23
    public String formatTimeString(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(time);
            format = new SimpleDateFormat("MM/dd");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

}