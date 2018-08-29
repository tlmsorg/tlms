package com.tlms.push.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
//@Configuration
@Component
public class PcmsWebSocketHandler implements WebSocketHandler{
	private static final Logger logger = Logger.getLogger(PcmsWebSocketHandler.class);
	public static final ArrayList<WebSocketSession> sessionList;
	static{
		sessionList = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<WebSocketSession> sessionList = PcmsWebSocketHandler.sessionList;
		for(int i = 0;i < sessionList.size();i++){
			WebSocketSession tempSession = sessionList.get(i);
			logger.info("tempSession:"+tempSession);
			if(webSocketSession.equals(tempSession)){
				sessionList.remove(tempSession);
				logger.info("i:"+sessionList.size());
			}	
		}
		logger.info("afterConnectionClosed-》session是否打开："+webSocketSession.isOpen());
		webSocketSession.close();
		logger.info("afterConnectionClosed-》session是否打开："+webSocketSession.isOpen());
		logger.info("剩余链接数 sessionList.size():"+sessionList.size());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		logger.info("afterConnectionEstablished");
		logger.info("webSocketSession");
		logger.info(webSocketSession);
		logger.info(webSocketSession.getRemoteAddress());
		logger.info(webSocketSession.getHandshakeAttributes());
		logger.info("afterConnectionEstablished-》session是否打开："+webSocketSession.isOpen());
		webSocketSession.sendMessage(new TextMessage("服务端链接成功"));
		sessionList.add(webSocketSession);
		for(int i = 0;i < sessionList.size();i++){
			WebSocketSession tempSession = sessionList.get(i);
			logger.info("************连接信息展示开始**************："+i);
			logger.info("当前连接sessionId："+tempSession.getId());
			logger.info(tempSession.getRemoteAddress());
			logger.info(tempSession.getHandshakeHeaders());
			logger.info(tempSession.getHandshakeHeaders());
			logger.info(tempSession.getPrincipal());
		}
		
	}

	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
		Object recObject = message.getPayload();
		JSONObject recJson = JSONObject.parseObject(recObject + "");
		logger.info("接收客户端数据recJson:"+recJson);
		Map<String,Object> hsa = webSocketSession.getHandshakeAttributes();
		ArrayList<WebSocketSession> sessionList = PcmsWebSocketHandler.sessionList;
		if(sessionList.size() == 0){
			hsa.put("userName", recJson.getString("userName"));
			hsa.put("userId", recJson.getString("userId"));
			hsa.put("sessionId", webSocketSession.getId());
			sessionList.add(webSocketSession);
		}else{
			boolean isExist = false;//true:未签入；false:已签入
			WebSocketSession existSession = null;
			for(WebSocketSession tempSession:sessionList){
				String tempUserId = hsa.get("userId")+"";
				if(tempUserId.equals(recJson.getString("userId"))){
					isExist = true;
					existSession = tempSession;
				}
			}
			if(!isExist){
				hsa.put("userId", recJson.getString("userId"));
				hsa.put("userName", recJson.getString("userName"));
				hsa.put("sessionId", webSocketSession.getId());
				sessionList.add(webSocketSession);
			}else{
				existSession.getHandshakeAttributes().put("userName", recJson.getString("userName"));
			}
		}
		logger.info("接收客户端数据："+recObject+"|当前链接session数 sessionList.size():"+sessionList.size());
		//发送客户端
		webSocketSession.sendMessage(new TextMessage("接收客户端数据："+recObject+"|当前链接session数 sessionList.size():"+sessionList.size()));
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//给指定人员发送信息
	public void sendMessage(String userId,String message) throws IOException{
		WebSocketSession findSession = null;
		String userName = "";
		for (WebSocketSession webSocketSession : sessionList) {
			Map<String,Object> hsa = webSocketSession.getHandshakeAttributes();
			String userIdLoop = hsa.get("userId")+"";
			if(userId.equals(userIdLoop)){
				findSession = webSocketSession;
//				userName = hsa.get("userName")+"";
				userName = message;//用户前端显示，后台推送值得改变情况
				break;
			}
		}
		if(findSession == null){
			message = "用户:"+userId+"未登录";
		}else{
			message = "用户:"+userId+"已登录，姓名："+userName;
			findSession.sendMessage(new TextMessage(message));
		}
		
	}
	

}
