/**
 * 通过uuid在应用层控制数据表主键
 */
package com.sys.test;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class UUIDTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int length = 10000;
		boolean find = false;
		ArrayList al = new ArrayList();
		String[] str = new String[length];
		for (int i = 0; i < length; i++) {
			UUID uuid = UUID.randomUUID();
			System.out.println(i+"*******"+uuid.toString());
			al.add(uuid);
		}
		
		for(int i = 0;i < length-1; i++){
			UUID src = (UUID) al.get(i);
			for (int j = i+1; j < length; j++) {
				UUID temp = (UUID) al.get(j);
				if(src.equals(temp)){
					System.out.println("找到相同的uuid");
					find = true;
				}
			}
		}
		System.out.println("未找到uuid");
		
	}	

}
