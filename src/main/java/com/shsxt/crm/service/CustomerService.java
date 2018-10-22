package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuan
 * @date 2018/10/22
 * @time 10:22
 */

@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;
}
