package training.com.first.controller.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import training.com.first.controller.iface.InitiateControllerInterface;
import training.com.first.service.iface.InitiateServiceInterface;

@RestController
public class InitiateControllerImplement implements InitiateControllerInterface {

	@Autowired
	private InitiateServiceInterface service;
	
	@Override
	public ResponseEntity<?> getOutput(HttpServletRequest req, HttpServletResponse response)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
			IllegalArgumentException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.getOutput());
	}

}
