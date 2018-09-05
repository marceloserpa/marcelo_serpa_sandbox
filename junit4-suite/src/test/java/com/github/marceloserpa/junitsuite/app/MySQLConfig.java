package com.github.marceloserpa.junitsuite.app;

import org.junit.Test;

public class MySQLConfig {

    @Test
    public void configure(){
        DataBaseConfig.getInstance().setAmi("ami-8897");
    }

}
