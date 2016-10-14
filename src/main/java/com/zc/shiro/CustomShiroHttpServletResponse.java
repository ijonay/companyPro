/** 
* @title CustomShiroHttpServletResponse.java
* @author zhanbq/战博奇  
* @date：2016年5月5日 下午12:49:04 
* Copyright 2016 知藏. All right reserved.
*  
*/  
package com.zc.shiro;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;

public class CustomShiroHttpServletResponse extends ShiroHttpServletResponse{

    /**
     * @param wrapped
     * @param context
     * @param request
     */
    public CustomShiroHttpServletResponse(HttpServletResponse wrapped, ServletContext context,
            ShiroHttpServletRequest request) {
        super(wrapped, context, request);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected String toEncoded(String url , String sessionId){
        
        if(url==null || sessionId==null){
            return url;
        }
        String path = url;
        String query = "";
        String anchor = "";
        int question = url.indexOf('?');
        
        if( question >= 0 ){
            url.substring(0, question);//截取
            query.substring(question);
        }
        int pound = path.indexOf('#');
        if(pound >= 0 ){
            anchor = path.substring(pound);
            path= path.substring(0 , pound);
        }
        StringBuilder strBuilder = new StringBuilder(path);
        strBuilder.append(anchor);
        strBuilder.append(query);
        return strBuilder.toString();
    }
}
