package com.github.marceloserpa.junitsuite.app;

import org.junit.Test;

public class MariaDBConfig {

    @Test
    public void configure(){
        DataBaseConfig.getInstance().setAmi("ami-1234");
    }

}
