package com.sys.service.impl;

import org.springframework.stereotype.Service;

import com.pujjr.pjrp.dubbo.service.IDubboService;

@Service
public class DubboConsumerImpl implements IDubboService {

	@Override
	public String dubboService() {
		return "1234343";
	}

}
