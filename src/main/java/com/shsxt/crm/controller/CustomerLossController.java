package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 11:11
 */

@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index() {
        return "customer_loss";
    }

    /**
     *  显示流失客户
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerLossQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLossService.queryForPage(query);

    }

    /**
     * 确认流失
     * @param customerLoss
     * @return
     */
    @RequestMapping("updateCustomerLoss")
    @ResponseBody
    public ResultInfo updateCustomerLoss(CustomerLoss customerLoss) {
        customerLossService.update(customerLoss);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}