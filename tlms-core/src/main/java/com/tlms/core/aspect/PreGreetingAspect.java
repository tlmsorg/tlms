package com.tlms.core.aspect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.dao.Test1Mapper;
import com.tlms.core.dao.Test1OperHisMapper;
import com.tlms.core.dao.Test1TableMapMapper;
import com.tlms.core.domain.Test1;
import com.tlms.core.domain.Test1OperHis;
import com.tlms.core.domain.Test1TableMap;
import com.tlms.core.util.Utils; 

@Aspect 
public class PreGreetingAspect {
	@Autowired
	private Test1OperHisMapper test1OperHisMapper;
	@Autowired
	private Test1Mapper test1Mapper;
	@Autowired
	private Test1TableMapMapper test1TableMapMapper;
	
	@Before("execution(* com.tlms.core.servlce.impl..*(..)) && args(object)")
	public void target(Object object) throws IllegalArgumentException, IllegalAccessException, ParseException{
		JSONObject jsonObj = (JSONObject) JSONObject.toJSON(object);
		Calendar cl = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
		System.out.println(sdf.format(calendar.getTime()));
		
		ArrayList mapperArray = new ArrayList();
		String className = "";
		String tableName = "";
		Field[] fields = object.getClass().getDeclaredFields();
		System.out.println("*********增强开始***********");
		System.out.println("PreGreetingAspect->target");
		System.out.println("对象类名 ："+object.getClass().getName());
		className = object.getClass().getName();
		Test1TableMap ttmm = test1TableMapMapper.selectByClassname(className);
		tableName = ttmm.getTableName();
		System.out.println("tableName:"+tableName);
		
		HashMap<String,Object> condition = new HashMap<String,Object>();
		condition.put("tableName", tableName);
		condition.put("id","'"+jsonObj.getString("id")+"'");
		HashMap operRecord = test1Mapper.selectCommon(condition);
		System.out.println("operRecord:"+operRecord);
		if(operRecord != null){
			Iterator<String> keyIt = operRecord.keySet().iterator();
//			存在object对应记录,执行修改操作
			while(keyIt.hasNext()){
				String key = keyIt.next();
				String value = (String) operRecord.get(key);
				System.out.println("key:"+key+"|value:"+value);
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					String fieldName = Utils.fieldParse2Col(field.getName());//属性名转数据库列
					String feildValue = (String) field.get(object);
					System.out.println("fieldName:"+fieldName+"|feildValue:"+feildValue);
					if(key.toUpperCase().equals(fieldName)){
						if(!value.equals(feildValue)){//修改值
							Test1OperHis test1OperHis = new Test1OperHis();
							test1OperHis.setAppCode("1");
							test1OperHis.setUserCode("200810405234");
							test1OperHis.setClassName(className);
							test1OperHis.setTableName(tableName);
							test1OperHis.setUpdMode("update");
							test1OperHis.setValuePre(value);
							test1OperHis.setValueNow(feildValue);
							test1OperHis.setOperTime(cl.getTime());
							test1OperHis.setId(UUID.randomUUID()+"");
							test1OperHis.setFieldName(fieldName);
							test1OperHisMapper.insert(test1OperHis);
						}
					}
				}
			}
		}else{
//			不存在记录，执行新增操作
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = Utils.fieldParse2Col(field.getName());
				String fieldValue = (String) field.get(object);
				Test1OperHis test1OperHis = new Test1OperHis();
				test1OperHis.setAppCode("1");
				test1OperHis.setUserCode("200810405234");
				test1OperHis.setClassName(className);
				test1OperHis.setTableName(tableName);
				test1OperHis.setUpdMode("add");
				test1OperHis.setValuePre("");
				test1OperHis.setValueNow(fieldValue);
				test1OperHis.setOperTime(cl.getTime());
				test1OperHis.setId(UUID.randomUUID()+"");
				test1OperHis.setFieldName(fieldName);
				test1OperHisMapper.insert(test1OperHis);
			}
			
		}
		
		
		
		Object objPre = null;//原始对象
		Method[] methods = object.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if(method.getName().startsWith("get")){
				System.out.println(method.getName());
			}
		}
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				System.out.println("属性名:"+field.getName()+"|值："+(String) field.get(object));
				if(field.getName().equals("id")){
//					objPre = test1Mapper.selectByPrimaryKey("1");
					
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("*********增强结束***********");
	}
}
