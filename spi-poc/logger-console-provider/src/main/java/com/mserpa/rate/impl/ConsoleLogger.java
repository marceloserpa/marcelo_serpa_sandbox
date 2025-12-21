package com.mserpa.rate.impl;

import com.mserpa.rate.api.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String s) {
        System.out.println(s);
    }
}
