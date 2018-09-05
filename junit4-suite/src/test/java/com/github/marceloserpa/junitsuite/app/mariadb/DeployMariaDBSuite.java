package com.github.marceloserpa.junitsuite.app.mariadb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.github.marceloserpa.junitsuite.app.DeployTest;
import com.github.marceloserpa.junitsuite.app.MariaDBConfig;
import com.github.marceloserpa.junitsuite.app.ShutdownTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MariaDBConfig.class,
    DeployTest.class,
    ShutdownTest.class
})
public class DeployMariaDBSuite {
}
