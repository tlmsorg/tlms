package com.sys.test;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class DataFieldMax implements DataFieldMaxValueIncrementer{

	public static void main(String[] args){
		// TODO Auto-generated method stub

	}

	public int nextIntValue() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	public long nextLongValue() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String nextStringValue() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
