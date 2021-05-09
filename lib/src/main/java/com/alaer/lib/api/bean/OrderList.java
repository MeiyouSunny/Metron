package com.alaer.lib.api.bean;

import java.util.List;

public class OrderList {

    public int count;
    public List<Order> rows;

    public static class Order {
        public float amount;
        public float usdt;
        public int rate;
        public int id;
        public String createTime;
        public String updateTime;
        public String sn;
        public int userId;
        public Object channelCommissionerId;
        public String startTime;
        public String endTime;
        public String paidTime;
        public int orderType;
        public int status;
        public String remark;
        public List<Quantity> quantity;
        public Object channelCommissioner;
        public User user;
    }

    public static class Quantity {
        public int quantity;
        public int id;
        public String createTime;
        public String updateTime;
        public int userId;
        public int orderId;
        public int modelId;
        public Model model;
    }

    public static class Model {
        public float hashrate;
        public float hashrateFreeRate;
        public float power;
        public float powerFreeRate;
        public float price;
        public float staticIncomeBy300Day;
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

    public static class User {
        public int id;
        public String nick;
        public String mobile;

    }

}
