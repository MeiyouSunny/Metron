package com.alaer.lib.api.bean;

import java.util.List;

public class OrderList {

    public int count;
    public List<Order> rows;

    public static class Order {
        public float amount;
        public float usdt;
        public float rate;
        public int id;
        public String createTime;
        public String updateTime;
        public String sn;
        public int userId;
        public String channelCommissionerId;
        public String startTime;
        public String endTime;
        public String paidTime;
        public int orderType;
        public int status;
        public List<Quantity> quantity;
        public Object channelCommissioner;
    }

    public static class Quantity {
        public int quantity;
        public int id;
        public String createTime;
        public String updateTime;
        public int userId;
        public int orderId;
        public int modelId;
        public Modle modle;
    }

    public static class Modle {
        public int hashrate;
        public int hashrateFreeRate;
        public int power;
        public int powerFreeRate;
        public int price;
        public double staticIncomeBy300Day;
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
    }

}
