package com.test.parenttochild;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessorUtils;

public class Main {

	public void fieldCopy(Object src,Object dest){
		Field[] srcFields = src.getClass().getFields();
		Field[] destFields = dest.getClass().getFields();
		for (Field srcField:srcFields) {
			for (Field destField:destFields) {
				if(srcField.getName().equals(destField.getName())){
					try {
						destField.set(dest, srcField.get(src));
//						System.out.println(srcField.getName()+"|"+srcField.get(src));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Parent> parentList = new ArrayList<Parent>();
		List<Child> childList = new ArrayList<Child>();
		Parent parent = new Parent();
		parent.setId("1");
		parent.setName("tang");
		parent.setSex("男");
		
		Child child = new Child();
		parentList.add(parent);
		childList.add(child);
//		child = (Child) parent;
//		parent = child;
		Main m = new Main();
		Field[] fields = child.getClass().getFields();
		for(Field field:fields){
			try {
				System.out.println(field.get(child));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		m.fieldCopy(parent,child);
		BeanUtils.copyProperties(parentList, childList);
//		m.fieldCopy(parentList,childList);
		for (Child childTemp:childList) {
			for(Field field:fields){
				try {
					System.out.println(field.get(child));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
