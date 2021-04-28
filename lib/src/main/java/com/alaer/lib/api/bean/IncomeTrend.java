package com.alaer.lib.api.bean;

import java.util.List;

public class IncomeTrend {

    public List<Ticker> tickers;
    public String hashrateUnit;

    public static class Ticker {
        public float hashrate;
        public float totalIncome;
        public float powerFee;
        public float income;
        public float userIncome;
        public float companyIncome;
        public float channelIncome;
        public String time;
    }

}
