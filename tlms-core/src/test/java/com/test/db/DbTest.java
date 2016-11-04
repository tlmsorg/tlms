package com.test.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.tlms.core.util.Utils;



/**
 * @author tom
 *
 */
public class DbTest {

	/**
	 * tom 2016年11月1日
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String sql = "	select a.table_name,b.table_comment as table_cname,a.column_name as field_name,a.column_comment as field_cname from (select table_name,column_name,COLUMN_COMMENT from information_schema.columns where COLUMN_COMMENT <> '' )a "
					+ " left join (select table_name,table_comment from information_schema.TABLES where table_comment <> '')b "
					+ " on a.table_name = b.table_name ";
			Class.forName("com.mysql.jdbc.Driver");
//			getConnection("jdbc:mysql://127.0.0.1:3306/test?username=test&password=test");
			Connection cnt = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "test", "test");
			/*DatabaseMetaData dbMeta = (DatabaseMetaData) cnt.getMetaData();
			ResultSet rs = dbMeta.getTables(null, "%", "%", new String[]{"TABLE"});
			while(rs.next()){
				System.out.println(rs.getString("T_APPLY"));
			}
			*/
			PreparedStatement ps = (PreparedStatement) cnt.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<HisFieldComment> list = new ArrayList<HisFieldComment>();
			while(rs.next()){
				ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
				Class cls = HisFieldComment.class;
				HisFieldComment obj = (HisFieldComment) cls.newInstance();
				for (int i = 0; i < meta.getColumnCount(); i++) {
					String columnType = meta.getColumnTypeName(i+1);//数据库对应type
					String columnLabel = meta.getColumnLabel(i+1);//sql语句对应列名
					String className = meta.getColumnClassName(i+1);
					String fieldName = Utils.col2Field(columnLabel);
					String setMethod = Utils.field2SetMethod(fieldName);
					Object value = rs.getObject(columnLabel);
					System.out.println(fieldName+"|"+columnType+"|"+className+"|"+columnLabel);
					Method settter = cls.getMethod(setMethod, className.getClass());
					settter.invoke(obj, value);
				}
				list.add(obj);
				System.out.println(obj.getTableName()+"|"+obj.getTableCname()+"|"+obj.getFieldName()+"|"+obj.getFieldCname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
