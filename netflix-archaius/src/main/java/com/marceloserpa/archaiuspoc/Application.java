package com.marceloserpa.archaiuspoc;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;

public class Application {

    public static void main(String[] args) {


        DynamicIntProperty prop =
                DynamicPropertyFactory.getInstance().getIntProperty("port", 0);
        // prop.get() may change value at runtime
        System.out.println(prop.get());


    }

}
