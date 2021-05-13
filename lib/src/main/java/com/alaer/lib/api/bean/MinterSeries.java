package com.alaer.lib.api.bean;

import java.util.List;

public class MinterSeries {

    public int id;
    public String createTime;
    public String updateTime;
    public Object createUser;
    public Object updateUser;
    public String name;
    public String currency;
    public List<MinterInfo> models;

    public static class MinterInfo {
        public int id;
        public String createTime;
        public String updateTime;
        public Object createUser;
        public Object updateUser;
        public int seriesId;
        public String name;
        public String algorithm;
        public float hashrate;
        public float hashrateFreeRate;
        public String hashrateUnit;
        public int power;
        public int powerFreeRate;
        public String powerUnit;
        public String thumbnail;
        public int stock;
        public int minimumQuantity;
        public String price;
        public String staticIncomeBy300Day;
        public int status;
    }

}
