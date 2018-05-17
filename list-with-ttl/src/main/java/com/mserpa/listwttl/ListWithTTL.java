package com.mserpa.listwttl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ListWithTTL {

    private static final long TTL = 3000l;
    private static final long CHECKER_INTERVAL = 1000l;

    private ScheduledExecutorService executor;
    private List<Element> actives;
    private List<Element> olders;

    public ListWithTTL() {
        this.actives = new LinkedList<>();
        this.olders = new LinkedList<>();
        this.executor =  Executors.newScheduledThreadPool(1);
        backgroundEvictChecker();
    }

    public void register(Element element){
        this.actives.add(element);
    }

    public void heartbeat(String ip){
        for(Element element : actives){
            if(element.getIp().equals(ip)){
                element.updateHearthbeat();
                break;
            }
        }
    }

    private ScheduledFuture<?> backgroundEvictChecker() {
        return executor.scheduleAtFixedRate(() -> {
            System.out.println("verify expiration");
            for (Iterator<Element> iterator = actives.iterator(); iterator.hasNext(); ) {
                Element element = iterator.next();
                long timeElapsed = System.currentTimeMillis() - element.getHeartbeat();
                System.out.println("time elapsed: " + timeElapsed);
                if(timeElapsed > TTL){
                    iterator.remove();
                    olders.add(element);
                }
            }
        },0, CHECKER_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public void printState(){
        System.out.println("-----");
        System.out.println("actives");
        for(Element element : actives){
            System.out.println(element.getIp());
        }

        System.out.println("olders");
        for(Element element : olders){
            System.out.println(element.getIp());
        }
        System.out.println("-----");
    }

}
