package com.marceloserpa.quartz.list;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class MyTask implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if(context.getJobDetail().getJobDataMap().containsKey("address")){
            List<Address> address = (List)context.getJobDetail().getJobDataMap().get("address");
            for(Address ad : address){
                System.out.println("Street: " + ad.getStreet() + " City: " + ad.getCity());
            }
        }

    }
}
