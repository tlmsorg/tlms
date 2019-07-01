package com.tlms.core.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
public class RmiServer {
	
	/**
	 *  用naming方式注册服务
	 * @throws Exception
	 */
	public void registServiceByNaming() throws Exception {
		LocateRegistry.createRegistry(6500);
		IRemoteMath remoteMath = new RemoteMath();
		Naming.bind("rmi://localhost:6500/remoteMath2", remoteMath);
		
		/**
		 * Naming类，只能在“远程对象注册表”上执行储存和读取操作，不能创建“远程对象注册表”
		 * LocalRegistry:可以在本机创建“远程对象注册表”
		 * Registry类，可以创建“远程对象注册表”
		 */
		
	}
	
	public void registService() throws Exception {
		//构建registry对象二
		RMIClientSocketFactory cs = new RMIClientSocketFactory() {
			@Override
			public Socket createSocket(String host, int port) throws IOException {
				return new Socket(host, port);
			}
		};
		RMIServerSocketFactory ss = new RMIServerSocketFactory() {
			@Override
			public ServerSocket createServerSocket(int port) throws IOException {
				return new ServerSocket(port);
			}
		};
		Registry registry = LocateRegistry.createRegistry(7500, cs, ss);
			
		IRemoteMath remoteMath = new RemoteMath();
		registry.bind("remoteMath3", remoteMath);
	}
	
	public static void main(String[] args) throws Exception {
		IRemoteMath remoteMath = new RemoteMath();
		/**
		 * 构建registry对象方式一(创建注册表)
		 * 每张注册表对应一个访问端口
		 */
		Registry registry = LocateRegistry.createRegistry(5000);
		
		registry.bind("remoteMath", remoteMath);
		
		new RmiServer().registServiceByNaming();
		new RmiServer().registService();
		
		
		
		
	}

}
