package com.tlms.bpm.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import junit.framework.TestCase;

public class Test23 extends TestCase {
	@Test
	public void testStartProcess(){
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("LeaveProcess.bpmn").deploy();
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
		System.out.println(processDefinition.getDeploymentId());
		System.out.println(processDefinition.getKey());
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		System.out.println(processInstance.getId()+"|"+processInstance.getName()+"|"+processInstance.getProcessVariables()+"|"+processInstance.getBusinessKey()+"|"+processInstance.getProcessDefinitionKey());
	}
}
