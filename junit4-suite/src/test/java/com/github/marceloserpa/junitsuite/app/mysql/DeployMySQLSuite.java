package com.github.marceloserpa.junitsuite.app.mysql;

import com.github.marceloserpa.junitsuite.app.DeployTest;
import com.github.marceloserpa.junitsuite.app.MySQLConfig;
import com.github.marceloserpa.junitsuite.app.ShutdownTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MySQLConfig.class,
    DeployTest.class,
    ShutdownTest.class
})
public class DeployMySQLSuite {

}
