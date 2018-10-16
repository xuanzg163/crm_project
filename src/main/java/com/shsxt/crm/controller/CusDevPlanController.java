package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.service.CusDevPlanService;
import com.shsxt.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @auther zhangxuan
 * @date 2018/10/16
 * @time 13:43
 */
@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String index(Integer sid, Model model){
        SaleChance saleChance = saleChanceService.queryById(sid);
        model.addAttribute(saleChance);
        return "cus_dev_plan_detail";
    }

    @RequestMapping("queryCusDevPlansByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CusDevPlanQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return cusDevPlanService.queryForPage(query);
    }


}
