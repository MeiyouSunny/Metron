package com.alaer.lib.api.bean;

import java.util.List;

public class IncomeList {

    public int count;
    public List<Income> rows;

    public static class Income {
        public float hashrate;
        public float totalIncome;
        public float powerFee;
        public float income;
        public float userIncome;
        public float companyIncome;
        public int channelIncome;
        public float currencyIncomePerUnit;
        public float cnyIncomePerUnit;
        public int id;
        public String createTime;
        public String updateTime;
        public String sn;
        public String currency;
        public int userId;
        public int orderId;
        public int workerId;
        public Object channelCommissionerId;
        public String incomeTime;
    }

}
