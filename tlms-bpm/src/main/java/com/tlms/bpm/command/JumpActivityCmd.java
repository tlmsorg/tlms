package com.tlms.bpm.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

public class JumpActivityCmd implements Command<ExecutionEntity>{
	private String activityId;
	private String procInstId;
	
	public JumpActivityCmd(String activityId,String procInstId) {
		this.activityId = activityId;
		this.procInstId = procInstId;
	}
	
	@Override
	public ExecutionEntity execute(CommandContext commandContext) {
		ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findExecutionById(procInstId);
		executionEntity.destroyScope("销毁原因");
		ProcessDefinitionImpl procDef = executionEntity.getProcessDefinition();
		ActivityImpl activity = procDef.findActivity(activityId);
		executionEntity.executeActivity(activity);
		return executionEntity;
	}

}
 