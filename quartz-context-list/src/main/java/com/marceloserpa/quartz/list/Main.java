package com.marceloserpa.quartz.list;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        List<Address> address = Arrays.asList(new Address("a", "canoas"), new Address("b", "guaiba"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("address", address);

        JobDetail jobDetail = newJob(MyTask.class)
                .setJobData((new JobDataMap(parameters)))
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
