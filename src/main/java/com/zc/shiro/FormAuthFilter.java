/** 
* @Title AuthBasicFilter.java
* @author 张镇强/zhangzq@heptax.com
* @date：2016年4月22日 
* Copyright 2016 知藏. All right reserved.
*  [描述]
*/
package com.zc.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.CommonHelper;
import com.zc.utility.response.ApiResultModel;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author 张镇强 / zhangzq@heptax.com
 *
 */
public class FormAuthFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return getSubject(request, response).isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);

        String requestPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (CommonHelper.isAPIRequest(requestPath)) {
            ApiResultModel result = new ApiResultModel(StatusCodeEnum.UNAUTHENTICATED);

            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("text/json;charset=UTF-8");
            setCorsHeader(WebUtils.toHttp(httpResponse));
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write(JSON.toJSONString(result));
            return false;
        }

        return super.onAccessDenied(request, response);
    }

    private void setCorsHeader(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers",
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
    }
}
