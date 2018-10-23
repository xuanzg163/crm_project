package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.po.CustomerLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 11:09
 */

@Service
public class CustomerLossService extends BaseService<CustomerLoss> {

    @Autowired
    private CustomerLossMapper customerLossMapper;
}
