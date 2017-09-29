package com.tlms.bpm.service;

import java.io.InputStream;
import java.util.List;

import com.tlms.bpm.vo.ProcessInstanceVo;

public interface IBpmService {
	/**
	 * 流程发布
	 */
	public void deployProcess();
	/**
	 * 删除流程
	 */
	public void deleteProcess();
	public void startProcess(String pdid);
	public List<ProcessInstanceVo> queryProcess();
	/**
	 * 读取资源
	 * @param processInstId 流程实例id
	 * @param resourceType 资源类型 resource：bpmn文件，dgrmResource：图片
	 * @return
	 */
	public InputStream readSource(String processInstId,String resourceType);
	/**
	 * 查询指定用户当前任务
	 * @param userId
	 * @return
	 */
	public List<ProcessInstanceVo> queryCurrProcess(String userId);
	public void doAgree(String userId,String procInstId);
	public void doReject(String userId,String procInstId);
	
	/**
	 * 根据业务编码和流程key查询流程实例
	 * @param busiKey 业务表单业务编码
	 * @param pdKey Process definition key 流程定义key
	 * @return
	 */
	public List<ProcessInstanceVo> queryProcessByBusinesskeyAndPdkey(String busiKey,String pdKey);
	
	public void processDesign();
	
}
