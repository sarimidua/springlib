package training.com.first.security;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import training.com.first.security.JWTToken;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class BaseAuth extends RuntimeException{


	private static final long serialVersionUID = 1028699980114882673L;
	@Autowired
	private JWTToken jwtTokenUtil;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public ResponseEntity<?> AuthLoginId(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		String userLogin = "";
				
		if (token != null) {
			userLogin = jwtTokenUtil.getIdFromToken(token.substring(6));
			if (!redisTemplate.hasKey("login:" + userLogin)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Collections.singletonMap("Message", "UNAUTHORIZED"));
			}
			redisTemplate.expire("login:" + userLogin, 30, TimeUnit.MINUTES);
			return ResponseEntity.ok(userLogin);			
		} 
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(Collections.singletonMap("Message", "FORBIDDEN"));
	}
	
	public ResponseEntity<?> getOrgCodeFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		String userOrganization = "";
		
		if (token != null) {
			userOrganization = jwtTokenUtil.getOrgFromToken(token.substring(6));
			
			return ResponseEntity.ok(userOrganization);			
		} 
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(Collections.singletonMap("Message", "FORBIDDEN"));
	}
	
	public ResponseEntity<?> getIdOrgFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		String idUserOrganization = "";
		
		if (token != null) {
			idUserOrganization = jwtTokenUtil.getIdOrgFromToken(token.substring(6));
			
			return ResponseEntity.ok(idUserOrganization);			
		} 
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(Collections.singletonMap("Message", "FORBIDDEN"));
	}
	
	public int res() {
		return HttpServletResponse.SC_OK;
	}
	
}
