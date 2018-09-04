package com.github.marceloserpa.parallelsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HelloWorldTest.class, HelloWorld2Test.class })
public class HelloWorldSuite {

}
