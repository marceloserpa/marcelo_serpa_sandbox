package com.mserpa.esperpoc;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.mserpa.esperpoc.events.OrderEvent;
import com.mserpa.esperpoc.listeners.MyListener;

import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addEventTypeAutoName("com.mserpa.esperpoc.events");
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(configuration);

        String expression = "select avg(price) from OrderEvent.win:time(10 sec)";
        EPStatement statement = epService.getEPAdministrator().createEPL(expression);

        MyListener myListener = new MyListener();
        statement.addListener(myListener);

        List<OrderEvent> orders = Arrays.asList(
            new OrderEvent("shirt", 74.50),
            new OrderEvent("sneakers", 194.50),
            new OrderEvent("short", 66.90));

        orders.forEach(order -> epService.getEPRuntime().sendEvent(order));




    }

}



















