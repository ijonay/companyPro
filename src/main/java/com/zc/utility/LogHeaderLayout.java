/** 
* @Title LogHeaderLayout.java
* @author 张镇强/zhangzq@heptax.com
* @date：2016年5月6日 
* Copyright 2016 知藏. All right reserved.
*  [描述]
*/
package com.zc.utility;

import org.apache.log4j.PatternLayout;

/**
 * @author 张镇强 / zhangzq@heptax.com
 *
 */
public class LogHeaderLayout extends PatternLayout {
    @Override
    public String getHeader() {
        return "thread    level    datetime   category    method    message" + System.getProperty("line.separator");
    }
}
