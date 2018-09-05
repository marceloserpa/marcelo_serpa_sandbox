package com.github.marceloserpa.junitsuite.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DeployTest {

    @Test
    public void deployDB() {
        DataBaseConfig config = DataBaseConfig.getInstance();
        System.out.println(config.getAmi());

        Deploy deploy = new Deploy();

        assertNotNull(deploy.deployDB(config.getAmi()));
    }
}