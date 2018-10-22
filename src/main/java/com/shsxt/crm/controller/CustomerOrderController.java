package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("index")
    public String index(Integer cusId, Model model) {
        Customer customer = customerService.queryById(cusId);
//        model.addAttribute("customer",customer);
        model.addAttribute(customer);
        return "customer_order";
    }

}
