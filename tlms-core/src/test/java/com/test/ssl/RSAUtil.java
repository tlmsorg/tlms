package com.test.ssl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;


public class RSAUtil {

	public static KeyPair genRsaKeyPair() throws Exception {
		KeyPair keyPair = null;
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		
		SecureRandom random = new SecureRandom();
		byte[] randomByte = random.generateSeed(12);
//		byte[] randomByte = new byte[12];
		random.nextBytes(randomByte);
		System.out.println("1:"+randomByte[0]);
		
		kpg.initialize(1024, random);
		keyPair = kpg.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		byte[] privateEncode = privateKey.getEncoded();
		System.out.println(privateEncode[1]);
		
//		keyStore.setKeyEntry("private", privateKey, "123456".toCharArray(), null);
		System.out.println(publicKey.getEncoded().length);
		
		return keyPair;
	}
	
	public static void main(String[] args) throws Exception {
		RSAUtil rsaUtil = new RSAUtil();
		rsaUtil.genRsaKeyPair();
	}

}
