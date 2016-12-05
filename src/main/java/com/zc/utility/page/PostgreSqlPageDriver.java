/** 
* @title PostgreSqlPageDriver.java
* @author zhuzhzh/zhuzz@heptax.com
* @date：2016年6月7日 下午3:49:46 
* Copyright 2016 知藏. All right reserved.
* PostgreSql分页拦截实现方法
*/
package com.zc.utility.page;


public class PostgreSqlPageDriver implements PageDriver {

    @Override
    public String getCountSql(String sql) {
        return String.format("select count(1) from (%s) a ", sql);
    }

    @Override
    public String getPageSql(Page pageHelper, String sql) {
        int offset = (pageHelper.getPageNumber() - 1)
                * pageHelper.getPageSize();
        return String.format("SELECT * from (%s)a  LIMIT %d offset %d ;", sql,
                pageHelper.getPageSize(), offset);
    }

}
