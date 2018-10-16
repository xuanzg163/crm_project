package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther zhangxuan
 * @date 2018/10/15
 * @time 14:11
 */
@Service
public class SaleChanceService extends BaseService<SaleChance> {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 添加或更新操作
     */
    public void saveOrUpdateSaleChance(SaleChance saleChance, Integer userId) {
        /***
         * 步骤
         * 1. 校验参数
         * 2. 补全参数
         * 3. 通过id区分是添加或者更新
         * 4. 执行最终操作
         * */

        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(),
                saleChance.getLinkPhone());

            saleChance.setUpdateDate(new Date());
            Integer id = saleChance.getId();

            if (null == id) {
                //添加操作
                saleChance.setState(0);
                saleChance.setDevResult(0);
                saleChance.setIsValid(1);// 有效数据
                saleChance.setCreateDate(new Date());// 创建事件

                User user = userMapper.queryById(userId);
                saleChance.setCreateMan(user.getUserName());//创建人

                AssertUtil.isTrue(saleChanceMapper.save(saleChance) < 1, CrmConstant.OPS_FAILED_MSG);


            } else {
                //更新
                AssertUtil.isTrue(saleChanceMapper.update(saleChance) < 1, CrmConstant.OPS_FAILED_MSG);

            }


    }

    private void checkSaleChanceParams(String customerName, String linkMan,
                                       String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "联系电话为空");
    }

    public List<Map> queryAllCustomerManager(){
        return saleChanceMapper.queryAllCustomerManager();
    }
}
