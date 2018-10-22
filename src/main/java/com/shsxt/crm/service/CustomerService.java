package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/22
 * @time 10:22
 */

@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 保存或更新客户信息
     * @param customer
     */
    public void saveOrUpdateCustomer(Customer customer) {
        //参数校验省略
        customer.setUpdateDate(new Date());
        Integer id = customer.getId();

        if (null == id){
            customer.setState(0);// 未流失
            customer.setIsValid(1);// 有限
            customer.setCreateDate(new Date());
            // 客户编号
            customer.setKhno(MathUtil.genereateKhCode());

            AssertUtil.isTrue(customerMapper.save(customer) < 1,
                    CrmConstant.OPS_FAILED_MSG);

        } else {
            AssertUtil.isTrue(customerMapper.update(customer)<1,
                    CrmConstant.OPS_FAILED_MSG);
        }
    }

    public List<Map> queryCustomerLevel(String dicName){
        return customerMapper.queryCustomerLevel(dicName);
    }
}
