package com.tlms.push.service.aliyun;

/**
 * 阿里云短信推送服务
 * @author tom
 *
 */
public interface AliyunShortMessagePush {
	/**
	 * 单条发送
	 * @return
	 */
	public String singleSendMsg();
}
