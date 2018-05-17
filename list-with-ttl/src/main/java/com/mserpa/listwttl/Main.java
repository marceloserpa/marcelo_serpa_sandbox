package com.mserpa.listwttl;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ListWithTTL elements = new ListWithTTL();

        elements.register(new Element("172.20.1.200"));
        elements.register(new Element("172.20.1.201"));
        elements.register(new Element("172.20.1.202"));

        Thread.sleep(1000l);
        elements.printState();

        Thread.sleep(1000l);
        elements.printState();

        elements.heartbeat("172.20.1.201");


        Thread.sleep(1000l);
        elements.printState();

        Thread.sleep(1000l);
        elements.printState();

        Thread.sleep(1000l);
        elements.printState();

        Thread.sleep(1000l);
        elements.printState();

        Thread.sleep(1000l);
        elements.printState();

    }


}
