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
import com.tlms.bpm.vo.ProcessInstanceVo;

@RestController
@ResponseBody
public class BpmController {
	private static final Logger logger = Logger.getLogger(BpmController.class);
	@Autowired
	private IBpmService bpmServiceImpl;
	
	@RequestMapping(value="/service/process/deploy",method=RequestMethod.GET)
	public void processDeploy(HttpServletRequest request){
		logger.info(request.getAttribute("userId"));
		bpmServiceImpl.deployProcess();
	}
	
	@RequestMapping(value="/service/process/delete",method=RequestMethod.GET)
	public void deleteProcess(HttpServletRequest request){
		bpmServiceImpl.deleteProcess();
	}
	
	
	@RequestMapping(value="/service/process/start/{processId}",method=RequestMethod.GET)
	public void startProcess(HttpServletRequest request,@PathVariable String processId){
		bpmServiceImpl.startProcess(processId);
	}
	
	@RequestMapping(value="/service/process/query",method=RequestMethod.GET)
	public List<ProcessInstanceVo> queryProcess(){
		return bpmServiceImpl.queryProcess();
	}
	
	/**
	 * 读取流程图
	 * @param processInstId
	 * @param resourceType
	 * @param response
	 */
	@RequestMapping(value="/process/source/{processInstId}/{resourceType}",method=RequestMethod.GET)
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
	@RequestMapping(value="/service/process/task",method=RequestMethod.GET)
	public List<ProcessInstanceVo> queryCurrProcess(HttpServletRequest request){
		String userId = request.getAttribute("userId")+"";
		return bpmServiceImpl.queryCurrProcess(userId);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/service/process/agree/{procInstId}",method=RequestMethod.POST)
	public void doAgree(HttpServletRequest request,@PathVariable String procInstId){
		String userId = request.getAttribute("userId")+"";
		bpmServiceImpl.doAgree(userId,procInstId);
	}
}
