package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * @author zhangxuan
 * @date 2018/10/23
 * @time 14:17
 */

public class CustomerReprieveQuery extends BaseQuery {
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
