package com.tlms.core.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName IRemoteMath
 * @Description rmi服务接口
 * @author 160068
 * @date 2019年6月27日 下午2:39:24
 * @version V1.0
 * 
 */
public interface IRemoteMath extends Remote{
	/**
	 * Remote接口：一个类extends java.rmi.Remote接口后，该类对象可以作为远程对象共其他服务调用
	 */
	
	
	public String doSave(String userName) throws RemoteException;
}
