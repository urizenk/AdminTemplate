package com.example.config;

import com.example.job.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yf
 * @create 2023-08-03 20:03
 */
@Configuration
public class QuartzConfig {

    @Bean("helloJob")
    public JobDetail helloJobDetail(){
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("DateTimeJob")
                .usingJobData("msg","Hello Quartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger helloTrgger(){
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetail())
                .withIdentity("quartzHelloTask")
                .withSchedule(builder)
                .build();
    }
}
