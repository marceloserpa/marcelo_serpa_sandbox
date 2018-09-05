package com.github.marceloserpa.junitsuite.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShutdownTest {

    @Test
    public void shutdownDB() {
        DataBaseConfig config = DataBaseConfig.getInstance();
        assertNotNull(config.getAmi());
    }
}