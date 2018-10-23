package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerServeMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 15:12
 */

@Service
public class CustomerServeService extends BaseService<CustomerServe> {

    @Autowired
    private CustomerServeMapper customerServeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加或更新
     * 客户服务
     * @param customerServe
     * @param userId
     */
    public void saveOrUpdateCustomerServe(CustomerServe customerServe,Integer userId) {

        customerServe.setUpdateDate(new Date());

        Integer id = customerServe.getId();

        if (null == id) {
            UserDto userDto = userMapper.queryById(userId);
            customerServe.setCreatePeople(userDto.getUserName());// 创建人
            customerServe.setState("1");// 初始状态1
            customerServe.setIsValid(1);// 有效状态1
            customerServe.setCreateDate(new Date());
            AssertUtil.isTrue(customerServeMapper.save(customerServe)<1,
                    CrmConstant.OPS_FAILED_MSG);
        }
    }

}
