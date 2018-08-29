package com.tlms.bpm.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Model;

import com.tlms.bpm.vo.ModelVo;
import com.tlms.bpm.vo.ProcessDefVo;
import com.tlms.bpm.vo.ProcessInstanceVo;

public interface IBpmService {
	/**
	 * 流程发布,发布eclipse编辑的bpmn文件
	 */
	public void deployProcess();
	/**
	 * 删除已发布流程
	 * @param pdid 流程定义id
	 */
	public void deleteDeployProcess(String pdid);
	/**
	 * 删除流程定义
	 * @param pdid
	 */
	public void deleteProcessDef(String pdid);
	public void deleteProcessDefCascade(String pdid);
	
	public void startProcess(String pdid);
	public List<ProcessInstanceVo> queryProcess();
	public List<ProcessDefVo> queryDefProcess();
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
	public void doClaim(String userId,String procInstId);
	public void doDelegate(String userId,String procInstId);
	/**
	 * 根据业务编码和流程key查询流程实例
	 * @param busiKey 业务表单业务编码
	 * @param pdKey Process definition key 流程定义key
	 * @return
	 */
	public List<ProcessInstanceVo> queryProcessByBusinesskeyAndPdkey(String busiKey,String pdKey);
	/**
	 * 保存model测试
	 */
	public void processDesign();
	/**
	 * 根据id查询流程模型
	 * @param modelId
	 * @return
	 */
	public List<Model> selectModelById(String modelId);
	/**
	 * 查询所有流程模型
	 * @return
	 */
	public List<ModelVo> selectAllModel();
	
//	public void editModel(String modelId);
	/**
	 * 发布流程模型
	 * @param modelId
	 */
	public void deployModel(String modelId);
	/**
	 * 删除模型
	 * @param modelId
	 */
	public void deleteModel(String modelId);
	
	public List<ProcessInstanceVo> queryClaimProcess(String userId);
	
	/**
	 * 发布手动流程
	 */
	public void deployModelManual();
	
}
