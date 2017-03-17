package com.tlms.push.service.aliyun;

/**
 * 阿里云消息推送接口 20170310
 * @author tom
 *
 */
public interface AliyunMailPush {
	/**
	 * 单条或批量发送，发送制定文档或者html body脚本
	 * @return
	 */
	public String singleSendMail();
	/**
	 * 批量发送，根据发件人列表发送
	 * @return
	 */
	public String batchSendMail();
}
