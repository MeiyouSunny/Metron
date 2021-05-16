package com.alaer.lib.api.bean;

import com.google.gson.annotations.SerializedName;

public class WorkerStats {

    public CoinStats BTC;
    public CoinStats ETH;

    public static class CoinStats {
        public Status status;
        public Income income;
        public int count;
    }

    public static class Status {
        // 0:库存 1:运行中 2:掉线 3:待上架 4:维护中 5:下架
        @SerializedName("0")
        public int _$0;
        @SerializedName("1")
        public int _$1;
        @SerializedName("2")
        public int _$2;
        @SerializedName("3")
        public int _$3;
        @SerializedName("4")
        public int _$4;
        @SerializedName("5")
        public int _$5;
    }

    public static class Income {
        public double hashrate;
        public String hashrateUnit;
    }

}
