package com.github.marceloserpa.parallelsuite.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.marceloserpa.parallelsuite.HelloWorld2Test;
import com.github.marceloserpa.parallelsuite.HelloWorldTest;

@RunWith(Suite.class)
@SuiteClasses({ HelloWorldTest.class, HelloWorld2Test.class })
public class HelloWorldSuite {

}
