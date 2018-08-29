package com.tlms.bpm.listener;

import org.activiti.engine.EngineServices;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

/**
 * 自动转办监听器
 * @author tom
 *
 */
public class TaskAutoRedirectListener implements TaskListener{
	private static final Logger logger = Logger.getLogger(TaskAutoRedirectListener.class);

	@Override
	public void notify(DelegateTask delegateTask) {
		logger.info("任务转办");
		logger.info(delegateTask);
		logger.info(ToStringBuilder.reflectionToString(delegateTask));
		String currAssignee = delegateTask.getAssignee();//当前代办人
		EngineServices engineService = delegateTask.getExecution().getEngineServices();
		TaskService taskService = engineService.getTaskService();
		String newUserId = "002";//将当前监听任务转给次代办人代为处理。
		taskService.setAssignee(delegateTask.getId(), newUserId);
		taskService.addComment(delegateTask.getId(), delegateTask.getProcessInstanceId(), "redirect", "备注消息");
		
	}
}
