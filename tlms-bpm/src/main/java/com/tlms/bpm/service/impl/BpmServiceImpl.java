package com.tlms.bpm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlms.bpm.dao.ApplyMapper;
import com.tlms.bpm.domain.Apply;
import com.tlms.bpm.service.IBpmService;
import com.tlms.bpm.vo.ProcessInstanceVo;

@Service
public class BpmServiceImpl implements IBpmService{
	private static final Logger logger = Logger.getLogger(BpmServiceImpl.class);
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private SpringProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ApplyMapper applyMapperImpl;
	@Override
	public void deployProcess() {
		repositoryService.createDeployment().addClasspathResource("LeaveProcess.bpmn").deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
		logger.info("key:"+processDefinition.getKey());
	}
	
	@Override
	public void deleteProcess() {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> pdList = processDefinitionQuery.list();
		for (ProcessDefinition processDefinition : pdList) {
			logger.info("删除已deploy流程，deployid："+processDefinition.getDeploymentId());
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
		}
	}
	
	@Override
	public void startProcess(String pdid) {
		Apply apply = new Apply();
		/**
		 * 申请单表主键（和流程实例关联的业务编码）
		 */
		String applyId = "tlms"+System.currentTimeMillis();
		apply.setId(UUID.randomUUID().toString());
		apply.setUserId("001");
		apply.setApplyId(applyId);
		applyMapperImpl.insertSelective(apply);
		
		logger.info("启动流程："+pdid);
//		ProcessInstance processInstance = runtimeService.startProcessInstanceById(pdid);
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("businessKey", applyId);
		//使用processDefinitionId启动流程
		/**
		 * 根据流程定义id启动流程，设置流程变量
		 */
//		ProcessInstance processInstance = runtimeService.startProcessInstanceById(pdid, variables);
		String pdKey = pdid;
		/**
		 * 根据流程定义key启动流程，设置流程变量
		 */
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(pdKey, variables);
		/**
		 * 根据流程定义key启动流程，设置业务编码和流程变量
		 */
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(pdKey, applyId, variables);
		logger.info("processInstance:"+processInstance);
		logger.info(processInstance.getId());
	}
	
	@Override
	public List<ProcessInstanceVo> queryProcess() {
		List<ProcessInstanceVo> processVoList = new ArrayList<ProcessInstanceVo>();
		List<ProcessInstance> processInstancesList = runtimeService.createProcessInstanceQuery().list();
		for (ProcessInstance processInstance : processInstancesList) {
			ProcessInstanceVo instance = new ProcessInstanceVo();
			instance.setId(processInstance.getId());
			instance.setProcInstId(processInstance.getProcessInstanceId());
			instance.setProcDefId(processInstance.getProcessDefinitionId());
			processVoList.add(instance);
		}
		System.out.println("processVoList:"+processVoList);
		return processVoList;
	}

	/* 
	    * 递归查询经过的流 
	    */  
	   private void getHighLightedFlows(List<ActivityImpl> activityList, List<String> highLightedFlows, List<String> historicActivityInstanceList) {  
	       for (ActivityImpl activity : activityList) {  
	           if (activity.getProperty("type").equals("subProcess")) {  
	               // get flows for the subProcess  
	               getHighLightedFlows(activity.getActivities(), highLightedFlows, historicActivityInstanceList);  
	           }  
	  
	           if (historicActivityInstanceList.contains(activity.getId())) {  
//	               List<PvmTransition> pvmTransitionList = activity.getOutgoingTransitions();  
	        	   List<PvmTransition> pvmTransitionList = activity.getIncomingTransitions();
	               for (PvmTransition pvmTransition : pvmTransitionList) {  
	                   String destinationFlowId = pvmTransition.getDestination().getId(); 
	                   if (historicActivityInstanceList.contains(destinationFlowId)) {  
	                       highLightedFlows.add(pvmTransition.getId());  
	                   }  
	               }  
	           }  
	       }  
	   }  
	   
	@Override
	public InputStream readSource(String processInstId,String resourceType) {
		String taskId = taskService.createTaskQuery().processInstanceId(processInstId).singleResult().getId();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String pdid = task.getProcessDefinitionId();
		logger.info("当前任务id："+taskId);
		InputStream inputStream = null;
		if("dgrmResource".equals(resourceType)){
//			ProcessDiagramGenerator pdg = processEngineConfiguration.getProcessDiagramGenerator();
			BpmnModel bpmnModel = repositoryService.getBpmnModel(pdid);
			
			//已完成节点+当前处理节点
			List<String> activeActivityIdList = new ArrayList<String>();
			List<String> finishActiveActivityIdList = new ArrayList<String>();
			//已执行的活动节点
			List<HistoricActivityInstance> hisActivityInstanceList = historyService.createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).finished().list();
			for (HistoricActivityInstance historicActivityInstance : hisActivityInstanceList) {
				finishActiveActivityIdList.add(historicActivityInstance.getActivityId());
			}
			
			activeActivityIdList.addAll(finishActiveActivityIdList);
			//获取活动状态节点
			List<String> activeActivityIds = runtimeService.getActiveActivityIds(task.getProcessInstanceId());
			activeActivityIdList.addAll(activeActivityIds);
			
			
			// 经过的流  
	        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());  
	        List<String> highLightedFlows = new ArrayList<>();  
//	        getHighLightedFlows(processDefinitionEntity.getActivities(), highLightedFlows, activeActivityIdList); 

	        ProcessDiagramGenerator pdg = processEngineConfiguration.getProcessDiagramGenerator();  
	        /*InputStream inputStream = pdg.generateDiagram(bpmnModel, "PNG", finishActiveActivityIdList, highLightedFlows,  
	                processEngineConfiguration.getProcessEngineConfiguration().getActivityFontName(),  
	                processEngineConfiguration.getProcessEngineConfiguration().getLabelFontName(),  
	                processEngineConfiguration.getProcessEngineConfiguration().getProcessEngineConfiguration().getClassLoader(), 10.0);*/  
	        
//	        InputStream inputStream = pdg.generateDiagram(bpmnModel, "PNG", runtimeService.getActiveActivityIds("7505"));
	        inputStream = pdg.generateDiagram(bpmnModel, "PNG", activeActivityIds, highLightedFlows,  
	                processEngineConfiguration.getProcessEngineConfiguration().getActivityFontName(),  
	                processEngineConfiguration.getProcessEngineConfiguration().getLabelFontName(),  
	                processEngineConfiguration.getProcessEngineConfiguration().getProcessEngineConfiguration().getClassLoader(), 1.0);
	        
		}else{
			ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
			ProcessDefinition pd = pdq.processDefinitionId(pdid).singleResult();
			inputStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());//获取bpmn文件
		}

        /*
		ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition pd = pdq.processDefinitionId(pdid).singleResult();
		InputStream is = null;
		if("dgrmResource".equals(resourceType)){
			is = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());//获取图片资源
		}else{
			is = repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName());//获取bpmn文件
		}
		return is;
		*/
		
		//模拟获取表单数据
		logger.info("taskService获取流程变量："+taskService.getVariable(task.getId(), "businessKey"));
		logger.info("runtimeService获取流程变量："+runtimeService.getVariable(task.getProcessInstanceId(), "businessKey"));
		return inputStream;
	}

	@Override
	public List<ProcessInstanceVo> queryCurrProcess(String userId) {
		List<Task> currTaskList = taskService.createTaskQuery().taskAssignee(userId).list();
		List<ProcessInstanceVo> pivList = new ArrayList<ProcessInstanceVo>();
		for (Task task : currTaskList) {
			ProcessInstanceVo piv = new ProcessInstanceVo();
			String processInstId = task.getProcessInstanceId();
			ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).singleResult();
			piv.setId(pi.getId());
			piv.setProcInstId(pi.getProcessInstanceId());
			piv.setProcDefId(pi.getProcessDefinitionId());
			pivList.add(piv);
		}
		return pivList;
	}

	@Override
	public void doAgree(String userId,String procInstId) {
		logger.info("审批通过："+userId+"|"+procInstId);
		ProcessInstance procInst = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("bmldComment", "部门领导审批意见：通过");
 		taskService.complete(task.getId(), variables);
	}

	@Override
	public void doReject(String userId, String procInstId) {
		ProcessInstance procInst = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("gjComment", "个金审批意见：拒绝");
		variables.put("type", 2);
		taskService.complete(task.getId(), variables);
	}

	@Override
	public List<ProcessInstanceVo> queryProcessByBusinesskeyAndPdkey(String busiKey, String pdKey) {
		List<ProcessInstanceVo> pivList = new ArrayList<ProcessInstanceVo>();
		List<ProcessInstance> piList = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(busiKey, pdKey).list();
		for (ProcessInstance pi : piList) {
			ProcessInstanceVo piv = new ProcessInstanceVo();
			piv.setId(pi.getId());
			piv.setProcInstId(pi.getProcessInstanceId());
			piv.setProcDefId(pi.getProcessDefinitionId());
			pivList.add(piv);
		}
		
		return pivList;
	}


}
