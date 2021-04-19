package com.metron.coin.data;

import android.text.TextUtils;

import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.PollNewInfo;
import com.metron.coin.R;

public class MinterUtil {

    public String parseMinterEstimationCapability(MinterSeries.MinterInfo minterInfo) {
        return minterInfo.hashrate + minterInfo.hashrateUnit + "H/s";
    }

    public String parseMinterPowerWaste(MinterSeries.MinterInfo minterInfo) {
        return minterInfo.power + minterInfo.powerUnit + "±" + minterInfo.powerFreeRate + "%";
    }

    public String parseMinterEstimationRevenue(MinterSeries.MinterInfo minterInfo) {
        return minterInfo.staticIncomeBy300Day + "%";
    }

    public String parseMinterPrice(MinterSeries.MinterInfo minterInfo) {
        return "¥ " + minterInfo.price;
    }

    public int parseMinterCurrencyIcon(MinterSeries minterSeries) {
        int iconRes = R.drawable.ic_btc;
        if (minterSeries == null)
            return iconRes;
        if (TextUtils.equals(minterSeries.currency, "ETH")) {
            iconRes = R.drawable.ic_eth;
        } else if (TextUtils.equals(minterSeries.currency, "USDT")) {
            iconRes = R.drawable.ic_usdt;
        }

        return iconRes;
    }

    public String getCnyRateBTC(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.BTC != null)
            return String.valueOf(pollNewInfo.BTC.cnyRate);
        return "";
    }

    public String getCnyRateETH(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.ETH != null)
            return String.valueOf(pollNewInfo.ETH.cnyRate);
        return "";
    }

}
