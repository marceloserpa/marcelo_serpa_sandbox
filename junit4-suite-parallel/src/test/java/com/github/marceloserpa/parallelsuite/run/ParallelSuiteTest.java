package com.github.marceloserpa.parallelsuite.run;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import com.github.marceloserpa.parallelsuite.AnotherHelloWorldTest;
import com.github.marceloserpa.parallelsuite.suite.HelloWorldSuite;

public class ParallelSuiteTest {
	
    @Test
    public void runSuiteParallel() {
        Class[] cls = { HelloWorldSuite.class, AnotherHelloWorldTest.class };
        
        JUnitCore.runClasses(ParallelComputer.classes(), cls);
    }

}
