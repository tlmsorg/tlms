package com.tlms.bpm.service.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tlms.bpm.dao.ApplyMapper;
import com.tlms.bpm.domain.Apply;
import com.tlms.bpm.service.IBpmService;
import com.tlms.bpm.vo.ModelVo;
import com.tlms.bpm.vo.ProcessDefVo;
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
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private IdentityService identityServiceImpl;
	@Override
	public void deployProcess() {
		repositoryService.createDeployment().addClasspathResource("LeaveProcess.bpmn").deploy();
		List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
		for (ProcessDefinition pd : processDefinitionList) {
			logger.info("已定义流程查询，key:"+pd.getKey());
		}
		
	}
	
	@Override
	public void deleteDeployProcess(String pdid) {
		/*ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> pdList = processDefinitionQuery.list();
		for (ProcessDefinition processDefinition : pdList) {
			logger.info("删除已deploy流程，deployid："+processDefinition.getDeploymentId());
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
		}*/
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(pdid).singleResult();
		repositoryService.deleteDeployment(pd.getId(), true);
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
		variables.put("applyId", applyId);
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
		/**
		 * 查询用户所在用户组列表
		 */
		List<Group> groupList = identityServiceImpl.createGroupQuery().groupMember(userId).list();
		List<String> groupIdList = new ArrayList<String>();
		for (Group group : groupList) {
			String groupId = group.getId();
			groupIdList.add(groupId);
		}
		
		List<Task> currTaskList = taskService.createTaskQuery().taskCandidateGroupIn(groupIdList).list();//候选组列表待办任务
//		List<Task> currTaskList = taskService.createTaskQuery().taskAssignee(userId).list();//候选人待办任务
//		List<Task> currTaskList = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();
//		List<Task> currTaskList = taskService.createTaskQuery().taskCandidateUser(userId).list();//候选人待办任务
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
		 Map<String,Object> processVariables = runtimeService.getVariables(task.getExecutionId());
		logger.info("审批通过,获取流程变量processVariables:"+processVariables);
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("bmldComment", "部门领导审批意见：通过");
		if(task.getTaskDefinitionKey().equals("deptLeaderAudit")){
			variables.put("deptLeaderApproved", "true");
		}else if(task.getTaskDefinitionKey().equals("hrAudit")){
			variables.put("hrApproved", "true");
//			task.setAssignee("001");
		}
		DelegationState delegationState = task.getDelegationState();
		if(DelegationState.PENDING.equals(delegationState)){
			Map<String,Object> delegateVars = new HashMap<String,Object>();
			delegateVars.put("delegateComment", "待办意见");
			taskService.resolveTask(task.getId(), delegateVars);
			taskService.complete(task.getId(), variables);
		}else if("".equals("delegationState") || delegationState == null || "".equals("null")){
			taskService.complete(task.getId(), variables);
		}
		
			
 		
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
	public void doClaim(String userId, String procInstId) {
		logger.info("执行签收："+userId+"|"+procInstId);
//		ProcessInstance procInst = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		Map<String,Object> variables = new HashMap<String,Object>();
		if(userId.equals("003"))
			taskService.claim(task.getId(), "001");
		else
			taskService.claim(task.getId(), userId);
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

	@Override
	public void processDesign() {
		ObjectMapper objectMapper = new ObjectMapper();  
        ObjectNode editorNode = objectMapper.createObjectNode();  
        editorNode.put("id", "canvas");  
        editorNode.put("resourceId", "canvas");  
        ObjectNode stencilSetNode = objectMapper.createObjectNode();  
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
        editorNode.set("stencilset", stencilSetNode);  
        Model modelData = repositoryService.newModel();  
        
        ObjectNode modelObjectNode = objectMapper.createObjectNode();  
//      modelObjectNode.put(MODEL_NAME, actReModel.getName());  
//      modelObjectNode.put(MODEL_REVISION, 1);  
        modelObjectNode.put("myFieldName", "属性已");
        //String description = null;  
//      modelObjectNode.put(MODEL_DESCRIPTION, descp);  
        modelData.setMetaInfo(modelObjectNode.toString());  
//      modelData.setName(actReModel.getName());  
        modelData.setName("model名称");
        repositoryService.saveModel(modelData);  
        try {
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
	}

	@Override
	public List<Model> selectModelById(String modelId) {
		Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();
//		repositoryService.createDeployment().name("test").addS
		return null;
	}

	@Override
	public List<ModelVo> selectAllModel() {
		List<ModelVo> mvList = new ArrayList<ModelVo>();
		List<Model> modelList = repositoryService.createModelQuery().list();
		for (Model model : modelList) {
			ModelVo mv = new ModelVo();
			mv.setId(model.getId());
			mv.setName(model.getName());
			mvList.add(mv);
		}
		return mvList;
	}

	public void deployModelManual(){
		//手动生成bpmnModel对象
		BpmnModel bpmnModel = new BpmnModel();
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		startEvent.setName("开始");
		
		UserTask userTask = new UserTask();
		userTask.setId("ldsp");
		userTask.setName("领导审批");
//				userTask.setAssignee("001");
		
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		endEvent.setName("结束");
		
		List<SequenceFlow> sequenceFlows=new ArrayList<SequenceFlow>();  
		List<SequenceFlow> toEnd=new ArrayList<SequenceFlow>();  
		SequenceFlow sf1 = new SequenceFlow("start", "ldsp");
		sf1.setId("start_ldsp");
		sequenceFlows.add(sf1);
		SequenceFlow sf2 = new SequenceFlow("ldsp","end");
		sf2.setId("ldsp_end");
		toEnd.add(sf2);
		
		
		startEvent.setOutgoingFlows(sequenceFlows);
		userTask.setIncomingFlows(sequenceFlows);
		userTask.setOutgoingFlows(toEnd);
		endEvent.setIncomingFlows(toEnd);
		
		Process process = new Process();
		process.setId("leaveProcessDynamic");
		process.setName("请假流程-自动生成");
		process.addFlowElement(startEvent);
		process.addFlowElement(sf1);
		process.addFlowElement(userTask);
		process.addFlowElement(sf2);
		process.addFlowElement(endEvent);
		bpmnModel.addProcess(process);
		
		
		ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
		ProcessValidator processValidator = processValidatorFactory.createDefaultProcessValidator();
		List<ValidationError> validate = processValidator.validate(bpmnModel);
		for (ValidationError validationError : validate) {
			logger.error(validationError.getActivityId()+"|"+validationError.getActivityName());
			logger.error(validationError.getDefaultDescription());
			logger.error(validationError.getProblem());
		}
		
		//流程图自动布局
		new BpmnAutoLayout(bpmnModel).execute();
		
		Process process2 = bpmnModel.getProcessById("leaveProcessDynamic");
		UserTask userTask2 = (UserTask) process2.getFlowElement("ldsp");
		List<String> candidateUsers = new ArrayList();
		candidateUsers.add("001");
		userTask2.setCandidateUsers(candidateUsers);
		repositoryService.createDeployment().name("手动创建模型名称").addBpmnModel("手动创建流程名称.bpmn20.xml", bpmnModel).deploy();	
		
	}
	
	@Override
	public void deployModel(String modelId) {
//		Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();
		//1、读取模型资源
		Model model = repositoryService.getModel(modelId);
		byte[] modelEditorSource = repositoryService.getModelEditorSource(modelId);
		byte[] modelEditorSourceExtra = repositoryService.getModelEditorSourceExtra(modelId);
		//2、模型流程资源转json对象。
		ObjectNode modelNode = null;
		try {
			modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3、模型节点对象转bpmn模型对象
		BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		

		/**
		 * 发布流程定义时，指定各节点候选人、候选组
		 */
		Process process = bpmnModel.getProcessById("leaveProcessXXKJ");
		UserTask ldspTask = (UserTask) process.getFlowElement("deptLeaderAudit");
		List<String> ldspCandidateGroup = ldspTask.getCandidateGroups();
		List<String> candidateGroups = new ArrayList();
		candidateGroups.add("001");//001:group id
		ldspTask.setCandidateGroups(candidateGroups);
		
		UserTask hrspTask = (UserTask) process.getFlowElement("hrAudit");
		List<String> hrspCandidateGroups = new ArrayList();
		hrspCandidateGroups.add("002");
		hrspTask.setCandidateGroups(hrspCandidateGroups);
		
		UserTask xjTask = (UserTask) process.getFlowElement("reportBack");
		List<String> xjTaskGroups = new ArrayList();
		xjTaskGroups.add("003");
		xjTask.setCandidateGroups(xjTaskGroups);
		
		//手动生成bpmnModel对象
		/*BpmnModel bpmnModel = new BpmnModel();
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		startEvent.setName("开始");
		
		UserTask userTask = new UserTask();
		userTask.setId("ldsp");
		userTask.setName("领导审批");
//		userTask.setAssignee("001");
		
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		endEvent.setName("结束");
		
		List<SequenceFlow> sequenceFlows=new ArrayList<SequenceFlow>();  
		List<SequenceFlow> toEnd=new ArrayList<SequenceFlow>();  
		SequenceFlow sf1 = new SequenceFlow("start", "ldsp");
		sf1.setId("start_ldsp");
		sequenceFlows.add(sf1);
		SequenceFlow sf2 = new SequenceFlow("ldsp","end");
		sf2.setId("ldsp_end");
		sequenceFlows.add(sf2);
		
		startEvent.setOutgoingFlows(sequenceFlows);
		userTask.setIncomingFlows(sequenceFlows);
		userTask.setOutgoingFlows(toEnd);
		endEvent.setIncomingFlows(toEnd);
		
		Process process = new Process();
		process.setId("leaveProcess");
		process.setName("请假流程-自动生成");
		process.addFlowElement(startEvent);
		process.addFlowElement(sf1);
		process.addFlowElement(userTask);
		process.addFlowElement(sf2);
		process.addFlowElement(endEvent);
		bpmnModel.addProcess(process);
		
		Process process2 = bpmnModel.getProcessById("leaveProcess");
		UserTask userTask2 = (UserTask) process2.getFlowElement("ldsp");
		List<String> candidateUsers = new ArrayList();
		candidateUsers.add("001");
		userTask2.setCandidateUsers(candidateUsers);
		*/
		ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
		ProcessValidator processValidator = processValidatorFactory.createDefaultProcessValidator();
		List<ValidationError> validate = processValidator.validate(bpmnModel);
		for (ValidationError validationError : validate) {
			logger.error(validationError.getActivityId()+"|"+validationError.getActivityName());
			logger.error(validationError.getDefaultDescription());
			logger.error(validationError.getProblem());
		}
		//4、bpmn模型对象转xml字符串
		byte[] bpmnXmlArray = new BpmnXMLConverter().convertToXML(bpmnModel);
		String bpmn = "";
		try {
			bpmn = new String(bpmnXmlArray,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//5、发布bpmn对象
		/**
		 * 批注：流程名称末尾必须加.bpmn才能发布流程定义成功。
		 */
//		String processName = modelNode.get("properties").get("name").asText();
		String processName = modelNode.get("properties").get("name").textValue() + ".bpmn20.xml";
//		String processName = "test.bpmn";
		logger.info(modelNode.get("properties").get("name"));
		logger.info(modelNode.get("properties").get("name").asText());
		logger.info(modelNode.get("properties").get("name").textValue());
//		repositoryService.createDeployment().name(model.getName()).addString(processName, bpmn).deploy();
		repositoryService.createDeployment().name(model.getName()).addBpmnModel(processName, bpmnModel).deploy();
	}

	@Override
	public void deleteModel(String modelId) {
		repositoryService.deleteModel(modelId);
	}

	@Override
	public List<ProcessDefVo> queryDefProcess() {
		List<ProcessDefVo> pdvList = new ArrayList<ProcessDefVo>();
		List<ProcessDefinition> pdList = repositoryService.createProcessDefinitionQuery().list();
		for (ProcessDefinition processDefinition : pdList) {
			ProcessDefVo pdv = new ProcessDefVo();
			pdv.setId(processDefinition.getId());
			pdv.setName(processDefinition.getName());
			pdv.setKey(processDefinition.getKey());
			pdvList.add(pdv);
		}
		return pdvList;
	}
	
	@Override
	public void deleteProcessDefCascade(String pdid) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(pdid).singleResult();
		repositoryService.deleteDeployment(pd.getDeploymentId(), true);
		
		
	}

	@Override
	public void deleteProcessDef(String pdid) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(pdid).singleResult();
		repositoryService.deleteDeployment(pd.getDeploymentId(), false);
		
		
	}

	@Override
	public List<ProcessInstanceVo> queryClaimProcess(String userId) {
		logger.info("已签收待办任务查询");
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).list();
		List<ProcessInstanceVo> instList = new ArrayList<ProcessInstanceVo>();
		for (Task task : taskList) {
			ProcessInstanceVo inst = new ProcessInstanceVo();
			inst.setId(task.getProcessInstanceId());
			inst.setProcDefId(task.getProcessDefinitionId());
			instList.add(inst);
		}
		return instList;
	}

	@Override
	public void doDelegate(String userId, String procInstId) {
		Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		taskService.delegateTask(task.getId(), userId);
	}
	
}
