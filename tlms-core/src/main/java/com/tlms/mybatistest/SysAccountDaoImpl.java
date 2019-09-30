package com.tlms.mybatistest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName SysAccountDaoImpl
 * @Description sysAccount接口实现类
 * @author 160068
 * @date 2019年9月30日 下午1:51:32
 * @version V1.0
 */
public class SysAccountDaoImpl implements SysAccountDao{

	private SqlSession sqlSession;
	
	public SysAccountDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SysAccountRespDto> selectByParams(SysAccountReqDto sysAccountReqDto) throws Exception {
		List<SysAccountRespDto> resultList = sqlSession.selectList("MyMapper1.selectSysAccountById1", sysAccountReqDto);
		return resultList;
	}

}
