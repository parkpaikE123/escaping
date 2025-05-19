package com.ryu.escaping.payments.vo;

import org.springframework.stereotype.Component;

@Component
public class KakaoPayProperties {

	private String cid;
	private String adminkey;
	
	public String getAdminKey() {
		return adminkey;
	}
	public void setAdminKey(String adminKey) {
		this.adminkey = adminKey;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
}
