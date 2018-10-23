package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    @Autowired
    private CustomerLossMapper customerLossMapper;

    /**
     * 查询流失客户
     */
    public void addLossCustomers() {

        /***
         * 1. 查询所有流失客户
         * 2. 批量插入客户流失表
         * 3.流失客户状态添加
         * */
        List<Customer> customerList = customerMapper.queryLossCustomers();

        if (!CollectionUtils.isEmpty(customerList)) {

            /**
             * 存流失客户列表
             */
            List<CustomerLoss> customerLossList = new ArrayList<>();

            for (Customer customer:customerList) {
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setState(0);// 预流失
                customerLoss.setIsValid(1);// 有效
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLossList.add(customerLoss);
            }

            /**
             * 批量插入流失客户到客户流失表
             */
            AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList)
            <customerLossList.size(),CrmConstant.OPS_FAILED_MSG);

            /**
             * 流失客户状态添加为1
             */
            AssertUtil.isTrue(customerMapper.updateCustomerState(customerList)<customerList.size(),
                    CrmConstant.OPS_FAILED_MSG);
        }
    }

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
