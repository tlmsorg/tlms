package com.tlms.push.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.tlms.push.handler.PcmsWebSocketHandler;

@Controller
public class PushController {
	private static final Logger logger = Logger.getLogger(PushController.class);
	@ResponseBody
	@RequestMapping(value="/startPush.ctrl")
	public void startPush(){
		logger.info("PushController->startPush");
		ArrayList<WebSocketSession> sessionList = PcmsWebSocketHandler.sessionList;
		int openCnt = 0;
		logger.info("连接数:"+sessionList.size());
		for(WebSocketSession webSocketSession:sessionList){
			try {
				String sendToClient = webSocketSession.getHandshakeAttributes().get("userId").toString();
				if(webSocketSession.isOpen()){
					openCnt++;
					webSocketSession.sendMessage(new TextMessage(sendToClient));
				}
			} catch (IOException e) {
//				e.printStackTrace();
			}		
		}
		logger.info("sessionList:"+sessionList+"|当前链接数openCnt："+openCnt);
	}
}
