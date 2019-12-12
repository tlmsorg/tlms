package com.tlms.core.annotiontest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbCreateMain {

	private static String getConstraints(Constraints con) {
	    String constraints = "";
	    if(!con.allowNull())
	      constraints += " NOT NULL";
	    if(con.primaryKey())
	      constraints += " PRIMARY KEY";
	    if(con.unique())
	      constraints += " UNIQUE";
	    return constraints;
	  }

	
	public String createTableSql(String className) throws Exception {
		String sql = "";
		List<String> columnDefs = new ArrayList<String>();
		
		Member member = new Member();
		Class<?> memberClass = Class.forName("com.tlms.core.annotiontest.Member");
		System.out.println(memberClass.getClassLoader());
		DBTable dbTable = memberClass.getAnnotation(DBTable.class);
		String tableName = dbTable.name();
		System.out.println(tableName);
//		sb.append("CREATE TABLE \n");
//		sb.append(tableName.toUpperCase() +"\n");
//		sb.append(" ( \n");
		Field[] fields = memberClass.getDeclaredFields();
		System.out.println("fields:"+Arrays.toString(fields));
		for (Field field : fields) {
			StringBuffer columnSb = new StringBuffer();
//			Annotation[] antts = field.getAnnotations();
			Annotation[] antts = field.getDeclaredAnnotations();
			if(antts[0] instanceof SQLString) {
				SQLString sqlString = (SQLString) antts[0];
				System.out.println("属性出现的注解SQLString："+sqlString);
				System.out.println(field.getName());
				System.out.println(sqlString.name());
				System.out.println(sqlString.value());
				Constraints constraint = sqlString.constraint();
				System.out.println(constraint.primaryKey());
				String columnName = sqlString.name();
				columnSb.append(columnName);
				columnSb.append(" VARCHAR(" + sqlString.value() + ") ");
				columnSb.append(this.getConstraints(constraint));
			    columnDefs.add(columnSb.toString());
			}
			
			if(antts[0] instanceof SQLInteger) {
				SQLInteger sqlInteger = (SQLInteger) antts[0];
				System.out.println("属性出现的注解SQLInteger："+sqlInteger);
				System.out.println(field.getName());
				Constraints contraint = sqlInteger.constraint();
				String columnName = sqlInteger.name();
				columnSb.append(columnName);
				columnSb.append(" int ");
				columnSb.append(this.getConstraints(contraint));
				columnDefs.add(columnSb.toString());
			}
		}
		System.out.println(columnDefs);
		
		StringBuffer tableSb = new StringBuffer();
		tableSb.append("CREATE TABLE "+ tableName +" ( ");
		for (String column : columnDefs) {
			tableSb.append("\n"+column + ",");
		}
		
		String tableSql = tableSb.toString().substring(0, tableSb.toString().length()-1) + "\n);";

		System.out.println(tableSql);
		return sql;
	}
	
	public static void main(String[] args) throws Exception {
		DbCreateMain dbCreate = new DbCreateMain();
		dbCreate.createTableSql("com.tlms.core.annotiontest.Member");
	}

}
