package com.marceloserpa.strampedlock.fun;

public class Balance {

    private long amount;

    public Balance(long initialAmount) {
        this.amount = initialAmount;
    }

    public long getAmount() {
        return amount;
    }

    public void addAmount(long value){
        this.amount += value;
    }
}
