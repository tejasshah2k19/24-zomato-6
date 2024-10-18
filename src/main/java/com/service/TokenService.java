package com.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	public String generateToken() {
		String data = "0987654321qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
		
		StringBuffer sb = new StringBuffer("");
		for(int i=1;i<=20;i++) {
			int index = (int)(Math.random()*data.length());//0 1 2 36 5 
			sb.append(data.charAt(index));
		}
		
		return sb.toString();
		
	}
}
