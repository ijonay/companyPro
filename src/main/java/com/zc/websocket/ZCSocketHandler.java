/** 
* @title ZCSocketHandler.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月26日 上午11:45:03 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
public class ZCSocketHandler extends TextWebSocketHandler {
    @Override  
    protected void handleTextMessage(WebSocketSession session,  
            TextMessage message) throws Exception {  
        super.handleTextMessage(session, message);  
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(returnMessage);  
    }
}
