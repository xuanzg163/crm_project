package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.po.CustomerReprieve;
import com.shsxt.crm.query.CustomerReprieveQuery;
import com.shsxt.crm.service.CustomerLossService;
import com.shsxt.crm.service.CustomerReprieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 14:13
 */

@Controller
@RequestMapping("customerReprieve")
public class CustomerReprieveController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @Autowired
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("index")
    public String index(Integer lossId, Model model){
        CustomerLoss customerLoss = customerLossService.queryById(lossId);
        model.addAttribute(customerLoss);
        return "customer_loss_reprieve";
    }

    /**
     *  添加或更新
     * @param customerReprieve
     * @return
     */
    @RequestMapping("saveOrUpdateCustomerReprieve")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.saveOrUpdateCustomerReprieve(customerReprieve);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delReprieve")
    @ResponseBody
    public ResultInfo delReprieve(Integer id){
        customerReprieveService.delete(id);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    /**
     * 查询分页
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryCustomerReprievesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerReprievesByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerReprieveQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerReprieveService.queryForPage(query);
    }

}
