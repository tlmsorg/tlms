package com.tlms.push.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliyunPushController {
	@ResponseBody
	@RequestMapping(value="/singleSendMail",method=RequestMethod.POST)
	public String singleSendMail(){
		
		return "";
	}
}
