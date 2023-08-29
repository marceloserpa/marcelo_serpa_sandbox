package com.marceloserpa;

import jdk.incubator.concurrent.ScopedValue;

public class Context {

    public final static ScopedValue<Long> LoggedUserID = ScopedValue.newInstance();

    public void process(Long userId) {
        ScopedValue.where(LoggedUserID, userId).run(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logStep(i);
            }
        });

    }

    private void logStep(int step) {
        System.out.println("User: " + Context.LoggedUserID.get() + " Step: " + step );
    }


}
