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
		//获取执行实体对象（当前流程正在执行的实体）
		ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findExecutionById(procInstId);
		executionEntity.destroyScope("退回原因：客户资料缺失，请重新提交资料");
		//通过执行实体获取流程定义
		ProcessDefinitionImpl procDef = executionEntity.getProcessDefinition();
		//通过流程定义获取对应的活动
		ActivityImpl activity = procDef.findActivity(activityId);
		executionEntity.executeActivity(activity);
		return executionEntity;
	}

}
 