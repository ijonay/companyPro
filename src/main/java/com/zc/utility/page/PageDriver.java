/** 
* @title PageDriver.java
* @author zhuzhzh/zhuzz@heptax.com
* @date：2016年6月7日 下午3:42:08 
* Copyright 2016 知藏. All right reserved.
* 分页拦截驱动类（可自行继承实现不同Sql以便实现自定义分页sql） 
*/
package com.zc.utility.page;

public interface PageDriver {

    /**
     * 获取总记录数Sql
     * 
     * @param sql
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月7日 下午3:43:16
     */
    String getCountSql(String sql);

    /**
     * 获取分页数据sql
     * 
     * @param pageHelper
     * @param sql
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月7日 下午4:35:10
     */
    String getPageSql(Page pageHelper, String sql);

}
