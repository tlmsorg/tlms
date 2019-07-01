package com.tlms.core.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName RemoteMath
 * @Description rmi 服务实现类
 * @author 160068
 * @date 2019年6月27日 下午2:39:49
 * @version V1.0
 */
public class RemoteMath extends UnicastRemoteObject implements IRemoteMath{
	/**
	 * 类 extends 类UnicastRemoteObject，保证客户端访问获得远程对象时，该远程对象会将自己的一个拷贝以socket的形式传输给客户端。
	 * 此时客户端所获得的这个拷贝称为：存根（stub），而服务器本身存在的远程对象称为：骨架（skeleton）。
	 * 存根（stub）是客户端的代理，用来与服务器端通信。
	 * 骨架（skeleton）为服务器端代理，接收客户端请求后，调用远程方法后，将处理结果返回客户端。
	 */
	
	private static final long serialVersionUID = 6540546291326186165L;

	protected RemoteMath() throws RemoteException {
//		super();
	}

	@Override
	public String doSave(String userName) throws RemoteException{
		System.out.println("RemoteMath----->doSave:"+userName);
		return "send to client";
	}

}
