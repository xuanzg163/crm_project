package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * @author zhangxuan
 * @date 2018/10/22
 * @time 15:25
 */

public class OrderDetailsQuery extends BaseQuery {
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
