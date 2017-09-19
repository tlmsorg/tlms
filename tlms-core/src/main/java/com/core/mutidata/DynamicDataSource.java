package com.core.mutidata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	/*@Override
	protected Object determineCurrentLookupKey() {
		 return DatabaseContextHolder.getCustomerType();   
	}*/
	@Override
	protected Object determineCurrentLookupKey() {
		// 从自定义的位置获取数据源标识
		System.out.println("***************determineCurrentLookupKey:"+DynamicDataSourceHolder.getDataSource());
		return DynamicDataSourceHolder.getDataSource();
	}

}
