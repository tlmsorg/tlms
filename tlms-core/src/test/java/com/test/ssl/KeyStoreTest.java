package com.test.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;;

public class KeyStoreTest {

	public void createJceks() throws Exception {
		String keyStoreFile = "keystore_jceks.jceks";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(null, null);
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		Key key = keyGen.generateKey();
		System.out.println(key);
		keyStore.setKeyEntry("nickname", key, storeFilePass.toCharArray(), null);
		System.out.println(File.separator);
		keyStore.store(new FileOutputStream("D:"+File.separator+keyStoreFile), storeFilePass.toCharArray());
	}
	
	public void readJceks() throws Exception {
		String keyStoreFile = "keystore_jceks.jceks";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(new FileInputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
		Key key = keyStore.getKey("nickname", storeFilePass.toCharArray());
		System.out.println("获取keyStore中的key："+key);
	}
	
	/**
	 * 存储密钥
	 * 160068
	 * 2018年11月5日 下午4:50:10
	 * @throws Exception
	 */
	public void createPkcs12() throws Exception {
		String keyStoreFile = "keystore_pkcs12.p12";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null);
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey sk = keyGen.generateKey();
		keyStore.setKeyEntry("nickname_pkcs12", sk, storeFilePass.toCharArray(), null);
		keyStore.store(new FileOutputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
	}
	
	/**
	 * 存储私钥
	 * 160068
	 * 2018年11月5日 下午4:49:49
	 * @throws Exception
	 */
	public void createPrivatePkcs12() throws Exception {
		
		String keyStoreFile = "keystore_pkcs12_private.p12";
		String storeFilePass = "pass";
		
		String commonName = "www.pujjr.com";
		//组织单位名称
		String organizationalUnit = "信息科技部";
		//组织名称
		String organizetion = "潽金金融";
		//城市或者区域
		String location = "昆明市";
		//州或者省份
		String state = "云南省";
		//国家代码
		String country = "cn";
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null);
		
		CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA", "SHA1WithRSA");
		certAndKeyGen.generate(1024);
		X500Name x500Name1 = new X500Name("CN=ROOT");
		X500Name x500Name2 = new X500Name(commonName, organizationalUnit, organizetion, location, state, country);
		X509Certificate cert = certAndKeyGen.getSelfCertificate(x500Name2, (long)365*24*3600);
		X509Certificate[] chain = new X509Certificate[1];
		chain[0] = cert;
		PrivateKey privateKey = certAndKeyGen.getPrivateKey();

		byte[] privateEncoded = privateKey.getEncoded();
		keyStore.setKeyEntry("private", privateKey, storeFilePass.toCharArray(), chain);
		//生成私钥
		keyStore.store(new FileOutputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());

		//String keyStoreFile2 = "keystore_pkcs12_cert.p12";
		String keyStoreFile2 = "keystore_pkcs12_cert.cer";
		String storeFilePass2 = "pass";
		KeyStore keyStore2 = KeyStore.getInstance("PKCS12");
		keyStore2.load(null, null);
		keyStore2.setCertificateEntry("cert", cert);
		/**
		 * publicEncoded与publicEncoded2值完全一致
		 */
		byte[] publicEncoded = cert.getPublicKey().getEncoded();
		byte[] publicEncoded2 = certAndKeyGen.getPublicKey().getEncoded();
		//生成证书
//		keyStore2.store(new FileOutputStream(new File("D:" + File.separator + keyStoreFile2)), storeFilePass2.toCharArray());
		FileOutputStream fos = new FileOutputStream(new File("D:" + File.separator + keyStoreFile2));
		fos.write(Base64.getEncoder().encode(cert.getEncoded()));
	}
	
	/**
	 * 存储证书
	 * 160068
	 * 2018年11月5日 下午5:31:33
	 * @throws Exception
	 */
	public void createCertPkcs12() throws Exception {
		String keyStoreFile = "keystore_pkcs12_cert.p12";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null);

		CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA", "SHA1WithRSA");
		certAndKeyGen.generate(1024);
		X509Certificate cert = certAndKeyGen.getSelfCertificate(new X500Name("CN=ROOT"), (long)365*24*3600);
		X509Certificate[] chain = new X509Certificate[1];
		chain[0] = cert;
		
		keyStore.setCertificateEntry("cert", cert);
		
		
		keyStore.store(new FileOutputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
	}
	
	/**
	 * 加载私钥
	 * 160068
	 * 2018年11月5日 下午5:42:05
	 * @throws Exception
	 */
	public void readPrivateKey() throws Exception{
		String keyStoreFile = "keystore_pkcs12_private.p12";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
		Key key = keyStore.getKey("private", storeFilePass.toCharArray());
		byte[] privateEncoded = key.getEncoded();
		System.out.println("key:"+key.toString());
	}
	
	/**
	 * 加载证书
	 * 160068
	 * 2018年11月5日 下午5:52:33
	 * @throws Exception
	 */
	public void readCert() throws Exception{
		String keyStoreFile = "keystore_pkcs12_cert.p12";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
		
		Enumeration<String> en = keyStore.aliases();
		while(en.hasMoreElements()) {
			String next = en.nextElement();
			System.out.println("next:"+next);
		}
		
		Certificate certificate = keyStore.getCertificate("cert");
		PublicKey publicKey = certificate.getPublicKey();
		System.out.println("certificate:" + certificate);
		byte[] publicEncoded = publicKey.getEncoded();
		System.out.println("key的主编码格式长度："+publicEncoded.length);
		for (int i = 0; i < publicEncoded.length; i++) {
//			System.out.println(publicEncoded[i]);
		}
		
		String publicKeyStr = new String(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
		System.out.println("publicKeyStr:"+publicKeyStr);
	}
	
	public void readCert2() throws Exception{
		String keyStoreFile = "keystore_pkcs12_cert.cer";
		String storeFilePass = "pass";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
		
		Enumeration<String> en = keyStore.aliases();
		while(en.hasMoreElements()) {
			String next = en.nextElement();
			System.out.println("next:"+next);
		}
		
		Certificate certificate = keyStore.getCertificate("cert");
		PublicKey publicKey = certificate.getPublicKey();
		System.out.println("certificate:" + certificate);
		byte[] publicEncoded = publicKey.getEncoded();
		System.out.println("key的主编码格式长度："+publicEncoded.length);
		for (int i = 0; i < publicEncoded.length; i++) {
			System.out.println(publicEncoded[i]);
		}
		
		String publicKeyStr = new String(publicKey.getEncoded());
		System.out.println("publicKeyStr:"+publicKeyStr);
	}
	
	/**
	 * 读取同盾证书
	 * 160068
	 * 2018年11月8日 下午1:52:54
	 * @throws Exception
	 */
	public void readTdCert() throws Exception{
		/*String keyStoreFile = "2017-tongdun.cn.cer";
		String storeFilePass = "changeit";
		KeyStore keyStore = KeyStore.getInstance("PKCS7");
		keyStore.load(null, null);
		
		keyStore.load(new FileInputStream(new File("D:" + File.separator + keyStoreFile)), storeFilePass.toCharArray());
		Certificate certificate = keyStore.getCertificate("tongdun");
		PublicKey publicKey = certificate.getPublicKey();
		System.out.println("certificate:" + certificate);
		byte[] publicEncoded = publicKey.getEncoded();
		System.out.println("key的主编码格式长度："+publicEncoded.length);
		for (int i = 0; i < publicEncoded.length; i++) {
			System.out.println(publicEncoded[i]);
		}
		
		String publicKeyStr = new String(publicKey.getEncoded());
		System.out.println("publicKeyStr:"+publicKeyStr);
		*/
		
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream fis = new FileInputStream(new File("D:" + File.separator + "2017-tongdun.cn.cer"));
//		X509Certificate cer = (X509Certificate) cf.generateCertificate(fis);
		Certificate cer = cf.generateCertificate(fis);
		fis.close();
		System.out.println(cer);
		PublicKey publicKey = cer.getPublicKey();
		System.out.println(publicKey.getEncoded());
		
//		"SHA256withRSA"	
		
	}
	
	
	private static byte[] encryptByPublicKeyRSALong(byte[] data, byte[] keyBytes) throws Exception {
		try {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA","BC");
			RSAPublicKey publicKey2 = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);

			BigInteger modulus = publicKey2.getModulus();
			BigInteger exponent = publicKey2.getPublicExponent();

			RSAKeyParameters pubKey = new RSAKeyParameters(false, modulus, exponent);
			AsymmetricBlockCipher rsaCiph = new RSAEngine();
			rsaCiph.init(true, pubKey);

			
			int blockSize = rsaCiph.getInputBlockSize();
			int outputSize = rsaCiph.getOutputBlockSize();
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			byte[] encBytes = null;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize) {
					encBytes = rsaCiph.processBlock(data, i * blockSize, blockSize);
				} else {
					encBytes = rsaCiph.processBlock(data, i * blockSize, data.length - i * blockSize);
				}
				System.arraycopy(encBytes, 0, raw, i * outputSize, outputSize);
				i++;
			}
			return raw;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		KeyStoreTest kst = new KeyStoreTest();
//		kst.createJceks();
//		kst.readJceks();
//		kst.createPkcs12();
//		kst.createPrivatePkcs12();
//		kst.createCertPkcs12();
//		kst.readPrivateKey();
//		kst.readCert();
		kst.readTdCert();
	}

}
