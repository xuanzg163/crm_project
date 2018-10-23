package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 19:51
 */

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("{state}")
    public String index(@PathVariable Integer state) {

        if (state == 0){

        } else if (state == 1) {
            return "report_customerLevel";
        }
        return "error";
    }

    /**
     * 客户报表，依照客户名和客户等级分组
     * @return
     */
    @RequestMapping("queryCustomerLevelNums")
    @ResponseBody
    public List<Map> queryCustomerLevelNums() {
        return customerService.queryCustomerLevelNums();
    }
}
