package com.sys.service.impl;

import java.awt.image.DataBufferByte;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.sys.service.IAuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class AuthServiceImpl implements IAuthService {

	public static void main(String[] args) {
		AuthServiceImpl ai = new AuthServiceImpl();
		ai.checkJwt(ai.createJwt());
	}
	@Override
	public String createJwt() {
		System.out.println("createJwt");
		String userName = "admin";
		String passWord = "admin123";
		long expiration = 6*1000*60;
		long nowMillis = System.currentTimeMillis();
		Calendar cl = Calendar.getInstance();
		Date dateNow = cl.getTime();
		
		SignatureAlgorithm signatureAlgoritm = SignatureAlgorithm.HS256;
		System.out.println(signatureAlgoritm.getJcaName());
		
		byte[] secretByte = DatatypeConverter.parseBase64Binary("secret");
		Key signKey = new SecretKeySpec(secretByte, signatureAlgoritm.getJcaName());
		JwtBuilder jb = Jwts.builder()
				.setId("200810405234")
				.setIssuedAt(dateNow)
				.setSubject("主题")
				.setIssuer("brighttang")
				.claim("userName", userName)
				.claim("passWord", passWord)
				.signWith(signatureAlgoritm, signKey);
		
		if(expiration > 0){
			long expMillis = nowMillis + expiration;
			Date expDate = new Date(expMillis);
			jb.setExpiration(expDate);
		}
		System.out.println("jb.compact():"+jb.compact());
		return jb.compact();
	}

	@Override
	public String checkJwt(String jwt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Claims claims = Jwts.parser()
		.setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
		.parseClaimsJws(jwt).getBody();
		System.out.println("claims:"+claims);
		System.out.println(sdf.format(claims.getExpiration()));
		System.out.println("claims.getId():"+claims.getId());
		System.err.println("claims.getIssuer():"+claims.getIssuer());
		System.out.println("claims.getSubject():"+claims.getSubject());
		System.out.println("sub:"+claims.get("sub"));
		System.out.println("userName:"+claims.get("userName"));
		System.out.println("passWord:"+claims.get("passWord"));
		return null;
	}

}
