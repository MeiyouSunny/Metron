package com.alaer.lib.api.bean;

public class PollNewInfo {

    public CoinInfo BTC;
    public CoinInfo ETH;

    public static class CoinInfo {
        public String currency;
        public String incomeUnit;
        public String networkHashrate;
        public float currencyPrice;
        public float cnyRate;
        public float currencyValue;
        public String dayTheoryCurrencyIncome;
        public String dayTheoryCnyIncome;
    }

}
