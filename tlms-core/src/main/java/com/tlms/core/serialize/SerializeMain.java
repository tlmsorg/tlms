package com.tlms.core.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeMain implements Serializable{

	private static final long serialVersionUID = -368874869L;
	
	private String name;
	
	
	public void doDeserialize() throws Exception{
		FileInputStream fis =  new FileInputStream(new File("d://contract.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		Contract contract = (Contract) ois.readObject();
		contract.afterDeserialize();
		System.out.println(contract.getId());
		System.out.println(contract.getContractName());
		System.out.println(contract.getContractNo());
	}
	
	public void doSerialize() throws Exception{
		Contract contract = new Contract();
		contract.setContractName("333.pdf");
		contract.setContractNo("222");
		contract.setId("111");
		FileOutputStream fos = new FileOutputStream(new File("d://contract.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(contract);
		oos.flush();
		fos.flush();
		oos.close();
		fos.close();
	}
	
	public static void main(String[] args) throws Exception {
		SerializeMain sm = new SerializeMain();
		sm.doSerialize();
		sm.doDeserialize();
		
	}

}
