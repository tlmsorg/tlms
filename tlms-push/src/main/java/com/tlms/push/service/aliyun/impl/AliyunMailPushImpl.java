package com.tlms.push.service.aliyun.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.BatchSendMailResponse;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tlms.push.service.aliyun.AliyunMailPush;
/**
 * 阿里云消息推送实现20170310
 * @author tom
 *
 */
public class AliyunMailPushImpl implements AliyunMailPush {

	/**
	 * 官方示例
	 */
	public void sample() {        
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIh1DJbbGvo9gN", "tjqPBo4x4Ve24oG1tytPP4yeX2DD7k");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        SingleSendMailResponse httpResponse = null;
        try {
            request.setAccountName("admin@mail.xiaoshuai.site");//控制台创建的发信地址
            request.setFromAlias("潽金金融");
            request.setAddressType(1);
            request.setTagName("控制台创建的标签");
            request.setReplyToAddress(true);
            request.setToAddress("798758642@qq.com");
            request.setSubject("邮件主题:还款提醒");

            String htmlBody = "<h2>This is a heading</h2><p>This is a paragraph.</p><p>This is another paragraph.</p><div style='color: red'>测试</div><button type='button' id='login'>Click me</button><a href='https://dm.console.aliyun.com/#/directmail/Home'>跳转阿里云</a>";
            request.setHtmlBody(htmlBody);
//          request.setHtmlBody("邮件正文");
//          request.setTextBody("您好，该还款了，哈哈哈哈哈啊哈22222！");
//            request.setTextBody(htmlBody);
             client.getAcsResponse(request);
            System.out.println(httpResponse.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }


	@Override
	public String singleSendMail() {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIh1DJbbGvo9gN", "tjqPBo4x4Ve24oG1tytPP4yeX2DD7k");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        SingleSendMailResponse httpResponse = null;
        try {
            request.setAccountName("admin@mail.xiaoshuai.site");//控制台创建的发信地址
            request.setFromAlias("潽金金融");
            request.setAddressType(1);
            request.setTagName("控制台创建的标签");
            request.setReplyToAddress(true);
            request.setToAddress("798758642@qq.com");
            request.setSubject("邮件主题:还款提醒");

            String htmlBody = "<h2>This is a heading</h2><p>This is a paragraph.</p><p>This is another paragraph.</p><div style='color: red'>测试</div><button type='button' id='login'>Click me</button><a href='https://dm.console.aliyun.com/#/directmail/Home'>跳转阿里云</a>";
            request.setHtmlBody(htmlBody);
//          request.setHtmlBody("邮件正文");
//          request.setTextBody("您好，该还款了，哈哈哈哈哈啊哈22222！");
//            request.setTextBody(htmlBody);
             client.getAcsResponse(request);
            System.out.println(httpResponse.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return httpResponse.getRequestId();
	}

	@Override
	public String batchSendMail() {
		 IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIh1DJbbGvo9gN", "tjqPBo4x4Ve24oG1tytPP4yeX2DD7k");
	        IAcsClient client = new DefaultAcsClient(profile);
	        BatchSendMailRequest request = new BatchSendMailRequest();
	        BatchSendMailResponse batchResponse = null;
	        try {
	            request.setAccountName("admin@mail.xiaoshuai.site");//控制台创建的发信地址
//	            request.setFromAlias("潽金金融");
	            request.setAddressType(1);
	            request.setTagName("API批量发送标签");
	            request.setTemplateName("还款提醒模板");
	            request.setReceiversName("王小二");
//	            request.setReplyToAddress(true);
//	            request.setToAddress("798758642@qq.com");
//	            request.setSubject("邮件主题:还款提醒");

//	            String htmlBody = "<h2>This is a heading</h2><p>This is a paragraph.</p><p>This is another paragraph.</p><div style='color: red'>测试</div><button type='button' id='login'>Click me</button><a href='https://dm.console.aliyun.com/#/directmail/Home'>跳转阿里云</a>";
//	            request.setHtmlBody(htmlBody);
//	          request.setHtmlBody("邮件正文");
//	          request.setTextBody("您好，该还款了，哈哈哈哈哈啊哈22222！");
//	            request.setTextBody(htmlBody);
	            batchResponse = client.getAcsResponse(request);
	            System.out.println(batchResponse.getRequestId());
	        } catch (ServerException e) {
	            e.printStackTrace();
	        }
	        catch (ClientException e) {
	            e.printStackTrace();
	        }
	        return batchResponse.getRequestId();
	}
	
	public static void main(String[] args) {
		AliyunMailPushImpl ams = new AliyunMailPushImpl();
//		ams.sample();
		ams.batchSendMail();
	}
}
