package com.ryu.escaping.payments.vo;

import org.springframework.stereotype.Component;

@Component
public class KakaoPayProperties {

	private String cid;
	private String secretKey;
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
}
