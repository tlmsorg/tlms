package com.tlms.mybatistest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ATestMain {
	private Logger logger = LoggerFactory.getLogger(ATestMain.class);
	Map map;
	/**
	 * 加载全局配置文件，调用相关statement
	 * @param args
	 * @throws IOException
	 */
	public void test1() throws Exception {
		String methodName = "自有方法：";
		String globalConfig = "com/tlms/mybatistest/mybatis-config.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(globalConfig);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//查询结果返回对象
		SysAccountRespDto sysAccountRespDto = null;
		/**
		 *selectOne方法，解析对象中id字段作为查询条件
		 */
		//1参数为对象
		SysAccountReqDto sysAccountReqDto = new SysAccountReqDto();
//		sysAccountReqDto.setId("17");
		sysAccountReqDto.setName("冉超");
//		sysAccountReqDto.setAge(50);
//		sysAccountReqDto.setSalary(5000.00);
		List<SysAccountRespDto> sysAccountRespDtoList = sqlSession.selectList("MyMapper1.selectSysAccountById1", sysAccountReqDto);
		logger.info(methodName+JSONObject.toJSONString(sysAccountRespDtoList));
		
		//2参数为字符串
		sysAccountRespDto = sqlSession.selectOne("MyMapper2.selectSysAccountById2", "10");
		logger.info(methodName+JSONObject.toJSONString(sysAccountRespDto));
		
		/*
		 * 返回map:测试callSettersOnNulls
		 */
		List<Map<String,String>> resutlList = sqlSession.selectList("MyMapper1.selectSysAccountById3", sysAccountReqDto);
		logger.info(resutlList.toString());
		
		
	}
	
	/**
	 * 手动实例化对象
	 * @throws Exception
	 */
	public void test2() throws Exception {
		String methodName = "手动实例化：";
		String globalConfig = "com/tlms/mybatistest/mybatis-config.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(globalConfig);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//查询结果返回对象
		SysAccountRespDto sysAccountRespDto = null;
		//1参数为对象
		SysAccountReqDto sysAccountReqDto = new SysAccountReqDto();
		sysAccountReqDto.setName("冉超");
		//3实例化对象
		SysAccountDao sysAccountDao = new SysAccountDaoImpl(sqlSession);
		List<SysAccountRespDto> sysAccountRespDtoList = sysAccountDao.selectByParams(sysAccountReqDto);
		logger.info(methodName+JSONObject.toJSONString(sysAccountRespDtoList,SerializerFeature.WriteMapNullValue));
	}
	
	/**
	 * 使用dynamic proxy
	 * @throws Exception
	 */
	public void test3() throws Exception {
		String methodName = "使用dynamic proxy：";
		String globalConfig = "com/tlms/mybatistest/mybatis-config.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(globalConfig);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//查询结果返回对象
		SysAccountRespDto sysAccountRespDto = null;
		//1参数为对象
		SysAccountReqDto sysAccountReqDto = new SysAccountReqDto();
		sysAccountReqDto.setName("冉超");
		//3使用dynamic proxy
		SysAccountDao sysAccountDao = sqlSession.getMapper(SysAccountDao.class);
		List<SysAccountRespDto> sysAccountRespDtoList = sysAccountDao.selectByParams(sysAccountReqDto);
		logger.info(methodName+JSONObject.toJSONString(sysAccountRespDtoList,SerializerFeature.WriteMapNullValue));
	}
	
	public static void main(String[] args) throws Exception {
		ATestMain test = new ATestMain();
//		test.test1();
//		test.test2();
		test.test3();
	}

}
