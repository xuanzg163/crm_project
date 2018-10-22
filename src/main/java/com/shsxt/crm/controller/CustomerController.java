package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/22
 * @time 10:21
 */

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("index")
    public String index() {
        return "customer";
    }

    /**
     *  客户信息管理
     *  查询分页
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.queryForPage(query);
    }
}
