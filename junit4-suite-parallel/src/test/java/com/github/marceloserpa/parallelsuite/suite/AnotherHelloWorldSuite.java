package com.github.marceloserpa.parallelsuite.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.marceloserpa.parallelsuite.AnotherHelloWorld;
import com.github.marceloserpa.parallelsuite.AnotherHelloWorldTest;

@RunWith(Suite.class)
@SuiteClasses({ AnotherHelloWorldTest.class })
public class AnotherHelloWorldSuite {

}
