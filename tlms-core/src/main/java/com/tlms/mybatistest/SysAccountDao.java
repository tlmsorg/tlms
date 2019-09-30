package com.tlms.mybatistest;

import java.util.List;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName SysAccountDao
 * @Description sysAccount接口
 * @author 160068
 * @date 2019年9月30日 下午1:50:33
 * @version V1.0
 */
public interface SysAccountDao {
	
	public List<SysAccountRespDto> selectByParams(SysAccountReqDto sysAccountReqDto) throws Exception;
	
}
