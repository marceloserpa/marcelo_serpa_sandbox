package com.marceloserpa;

import jdk.incubator.concurrent.ScopedValue;

public class Context {

    public final static ScopedValue<Long> LoggedUserID = ScopedValue.newInstance();


}
