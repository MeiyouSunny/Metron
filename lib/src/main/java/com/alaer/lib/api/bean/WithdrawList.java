package com.alaer.lib.api.bean;

import java.util.List;

public class WithdrawList {

    public int count;
    public List<Withdraw> rows;

    public static class Withdraw {
        public float balance;
        public int id;
        public String createTime;
        public String updateTime;
        public String sn;
        public String currency;
        public int userId;
        public String amount;
        public String withdrawTime;
        public int status;
        public int withdrawType;
        public int userRole;
    }

}
