package com.tlms.bpm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlms.bpm.service.IBpmService;
import com.tlms.bpm.vo.ModelVo;
import com.tlms.bpm.vo.ProcessDefVo;
import com.tlms.bpm.vo.ProcessInstanceVo;

import io.swagger.annotations.Api;

@Api(tags="流程控制")
@RestController
@ResponseBody
public class BpmController {
	private static final Logger logger = Logger.getLogger(BpmController.class);
	@Autowired
	private IBpmService bpmServiceImpl;
	
	@RequestMapping(value="/process/deploy",method=RequestMethod.GET)
	public void processDeploy(HttpServletRequest request){
		logger.info(request.getAttribute("userId"));
		bpmServiceImpl.deployProcess();
	}
	@RequestMapping(value="/process/deploy/manual",method=RequestMethod.GET)
	public void processDeployManual(HttpServletRequest request){
		logger.info(request.getAttribute("userId"));
		bpmServiceImpl.deployModelManual();
	}
	
	@RequestMapping(value="/process/def/cascade/{pdid}",method=RequestMethod.DELETE)
	public void deleteProcessDefCascade(@PathVariable String pdid){
		bpmServiceImpl.deleteProcessDefCascade(pdid);
	}
	
	@RequestMapping(value="/process/def/{pdid}",method=RequestMethod.DELETE)
	public void deleteProcessDef(@PathVariable String pdid){
		bpmServiceImpl.deleteProcessDef(pdid);
	}
	
	@RequestMapping(value="/process/delete/{pdid}",method=RequestMethod.GET)
	public void deleteProcess(@PathVariable String pdid){
		bpmServiceImpl.deleteDeployProcess(pdid);
	}
	
	
	@RequestMapping(value="/process/start/{processId}",method=RequestMethod.GET)
	public void startProcess(HttpServletRequest request,@PathVariable String processId){
		bpmServiceImpl.startProcess(processId);
	}
	
	@RequestMapping(value="/process/query",method=RequestMethod.GET)
	public List<ProcessInstanceVo> queryProcess(){
		return bpmServiceImpl.queryProcess();
	}
	
	@RequestMapping(value="/process/query/def",method=RequestMethod.GET)
	public List<ProcessDefVo> queryDefProcess(){
		return bpmServiceImpl.queryDefProcess();
	}
	
	@RequestMapping(value="/process/query/{busiKey}/{pdKey}",method=RequestMethod.GET)
	public List<ProcessInstanceVo> querySpecialProcess(@PathVariable String busiKey,@PathVariable String pdKey){
		return bpmServiceImpl.queryProcessByBusinesskeyAndPdkey(busiKey, pdKey);
	}
	
	/**
	 * 读取流程图
	 * @param processInstId
	 * @param resourceType
	 * @param response
	 */
	@RequestMapping(value="/source/{processInstId}/{resourceType}",method=RequestMethod.GET)
	public void readResource(@PathVariable String processInstId,@PathVariable String resourceType,HttpServletResponse response){
		InputStream is = bpmServiceImpl.readSource(processInstId,resourceType);
		byte[] buf = new byte[1024];
		int readLenth = 0;
		try {
			while((readLenth = is.read(buf)) > 0){
				response.getOutputStream().write(buf, 0, readLenth);
				readLenth = 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询当前任务
	 * @return
	 */
	@RequestMapping(value="/process/task",method=RequestMethod.GET)
	public List<ProcessInstanceVo> queryCurrProcess(HttpServletRequest request){
		String userId = request.getAttribute("userId")+"";
		return bpmServiceImpl.queryCurrProcess(userId);
	}
	
	/**
	 * 查询当前任务
	 * @return
	 */
	@RequestMapping(value="/process/todoTask",method=RequestMethod.GET)
	public List<ProcessInstanceVo> queryTodoTaskProcess(HttpServletRequest request){
		String userId = request.getAttribute("userId")+"";
		return bpmServiceImpl.queryClaimProcess(userId);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/process/agree/{procInstId}",method=RequestMethod.POST)
	public void doAgree(HttpServletRequest request,@PathVariable String procInstId){
		String userId = request.getAttribute("userId")+"";
		bpmServiceImpl.doAgree(userId,procInstId);
	}
	
	@RequestMapping(value="/process/reject/{procInstId}",method=RequestMethod.POST)
	public void doReject(HttpServletRequest request,@PathVariable String procInstId){
		String userId = request.getAttribute("userId")+"";
		bpmServiceImpl.doReject(userId,procInstId);
	}
	
	@RequestMapping(value="/process/claim/{procInstId}",method=RequestMethod.POST)
	public void doClaim(HttpServletRequest request,@PathVariable String procInstId){
		String userId = request.getAttribute("userId")+"";
		bpmServiceImpl.doClaim(userId,procInstId);
	}
	@RequestMapping(value="/process/delegate/{procInstId}",method=RequestMethod.POST)
	public void doDelegate(HttpServletRequest request,@PathVariable String procInstId){
		String userId = request.getAttribute("userId")+"";
		bpmServiceImpl.doDelegate(userId,procInstId);
	}
	
	/*@RequestMapping(value="/service/model",method=RequestMethod.GET)
	public ProcessInstanceVo dgrm2(){
		return new ProcessInstanceVo();
	}
	
	@RequestMapping(value="/service/model/{modelId}/json",method=RequestMethod.GET)
	public ProcessInstanceVo dgrm(@PathVariable String modelId){
		logger.info("模型编号："+modelId);
		return new ProcessInstanceVo();
	}*/
	
	@RequestMapping(value="/process/design",method=RequestMethod.GET)
	public ProcessInstanceVo processDesign(){
		bpmServiceImpl.processDesign();
		return new ProcessInstanceVo();
	}
	
	@RequestMapping(value="/process/model",method=RequestMethod.GET)
	public List<ModelVo> queryModel(){
		return bpmServiceImpl.selectAllModel();
	}
	
	@RequestMapping(value="/process/model/{modelId}",method=RequestMethod.POST)
	public ProcessInstanceVo editModel(String modeiId){
		bpmServiceImpl.processDesign();
		return new ProcessInstanceVo();
	}
	
	@RequestMapping(value="/process/model/deploy/{modelId}",method=RequestMethod.POST)
	public void deployModel(@PathVariable String modelId){
		bpmServiceImpl.deployModel(modelId);
		
	}
	
	@RequestMapping(value="/process/model/{modelId}",method=RequestMethod.DELETE)
	public void deleteModel(@PathVariable String modelId){
		bpmServiceImpl.deleteModel(modelId);
		
	}
	
	@RequestMapping(value="/process/jump/{procInstId}",method=RequestMethod.GET)
	public void processJump(@PathVariable String procInstId){
		 bpmServiceImpl.processJump(procInstId);
		 
	}
	
}
