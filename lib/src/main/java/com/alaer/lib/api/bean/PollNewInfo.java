package com.alaer.lib.api.bean;

public class PollNewInfo {

    public Coin BTC;
    public Coin ETH;
    public Coin USDT;
    public Rate rates;

    public static class Coin {
        public String currency;
        public PricePercentChange pricePercentChange;
        public String incomeUnit;
        public float networkHashrate;
        public float currencyPrice;
        public float cnyRate;
        public float currencyValue;
        public float dayTheoryCurrencyIncome;
        public float dayTheoryCnyIncome;

    }

    public static class PricePercentChange {
        public float hour;
        public float day;
        public float week;
    }

    public static class Rate {
        public float btc;
        public float cny;
        public float eur;
        public float jpy;
        public float krw;
        public float rub;
        public float sar;
        public float usd;
    }
}
