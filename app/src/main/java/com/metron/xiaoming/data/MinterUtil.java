package com.metron.xiaoming.data;

import android.text.TextUtils;

import com.alaer.lib.api.bean.MinterList;
import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.MinterStats;
import com.alaer.lib.api.bean.OrderList;
import com.alaer.lib.api.bean.PollNewInfo;
import com.alaer.lib.api.bean.WorkerStats;
import com.metron.xiaoming.R;
import com.metron.xiaoming.util.CollectionUtils;
import com.metron.xiaoming.util.NumberUtils;

import java.util.List;

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
            return String.valueOf(pollNewInfo.BTC.currencyValue);
        return "";
    }

    public String getCnyRateETH(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.ETH != null)
            return String.valueOf(pollNewInfo.ETH.currencyValue);
        return "";
    }

    public String getCnyUSDT(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.USDT != null && pollNewInfo.rates != null) {
            return (pollNewInfo.USDT.currencyPrice * pollNewInfo.rates.cny) + "";
        }
        return "";
    }

    public String getPercentChangeBTC(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.BTC != null) {
            String sign = pollNewInfo.BTC.pricePercentChange.day >= 0 ? "+" : "-";
            return sign + pollNewInfo.BTC.pricePercentChange.day + "%";
        }
        return "";
    }

    public String getPercentChangeETH(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.ETH != null) {
            String sign = pollNewInfo.ETH.pricePercentChange.day >= 0 ? "+" : "-";
            return sign + pollNewInfo.ETH.pricePercentChange.day + "%";
        }
        return "";
    }

    public String getPercentChangeUSDT(PollNewInfo pollNewInfo) {
        if (pollNewInfo != null && pollNewInfo.USDT != null) {
            String sign = pollNewInfo.USDT.pricePercentChange.day >= 0 ? "+" : "-";
            return sign + pollNewInfo.USDT.pricePercentChange.day + "%";
        }
        return "";
    }

    public String parseMinterStatus(MinterList.Minter minter) {
        if (minter == null)
            return "";
        final String[] statusList = {"库存", "运行中", "掉线", "待上架", "维护中", "下架"};
        if (minter.status < statusList.length)
            return statusList[minter.status];

        return "";
    }

    public String parseMinterEstimationCapability(MinterList.Minter minter) {
        return minter.model.hashrate + minter.model.hashrateUnit + "H/s";
    }

    public String parseMinterPowerWaste(MinterList.Minter minter) {
        return minter.model.power + minter.model.powerUnit + "±" + minter.model.powerFreeRate + "%";
    }

    // 0:库存 1:运行中 2:掉线 3:待上架 4:维护中 5:下架
    public int[] parseMinterStatsValues(List<MinterStats> minterStatsList) {
        int[] values = new int[4];
        for (MinterStats minterStats : minterStatsList) {
            values[0] += minterStats.count;
            if (minterStats.status == 1) {
                values[1] = minterStats.count;
            } else if (minterStats.status == 3) {
                values[2] = minterStats.count;
            } else if (minterStats.status == 4) {
                values[3] = minterStats.count;
            }
        }

        return values;
    }

    // 全网算力，单位K，需转换为T或者E等
    public String parseNetworkHashrate(PollNewInfo.Coin coin) {
        double power = 1;
        String unit = "KH/S";
        if (TextUtils.equals(coin.currency, "BTC")) {
            power = Math.pow(1000, 4);
            unit = "TH/S";
        } else if (TextUtils.equals(coin.currency, "ETH")) {
            power = Math.pow(1000, 2);
            unit = "MH/S";
        }
        return NumberUtils.instance().parseFloat8((float) (coin.networkHashrate / power)) + unit;
    }

    // 当前算力
    public String parseCurrentHashrate(WorkerStats.Income income) {
        return income.hashrate + income.hashrateUnit + "H/S";
    }

    // 当前难度，单位K，需转换为T或者E等
    public String parseDifficulty(PollNewInfo.Coin coin) {
        double power = 1;
        String unit = "KH/S";
        if (TextUtils.equals(coin.currency, "BTC")) {
            power = Math.pow(1000, 4);
            unit = "TH/S";
        } else if (TextUtils.equals(coin.currency, "ETH")) {
            power = Math.pow(1000, 2);
            unit = "MH/S";
        }
        return NumberUtils.instance().parseFloat8((float) (coin.difficulty / power)) + unit;
    }

    // 当前币价
    public String parseCoinCurrencyValue(PollNewInfo.Coin coin) {
        return "¥" + NumberUtils.instance().parseFloat8(coin.currencyValue);
    }

    // 每日理论收益
    public String parseCoinDayProfit(PollNewInfo.Coin coin) {
        return NumberUtils.instance().parseFloat8(coin.dayTheoryCurrencyIncome)
                + " " + coin.currency + " ≈ ¥ "
                + NumberUtils.instance().parseFloat8(coin.dayTheoryCnyIncome);
    }

    public String parseOrderTime(OrderList.Order order) {
        return parserIncomeTime(order.startTime) + "至" + parserIncomeTime(order.endTime);
    }

    public String parserIncomeTime(String time) {
        if (!TextUtils.isEmpty(time) && time.contains("T"))
            return time.split("T")[0];
        return time;
    }

    // 1:收益分成 2:固定投资，用于USDT
    public String parseOrderType(OrderList.Order order) {
        return "订单交易";
//        return (order.orderType == 1) ? "收益分成" : "固定投资";
    }

    // 采购数量
    public String parseOrderQuanlity(OrderList.Order order) {
        if (order == null || CollectionUtils.isEmpty(order.quantity))
            return "0台";
        int count = 0;
        for (OrderList.Quantity quantity : order.quantity) {
            count += quantity.quantity;
        }

        return count + "台";
    }

    public String parseUserName(OrderList.Order order) {
        if (order == null || order.user == null)
            return "";
        return order.user.nick;
    }

}
