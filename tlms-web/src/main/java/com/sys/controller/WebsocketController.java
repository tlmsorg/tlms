package com.sys.controller;

import java.io.IOException;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketHandler;

import com.alibaba.fastjson.JSONObject;
import com.tlms.push.handler.PcmsWebSocketHandler;

@RestController
@ResponseBody
@RequestMapping(value="/websocket")
public class WebsocketController {

	@Autowired
	private PcmsWebSocketHandler pcmsWebSocketHandler;
	@RequestMapping(value="/push",method=RequestMethod.POST)
	public void pushMesg(@RequestBody JSONObject message) throws IOException{
//		JSONObject json = JSONObject.parseObject(message);
		
		pcmsWebSocketHandler.sendMessage(message.getString("userId"), message.getString("userName"));
	}
}
