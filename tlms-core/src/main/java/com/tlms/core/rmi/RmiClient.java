package com.tlms.core.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName RmiClient
 * @Description
 * @author 160068
 * @date 2019年6月27日 下午2:39:16
 * @version V1.0
 */
public class RmiClient {
	public static void main(String[] args) throws Exception {
		
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5000);
		IRemoteMath remoteMath = (IRemoteMath) registry.lookup("remoteMath");
		String test=remoteMath.doSave("RmiClient");
		System.out.println("客户端调用服务端："+test);
		
		IRemoteMath remoteMath2 = (IRemoteMath) Naming.lookup("rmi://localhost:6500/remoteMath2");
		String test2=remoteMath2.doSave("RmiClient2");
		System.out.println("客户端调用服务端："+test2);
		
		Registry rigistry3 = LocateRegistry.getRegistry("127.0.0.1",7500);
		IRemoteMath remoteMath3 = (IRemoteMath) rigistry3.lookup("remoteMath3");
		String test3=remoteMath3.doSave("RmiClient3");
		System.out.println("客户端调用服务端："+test3);
	}
}
