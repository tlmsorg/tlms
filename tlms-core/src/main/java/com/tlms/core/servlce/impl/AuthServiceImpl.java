package com.tlms.core.servlce.impl;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tlms.core.domain.SysUser;
import com.tlms.core.service.IAuthService;
import com.tlms.core.service.IUserService;
import com.tlms.core.vo.TokenVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class AuthServiceImpl implements IAuthService {
	@Value("${token.expire.time}")
	private long expireTime;
	@Autowired
	private IUserService userServiceImpl;
	public static void main(String[] args) {
		AuthServiceImpl ai = new AuthServiceImpl();
		SysUser user = new SysUser();
		user.setUserId("001");
		user.setPasswd("000000");
		ai.checkJwt(ai.createJwt(user).getToken());
	}
	@Override
	public TokenVo createJwt(SysUser user) {
		Long tokenExpireTime = System.currentTimeMillis() + expireTime;
		String userId = user.getUserId();
		String passwd = user.getPasswd();
//		long expiration = 6*1000*60;
		long nowMillis = System.currentTimeMillis();
		Calendar cl = Calendar.getInstance();
		Date dateNow = cl.getTime();
		//数字签名算法
		SignatureAlgorithm signatureAlgoritm = SignatureAlgorithm.HS256;
		System.out.println(signatureAlgoritm.getJcaName());
		//密钥字节数组
		byte[] secretByte = DatatypeConverter.parseBase64Binary("secret");
		//结合签名算法名称、密钥数组生成密钥
		Key signKey = new SecretKeySpec(secretByte, signatureAlgoritm.getJcaName());
		//创建java webtoken实例
		JwtBuilder jb = Jwts.builder()
				.setId("200810405234")
				.setIssuedAt(dateNow)
				.setSubject("主题")
				.setIssuer("brighttang")
				.claim("userId", userId)
				.claim("passwd", passwd)
				.claim("expireTime",tokenExpireTime)//token超时时间
				.signWith(signatureAlgoritm, signKey);
		
		if(expireTime > 0){
			long expMillis = nowMillis + expireTime;
			Date expDate = new Date(tokenExpireTime);
			//设置webtoken失效时间
			jb.setExpiration(expDate);
		}
		System.out.println("jb.compact():"+jb.compact());
		TokenVo tokenVo = new TokenVo();
		tokenVo.setToken(jb.compact());
		tokenVo.setExpireTime(tokenExpireTime);
		return tokenVo;
	}

	@Override
	public TokenVo checkJwt(String jwt) {
		TokenVo tokenVo = new TokenVo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
				.parseClaimsJws(jwt).getBody();
		
		System.out.println("*************webtoken验证*************");
		System.out.println("claims:"+claims);
//		System.out.println(sdf.format(claims.getExpiration()));
		System.out.println("claims.getId():"+claims.getId());
		System.err.println("claims.getIssuer():"+claims.getIssuer());
		System.out.println("claims.getSubject():"+claims.getSubject());
		System.out.println("sub:"+claims.get("sub"));
		System.out.println("userId:"+claims.get("userId"));
		System.out.println("passwd:"+claims.get("passwd"));
		System.out.println("expireTime:"+claims.get("expireTime"));
		System.out.println("系统当前时间："+System.currentTimeMillis());
		
		//模拟密码验证通过后，刷新token
		SysUser user = new SysUser();
		user.setUserId(claims.get("userId")+"");
		user.setPasswd(claims.get("passwd")+"");
		tokenVo = this.createJwt(user);
		tokenVo.setUserId(claims.get("userId")+"");
		tokenVo.setPasswd(claims.get("passwd")+"");
		return tokenVo;
	}

}
