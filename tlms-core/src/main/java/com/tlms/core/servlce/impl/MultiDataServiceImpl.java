package com.tlms.core.servlce.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mutidata.DatabaseContextHolder;
import com.core.mutidata.DynamicDataSource;
import com.core.mutidata.DynamicDataSourceHolder;
import com.tlms.core.dao.SysGroupMapper;
import com.tlms.core.dao.Test1Mapper;
import com.tlms.core.dao.Test2Mapper;
import com.tlms.core.domain.SysGroup;
import com.tlms.core.domain.Test1;
import com.tlms.core.service.IMultiDataService;

@Service
public class MultiDataServiceImpl implements IMultiDataService{


	@Autowired
	private SysGroupMapper sysGroupMapperImpl;
	@Override
	public String userQuery() {
		System.out.println("userQuery");
		System.out.println("********************************************");
		DynamicDataSourceHolder.setDataSource("dataSource2");
		List<SysGroup> groupList = sysGroupMapperImpl.selectAllGroup();
		System.out.println(groupList.size());
		return "11111";
	}

}
