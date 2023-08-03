package com.example.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author yf
 * @create 2023-08-03 21:13
 */
@Slf4j
public class HelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        context.getJobDetail().getJobDataMap().forEach(
                (k,v) -> log.info("param, key:{}, value:{}" ,k,v)
        );
        log.info("quartz 测试完成了，执行时间：" + new Date());
    }
}
