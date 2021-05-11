package com.alaer.lib.api.bean;

import java.util.List;

public class WithdrawStats {

    public List<Withdraw> withdraw;
    public Income income;
    public float balance;

    public static class Income {
        public float totalIncome;
        public float hashrate;
        public float income;
        public float userIncome;
        public float companyIncome;
        public float channelIncome;
        public float dividedIncome;
        public float powerFee;
        public String hashrateUnit;
    }

    public static class Withdraw {
        public int status;
        public float amount;
    }

}
