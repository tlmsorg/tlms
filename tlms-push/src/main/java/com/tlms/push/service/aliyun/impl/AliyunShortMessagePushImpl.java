package com.tlms.push.service.aliyun.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.tlms.push.service.aliyun.AliyunShortMessagePush;
/**
 * 阿里云短信推送20170313
 * @author tom
 *
 */
public class AliyunShortMessagePushImpl implements AliyunShortMessagePush {
    public void sample() {        
        try {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIh1DJbbGvo9gN", "tjqPBo4x4Ve24oG1tytPP4yeX2DD7k");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName("唐德亮");//控制台创建的签名名称
             request.setTemplateCode("SMS_53680102");//控制台创建的模板CODE
            request.setParamString("{\"name\":\"小帅帅美女\"}");//短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
            //request.setParamString("{}");
            request.setRecNum("15923518017");//接收号码
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            System.out.println(httpResponse.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		AliyunShortMessagePushImpl asmp = new AliyunShortMessagePushImpl();
		asmp.sample();
	}

	@Override
	public String singleSendMsg() {
		return "";
	}
}
