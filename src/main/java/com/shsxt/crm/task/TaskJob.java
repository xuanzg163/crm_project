package com.shsxt.crm.task;

import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 8:53
 */

@Component
public class TaskJob {
    @Autowired
    private CustomerService customerService;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void job01() {
        System.out.println("定时任务开始");
        customerService.addLossCustomers();
        System.out.println("定时任务结束");

    }
}
