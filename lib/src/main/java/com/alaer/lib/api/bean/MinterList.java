package com.alaer.lib.api.bean;

import java.util.List;

public class MinterList {

    public int count;
    public List<Minter> rows;

    public static class Minter {
        public float estimatedDailyIncome;
        public int id;
        public String createTime;
        public String updateTime;
        public int userId;
        public int orderId;
        public int orderType;
        public String identifier;
        public int modelId;
        public String currency;
        public String startTime;
        public String endTime;
        public String address;
        public int status;
        public Model model;

        public static class Model {
            public float hashrate;
            public String hashrateFreeRate;
            public String power;
            public String powerFreeRate;
            public String price;
            public String staticIncomeBy300Day;
            public int id;
            public String createTime;
            public String updateTime;
            public int seriesId;
            public String name;
            public String algorithm;
            public String hashrateUnit;
            public String powerUnit;
            public String thumbnail;
            public int stock;
            public int minimumQuantity;
            public int status;
            public Series series;

            public static class Series {
                public int id;
                public String createTime;
                public String updateTime;
                public String name;
                public String currency;
                public int status;
            }
        }
    }

}
