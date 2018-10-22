package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.service.CustomerOrderService;
import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/22
 * @time 13:16
 */
@Controller
@RequestMapping("customerOrder")
public class CustomerOrderController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping("index")
    public String index(Integer cusId, Model model) {
        Customer customer = customerService.queryById(cusId);
//        model.addAttribute("customer",customer);
        model.addAttribute(customer);
        return "customer_order";
    }

    /**
     * 查询分页
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryCustomerOrdersByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerOrdersByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerOrderQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerOrderService.queryForPage(query);
    }

}
